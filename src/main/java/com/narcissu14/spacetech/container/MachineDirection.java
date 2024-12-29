package com.narcissu14.spacetech.container;

import org.jetbrains.annotations.NotNull;

/**
 * @author Narcissu14
 */
public enum MachineDirection {
    DOWN,
    UP,
    NORTH,
    EAST,
    SOUTH,
    WEST,
    NO_DIRECTION;

    public static @NotNull MachineDirection getByName(String name) {
        for (MachineDirection direction : MachineDirection.values()) {
            if (direction.name().equalsIgnoreCase(name)) {
                return direction;
            }
        }
        return NO_DIRECTION;
    }
}
