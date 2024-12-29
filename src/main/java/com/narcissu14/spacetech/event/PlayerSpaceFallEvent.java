package com.narcissu14.spacetech.event;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author Narcissu14
 */
public class PlayerSpaceFallEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final Location from;
    @Getter
    private final Location to;
    @Getter
    private final Player player;
    private boolean cancelled = false;

    public PlayerSpaceFallEvent(Player player, Location from, Location to) {
        this.from = from;
        this.to = to;
        this.player = player;
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

}
