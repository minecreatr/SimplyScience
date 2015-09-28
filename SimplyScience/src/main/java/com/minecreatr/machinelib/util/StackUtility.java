package com.minecreatr.machinelib.util;

import net.minecraft.item.ItemStack;

/**
 * ItemStack Utility class for MachineLib
 *
 * @author minecreatr
 */
public class StackUtility {

    public static boolean canStack(ItemStack stack1, ItemStack stack2){
        if (stack1 == null && stack2 == null){
            return true;
        }
        else if (stack1 == null || stack2 == null){
            return false;
        }
        if (stack1.getItem() == stack2.getItem() && stack1.getItemDamage() == stack2.getItemDamage() && stack1.getTagCompound().equals(stack2.getTagCompound())){
            return (stack1.stackSize + stack2.stackSize) < stack1.getItem().getItemStackLimit(stack1);
        }
        return false;
    }

    /**
     * Stacks the 2 ItemStacks without checking if they can stack
     * @param stack1 A Stack
     * @param stack2 Another stack
     */
    public static ItemStack unsafeStack(ItemStack stack1, ItemStack stack2){
        stack1.stackSize+=stack2.stackSize;
        return stack1;
    }

    /**
     * Staccks the ItemStacks
     * @param stack1 A Stack
     * @param stack2 Another Stack
     * @return Whether it was succesfully able to stack the ItemStacks
     */
    public static boolean stack(ItemStack stack1, ItemStack stack2){
        if (canStack(stack1, stack2)){
            unsafeStack(stack1, stack2);
            return true;
        }
        else {
            return false;
        }
    }
}
