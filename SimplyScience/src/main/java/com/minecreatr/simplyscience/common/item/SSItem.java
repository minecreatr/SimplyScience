package com.minecreatr.simplyscience.common.item;

import com.minecreatr.simplyscience.SimplyScience;
import net.minecraft.item.Item;

/**
 * Basic Item for the SimplyScience mod
 *
 * @author minecreatr
 */
public abstract class SSItem extends Item{

    public SSItem(String name){
        setUnlocalizedName(name);
        setTextureName(name);
        setCreativeTab(SimplyScience.tab);
    }
}
