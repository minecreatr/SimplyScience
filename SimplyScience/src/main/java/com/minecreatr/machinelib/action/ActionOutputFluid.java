package com.minecreatr.machinelib.action;

import com.minecreatr.machinelib.IMachineTile;
import com.minecreatr.machinelib.util.FluidUtility;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

/**
 * Action for outputting fluid
 *
 * @author minecreatr
 */
public class ActionOutputFluid implements IAction {

    private FluidStack stack;

    private int tankID;

    public ActionOutputFluid(FluidStack stack, int tankID){
        this.stack = stack;
        this.tankID = tankID;
    }

    public ActionOutputFluid(FluidStack stack){
        this(stack, -1);
    }

    public boolean canExecute(IMachineTile tile){
        if (tankID < 0) {
            int[] tanks = tile.getHandler().getOutputTanks();
            for (int id : tanks) {
                if (FluidUtility.canInsertFluid(stack, tile.getTank(id))) {
                    return true;
                }
            }
        }
        else if (tankID < tile.getHandler().getFluidTanks().length){
            return tile.getHandler().getFluidTanks()[tankID].fluid.isFluidEqual(stack) && FluidUtility.canInsertFluid(stack, tile.getTank(tankID));
        }
        return false;
    }

    public boolean execute(IMachineTile tile){
        if (tankID < 0) {
            int[] tanks = tile.getHandler().getOutputTanks();
            for (int id : tanks) {
                return tile.getTank(id).fill(stack, true) == stack.amount;
            }
        }
        else if (tankID < tile.getHandler().getFluidTanks().length){
            return tile.getTank(tankID).fill(stack, true) == stack.amount;
        }
        return false;
    }
}
