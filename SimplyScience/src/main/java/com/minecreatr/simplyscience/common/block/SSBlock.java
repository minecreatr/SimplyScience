package com.minecreatr.simplyscience.common.block;

import com.minecreatr.simplyscience.Reference;
import com.minecreatr.simplyscience.SimplyScience;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * Basic block for Simply Science
 *
 * @author minecreatr
 */
public abstract class SSBlock extends Block implements ISSBlock, Reference{

    private String name;

    public SSBlock(String name, Material material){
        super(material);
        this.name = name;
        setBlockName(name);
        setBlockTextureName(name);
        setCreativeTab(SimplyScience.tab);
        GameRegistry.registerBlock(this, name);
    }

    public SSBlock(String name, Material material, Class<? extends ItemBlock> itemBlock){
        super(material);
        this.name = name;
        setBlockName(name);
        setBlockTextureName(name);
        setCreativeTab(SimplyScience.tab);
        GameRegistry.registerBlock(this, itemBlock, name);
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public Block getBlock(){
        return this;
    }

    public abstract String getVariantName(int meta);

}
