package com.narcissu14.spacetech.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author Narcissu14
 */
public class SpaceOxygenEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;

    private Player player;
    private int oxygen;

    public SpaceOxygenEvent(Player player, int oxygen) {
        this.player = player;
        this.oxygen = oxygen;
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
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        if (oxygen > 0) {
            this.oxygen = oxygen;
        } else {
            this.oxygen = 0;
        }
    }

    public void addOxygen(int oxygen) {
        setOxygen(this.oxygen + oxygen);
    }
}