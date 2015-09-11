package com.minecreatr.simplyscience.common.item;

import com.minecreatr.simplyscience.SimplyScience;
import com.minecreatr.simplyscience.common.init.BlocksInit;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;

/**
 * Item for grapes
 *
 * @author minecreatr
 */
public class ItemGrape extends ItemSeedFood{


    public ItemGrape(){
        super(4, 0.6f, BlocksInit.getBlock("blockGrapes"), Blocks.farmland);
        GameRegistry.registerItem(this, "grapes");
        setUnlocalizedName("grapes");
        setTextureName("grapes");
        setCreativeTab(SimplyScience.tab);
    }
}
