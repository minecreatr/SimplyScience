package com.minecreatr.machinelib.action;

import com.minecreatr.machinelib.IMachineTile;
import com.minecreatr.machinelib.util.FluidUtility;
import net.minecraftforge.fluids.FluidStack;

/**
 * Action for using a fluid
 *
 * @author minecreatr
 */
public class ActionInputFluid implements IAction{

    private int tankId;

    private FluidStack stack;

    public ActionInputFluid(FluidStack stack, int tankId){
        this.stack = stack;
        this.tankId = tankId;
    }

    public boolean canExecute(IMachineTile tile){
        return tankId < tile.getHandler().getFluidTanks().length && FluidUtility.canPullFluid(stack, tile.getTank(tankId));
    }

    public boolean execute(IMachineTile tile){
        if (tankId < tile.getHandler().getFluidTanks().length && FluidUtility.canPullFluid(stack, tile.getTank(tankId))){
            return tile.getTank(tankId).drain(stack.amount, true).amount == stack.amount;
        }
        else {
            return false;
        }
    }
}
