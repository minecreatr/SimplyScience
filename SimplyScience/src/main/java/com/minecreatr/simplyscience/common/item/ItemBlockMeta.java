package com.minecreatr.simplyscience.common.item;

import com.minecreatr.simplyscience.Reference;
import com.minecreatr.simplyscience.common.block.SSBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * ItemBlock with metadata for the SimplyScience mod
 *
 * @author minecreatr
 */
public class ItemBlockMeta extends ItemBlock implements Reference{

    public ItemBlockMeta(Block block){
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    public int getMetadata(int meta){
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        if (Block.getBlockFromItem(stack.getItem())!=null && Block.getBlockFromItem(stack.getItem()) instanceof SSBlock){
            SSBlock block = (SSBlock) Block.getBlockFromItem(stack.getItem());
            return block.getName()+"."+block.getVariantName(stack.getItemDamage());
        }
        else {
            return super.getUnlocalizedName(stack);
        }
    }
}
