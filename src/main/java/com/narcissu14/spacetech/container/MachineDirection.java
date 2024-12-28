package com.narcissu14.spacetech.container;

/**
 * @author Narcissu14
 */
public enum MachineDirection {
    DOWN, UP, NORTH, EAST, SOUTH, WEST, NO_DIRECTION;

    public static MachineDirection getByName(String name) {
        switch (name.toUpperCase()) {
            case "DOWN":
                return DOWN;
            case "UP":
                return UP;
            case "NORTH":
                return NORTH;
            case "EAST":
                return EAST;
            case "SOUTH":
                return SOUTH;
            case "WEST":
                return WEST;
            default:
                return NO_DIRECTION;
        }
    }
}
