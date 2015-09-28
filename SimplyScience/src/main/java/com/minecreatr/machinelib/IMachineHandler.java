package com.minecreatr.machinelib;

import com.minecreatr.machinelib.action.ActionInputFluid;
import com.minecreatr.machinelib.action.ActionOutputFluid;
import com.minecreatr.machinelib.action.ActionOutputItem;
import com.minecreatr.machinelib.action.ActionUseRF;
import com.minecreatr.machinelib.action.IAction;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;

import java.util.List;

/**
 * Interface for handling certain things for a machine
 *
 * @author minecreatr
 */
public interface IMachineHandler {

    /**
     * Whether the item is valid in the specified slot.
     * @param stack The ItemStack
     * @param slot The Slot
     * @return Whether the ItemStack is valid in the specified slot
     */
    boolean isValid(ItemStack stack, int slot);

    /**
     * Get an Array of slot index's that are the input slots
     * @return The Array of slot index's for the input slot
     */
    int[] getInputSlots();

    /**
     * Get an array of slot index's that are for the output slots
     * @return The Array of slot index's for the output slots
     */
    int[] getOutputSlots();

    /**
     * The Total amount of slots this machine will have
     * This should include input and output as well as any uppgrade slots or anything else
     * @return The Total amount of slots this machine uses
     */
    int getTotalSlots();

    /**
     * Returns an array of fluid tank info. Order in this array equals the fluid tanks id
     * @return An Array of fluid tank info
     */
    FluidTankInfo[] getFluidTanks();

    int[] getOutputTanks();

    int[] getInputTanks();

    class MachineInput {

        private ItemStack[] inputStacks;

        private FluidTank[] inputFluids;

        public MachineInput(ItemStack[] inputStacks, FluidTank[] inputFluids){
            this.inputStacks = inputStacks;
            this.inputFluids = inputFluids;
        }

        public ItemStack[] getStacks(){
            return this.inputStacks;
        }

        public FluidTank[] getFluids(){
            return this.inputFluids;
        }
    }

    class MachineOutput {

        private List<IAction> actions;

        public MachineOutput(List<IAction> actions){
            this.actions = actions;
        }
    }

    class MachineOutputBuilder {

        private List<IAction> actions;

        public MachineOutputBuilder addItemOutput(ItemStack stack){
            actions.add(new ActionOutputItem(stack));
            return this;
        }

        public MachineOutputBuilder addItemOutput(ItemStack stack, int slot){
            actions.add(new ActionOutputItem(stack, slot));
            return this;
        }

        public MachineOutputBuilder addItemInput(ItemStack stack, int slot){
            actions.add(new ActionOutputItem(stack, slot));
            return this;
        }

        public MachineOutputBuilder addFluidOutput(FluidStack stack){
            actions.add(new ActionOutputFluid(stack));
            return this;
        }

        public MachineOutputBuilder addFluidOutput(FluidStack stack, int tankId){
            actions.add(new ActionOutputFluid(stack, tankId));
            return this;
        }

        public MachineOutputBuilder addFluidInput(FluidStack stack, int tankId){
            actions.add(new ActionInputFluid(stack, tankId));
            return this;
        }

        public MachineOutputBuilder addPowerDraw(int amount){
            actions.add(new ActionUseRF(amount));
            return this;
        }

        public MachineOutput build(){
            return new MachineOutput(this.actions);
        }
    }

}
