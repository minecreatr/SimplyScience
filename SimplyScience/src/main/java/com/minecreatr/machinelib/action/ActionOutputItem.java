package com.minecreatr.machinelib.action;

import com.minecreatr.machinelib.IMachineTile;
import com.minecreatr.machinelib.util.StackUtility;
import net.minecraft.item.ItemStack;

/**
 * Action for outputting a specific item
 *
 * @author minecreatr
 */
public class ActionOutputItem implements IAction{

    private ItemStack stack;

    private int prefSlot;

    public ActionOutputItem(ItemStack stack, int slot){
        this.stack = stack;
        this.prefSlot = slot;
    }

    public ActionOutputItem(ItemStack stack){
        this(stack, -1);
    }

    public boolean canExecute(IMachineTile tile){
        if (prefSlot < 0) {
            int[] outSlots = tile.getHandler().getOutputSlots();
            for (int slot : outSlots) {
                if (tile.getStackInSlot(slot) == null || (StackUtility.canStack(tile.getStackInSlot(slot), stack))) {
                    return true;
                }
            }
        }
        else if (prefSlot < tile.getHandler().getTotalSlots()){
            return tile.getStackInSlot(prefSlot) == null || (StackUtility.canStack(tile.getStackInSlot(prefSlot), stack));
        }
        return false;
    }

    public boolean execute(IMachineTile tile){
        if (prefSlot < 0) {
            int[] outSlots = tile.getHandler().getOutputSlots();
            for (int slot : outSlots) {
                if (tile.getStackInSlot(slot) == null || (StackUtility.canStack(tile.getStackInSlot(slot), stack))) {
                    tile.setStackInSlot(slot, StackUtility.unsafeStack(tile.getStackInSlot(slot), stack));
                    return true;
                }
            }
        }
        else if (prefSlot < tile.getHandler().getTotalSlots()){
            tile.setStackInSlot(prefSlot, StackUtility.unsafeStack(tile.getStackInSlot(prefSlot), stack));
            return true;
        }
        return false;
    }


}
