package com.minecreatr.machinelib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidTank;

/**
 * Should be implemented by machine tile entities
 *
 * This is so that actions can effect them
 *
 * @author minecreatr
 */
public interface IMachineTile{

    /**
     * Gets the amount of power stored in this machine
     */
    int getPower();

    /**
     * Gets the maximum amount of power able to be stored in this machine
     */
    int getMaxPower();

    /**
     * Set the power in this machine
     */
    void setPower(int value);

    /**
     * Decrease the power in this machine by the specified value
     * @return If It was able to decrement the power
     */
    boolean decPower(int amount);

    /**
     * Increase the power in this machine by the specified value
     * @return Whether it was able to increase the power in this machine
     */
    boolean incrPower(int amount);

    /**
     * Gets the machine handler
     * @return The Machine Handler for this machine
     */
    IMachineHandler getHandler();

    ItemStack[] getInventory();

    void setStackInSlot(int slot, ItemStack stack);

    ItemStack getStackInSlot(int slot);

    FluidTank getTank(int id);


}
