package com.minecreatr.machinelib.action;

import com.minecreatr.machinelib.IMachineTile;
import net.minecraft.item.ItemStack;

/**
 * Action for using an ItemStack
 *
 * @author minecreatr
 */
public class ActionInputItem implements IAction{

    private int slot;
    private ItemStack stack;

    public ActionInputItem(ItemStack stack, int slot){
        this.stack = stack;
        this.slot = slot;
    }

    public boolean canExecute(IMachineTile tile){
        return slot < tile.getHandler().getTotalSlots() && tile.getStackInSlot(slot).isItemEqual(stack) && tile.getStackInSlot(slot).stackSize >= stack.stackSize;
    }

    public boolean execute(IMachineTile tile){
        if (tile.getStackInSlot(slot).isItemEqual(stack) && tile.getStackInSlot(slot).stackSize >= stack.stackSize){
            if (tile.getStackInSlot(slot).stackSize == stack.stackSize){
                tile.setStackInSlot(slot, null);
            }
            else {
                tile.setStackInSlot(slot, tile.getStackInSlot(slot).splitStack(stack.stackSize));
            }
            return true;
        }
        else {
            return false;
        }
    }
}
