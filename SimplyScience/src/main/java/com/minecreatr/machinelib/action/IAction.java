package com.minecreatr.machinelib.action;

import com.minecreatr.machinelib.IMachineTile;

/**
 * Represents an Action that can be taken on a tile entity
 *
 * @author minecreatr
 */
public interface IAction {

    /**
     * Whether this action can be executed on the specified tile
     * @param tile The Tile
     * @return Whether this action can be executed on the tile
     */
    boolean canExecute(IMachineTile tile);

    /**
     * Execute this action on the following tile
     * @param tile The Tile
     * @return Whether the action was able to complete succesfully
     */
    boolean execute(IMachineTile tile);
}
