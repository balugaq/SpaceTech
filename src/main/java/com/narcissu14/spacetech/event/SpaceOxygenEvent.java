package com.narcissu14.spacetech.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Narcissu14
 */
public class SpaceOxygenEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final Player player;
    private boolean cancelled = false;
    @Getter
    private int oxygen;

    public SpaceOxygenEvent(Player player, int oxygen) {
        this.player = player;
        this.oxygen = oxygen;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = Math.max(oxygen, 0);
    }

    public void addOxygen(int oxygen) {
        setOxygen(this.oxygen + oxygen);
    }
}