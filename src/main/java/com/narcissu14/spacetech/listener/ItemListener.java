package com.narcissu14.spacetech.listener;

import com.narcissu14.spacetech.SpaceTech;
import com.narcissu14.spacetech.objects.STItems;
import com.narcissu14.spacetech.setup.config.STConfig;
import com.narcissu14.spacetech.utils.ActionBarAPI;
import io.github.thebusybiscuit.slimefun4.api.events.ResearchUnlockEvent;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Narcissu14
 */
public class ItemListener implements Listener {
    public ItemListener(@NotNull SpaceTech plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * 限制或禁止物品放置的监听
     **/
    @EventHandler
    public void onBlockPlace(@NotNull BlockPlaceEvent event) {
        if (SlimefunUtils.isItemSimilar(event.getItemInHand(), STItems.ANTIMATTER_COLLECT_MACHINE, true)) {
            if (!STConfig.spaceWorldList.contains(event.getPlayer().getWorld().getName())) {
                ActionBarAPI.sendActionBar(event.getPlayer(), "§c§l你只能在太空中放置 §d反物质捕获机");
                event.setCancelled(true);
            }
        }
        //以后也许会增加更多
    }

    @EventHandler
    public void onPlayerUnlockNew(@NotNull ResearchUnlockEvent event) {
        if (!event.getPlayer().hasPermission("slimefun.*")) {
            StringBuilder unlockMsg = new StringBuilder("§6☞ §e");
            unlockMsg.append(event.getPlayer().getName());
            unlockMsg.append(" §a解锁了远古工艺研究: §e");
            unlockMsg.append(event.getResearch().getName(event.getPlayer()));
            Bukkit.broadcastMessage(unlockMsg.toString());
        }
    }
}
