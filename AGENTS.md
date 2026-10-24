# AGENTS.md

本文件是给 AI 编程助手看的项目说明与工作约定。动手前先读完本文件。

## 项目概况

SpaceTech 是一个 Slimefun4 扩展插件，为 Minecraft 服务器添加：随机生成的太空世界（THE_END 环境）、太空独有矿物（钛/钕/铜/银）、高科技机器（联氨合成机、电磁炼矿机、反物质捕获机、基岩破穿器等）与太空装备（各级太空盔、喷气太空服）。

- 技术栈：Java 16 + Maven，目标平台 Paper 1.20.1，运行时依赖 Slimefun4 插件
- 包名：`com.narcissu14.spacetech`
- 主类：`com.narcissu14.spacetech.SpaceTech`（继承 `JavaPlugin`，实现 `SlimefunAddon`）
- 上游：https://gitee.com/narcissu14/SpaceTech （本仓库为非官方 fork）
- 协议：GNU AGPL，见 LICENSE

## 构建与验证

- 构建：`mvn clean package`（pom 的 defaultGoal 即为此，直接 `mvn` 也行）
- 产物：`target/SpaceTech-13.jar`，由 maven-shade 打包（`minimizeJar=true`）
- 部署验证：jar 放入 Paper 1.20.1 服务端 `plugins/` 目录，同服需安装 Slimefun4
- 仓库无自动化测试。AI 改完代码的最低验收线是 `mvn package` 编译通过；运行期行为（生成器、机器逻辑、事件）由主人在测试服实测，AI 不得仅凭编译通过宣称功能正常
- **已知环境坑（2026-10-24 发现，未修复）**：本机 Maven 默认跑在 JDK 26 上，而 Lombok 1.18.34 不支持 JDK 26——注解处理静默失效，所有 Lombok 生成的 getter 全部丢失，编译报约 200 个「找不到符号」（`getInstance()` / `getOutput()` / `getPoints()` 等，全部集中在 `STItemSetup.java` 和 `AbstractPointsMachine.java`）。**这不是代码 bug，不要为此改业务代码。** 修复二选一：① pom 中 Lombok 升级到支持新 JDK 的版本（当前中央仓库最新 1.18.48，建议直接上最新）；② 安装 JDK 21 并设置 `JAVA_HOME` 指过去再构建。修哪个由主人定。

## 目录结构

```
src/main/java/com/narcissu14/spacetech/
├── SpaceTech.java            # 主类：配置加载、物品注册、监听器注册、太空世界创建
├── objects/
│   ├── STItems.java          # 所有物品的 SlimefunItemStack 声明（最常改的文件）
│   ├── STCategories.java     # Slimefun 分类
│   ├── RechargeableItem.java / UnplaceableItem.java
│   └── blocks/               # 机器与矿石实现
│       ├── AbstractOre.java / SpaceOre.java      # 太空矿物
│       ├── AbstractPointsMachine.java            # 带"挖掘度"机器的基类
│       ├── HydrazineMachine / OxygenChargeMachine / OreRefiningMachine ...
├── setup/
│   ├── STItemSetup.java      # 物品注册入口（setupItems()），配方多在此装配
│   ├── CreatorMachine.java / UUMachine.java
│   └── config/               # AbstractConfig 基类 + STConfig（SpaceWorld.SpaceWorldList）
├── listener/
│   ├── SpaceWorldListener.java # 太空世界机制：氧气/窒息、低引力、冰结水、防末影龙
│   └── ItemListener.java       # 物品交互（太空盔、镐、检测器等）
├── generator/
│   ├── SpaceGenerator.java     # 太空世界 ChunkGenerator（空区块 + 固定出生点）
│   └── populator/PlanetPopulator.java # 星球与星核生成
├── event/                    # 自定义 Bukkit 事件（SpaceOxygenEvent、PlayerSpaceFallEvent）
├── container/                # MachineDirection、PointMachineRecipe 等辅助数据类
└── utils/                    # ActionBarAPI、TitleAPI、ItemUtils、SkullUtil、ChargeableBlock、ItemEnergy 等
```

## 代码约定

1. **新物品流程**：在 `STItems.java` 声明 `SlimefunItemStack`（ID 统一带 `SPACETECH_` 前缀），再在 `STItemSetup.setupItems()` 里注册并配配方。物品 ID 一旦发布不要改，改了等于删旧物品、坏存档。
2. **物品显示**：物品名与 lore 用中文，颜色用 `§` 代码；自定义物品外观统一走 `CustomItemStack` / `CustomPotionItem`。
3. **Lombok** 可用（`@Getter` 等），依赖为 provided，IDE 需装插件。
4. **机器实现**：有"挖掘度/点数"机制的机器继承 `AbstractPointsMachine`；耗电机器参考已有类的 `ChargeUtils` / `ChargeableBlock` 用法，别发明新电力体系。
5. **注释与文档**：注释用中文；公共方法按现有风格补简短注释。README/TODO 均为中文。
6. **配置**：所有可配置项走 `STConfig`，默认值写进 jar 内 `resources/config.yml`；新增配置项要同步默认文件并在 `initOptions()` 读取。
7. **命名**：类名大驼峰，方法小驼峰，常量全大写下划线——与现有代码保持一致，别引入新风格。

## 注意事项（踩过的坑）

- **Java 版本别被 pom 骗了**：`<properties>` 里 `java.version=20` 名不副实，实际编译级别是 maven-compiler-plugin 的 source/target = **16**。语法用到 17+ 特性会编译失败。
- **config.yml 双层 resources 路径**：源文件在 `src/main/resources/resources/config.yml`（不是笔误）。`SpaceTech#createDefaultConfiguration` 从 jar entry `resources/config.yml` 读取，移动它会导致默认配置不再释放。
- **太空世界生成方式特殊**：THE_END 环境 + FLAT 世界类型 + `SpaceGenerator`；`SpaceTech#loadSpaceWorld` 还会处理出生点末影水晶/基岩清理。改生成逻辑前先读 `PlanetPopulator` 和这段代码。
- **需求跟踪**：`TODO.md` 是功能清单，完成的条目标【完成】。新需求先落 TODO 再动手，保持仓库传统。
- **提交边界**：AI 只把工作区改到可验收状态，不主动 commit / push；提交由主人决定。

## 给 AI 的工作方式

- 改代码前先 Read 相关文件，理解现有模式再动手，不要凭空发明新架构。
- 批量改 `STItems.java` / `STItemSetup.java` 时逐条修改，改完 grep 复查落地。
- 涉及删除、批量重命名、世界生成参数调整等高风险操作，先说明影响面，等主人确认。
