package com.minecreatr.machinelib.util;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

/**
 * Fluid Utility for machine lib
 *
 * @author minecreatr
 */
public class FluidUtility {


    /**
     * Whether the specified FluidStack can be inserted into the specified tank
     * @param stack The Fluid Stack
     * @param tank The Fluid Tank
     * @return Whether the FluidStack can be inserted into the tank
     */
    public static boolean canInsertFluid(FluidStack stack, FluidTank tank){
        return isStackEmpty(tank.getFluid()) || (stack.isFluidEqual(tank.getFluid()) && (stack.amount+tank.getFluidAmount()) <= tank.getCapacity());
    }

    /**
     * Whether the following fluid stack can be pulled from the tank
     * @param stack The FluidStack
     * @param tank The Tank
     * @return Whether the following fluid stack can be pulled from the tank
     */
    public static boolean canPullFluid(FluidStack stack, FluidTank tank){
        return isStackEmpty(stack) || (stack.isFluidEqual(tank.getFluid()) && stack.amount <= tank.getFluidAmount());
    }

    /**
     * Returns whether the fluid stack has any fluid in it
     * @param stack The Fluid Stack
     * @return Whether it has any fluid in it
     */
    public static boolean isStackEmpty(FluidStack stack){
        return stack == null || stack.amount <= 0;
    }
}
