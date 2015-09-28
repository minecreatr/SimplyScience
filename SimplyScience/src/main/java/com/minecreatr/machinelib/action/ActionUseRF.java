package com.minecreatr.machinelib.action;

import com.minecreatr.machinelib.IMachineTile;

/**
 * Action for using RF
 *
 * @author minecreatr
 */
public class ActionUseRF implements IAction{

    private int amount;

    public ActionUseRF(int amount){
        this.amount = amount;
    }

    public boolean canExecute(IMachineTile tile){
        return 0 <= (tile.getPower() - amount);
    }

    public boolean execute(IMachineTile tile){
        return tile.decPower(amount);
    }
}
