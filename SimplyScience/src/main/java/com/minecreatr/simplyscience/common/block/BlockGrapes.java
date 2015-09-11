package com.minecreatr.simplyscience.common.block;

import com.minecreatr.simplyscience.common.init.ItemsInit;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * Crop for Block Grapes
 *
 * @author minecreatr
 */
public class BlockGrapes extends BlockCrops implements ISSBlock{

    private IIcon[] icons;

    public BlockGrapes(){
        super();
        setBlockName("blockGrapes");
        setBlockTextureName("blockGrapes");
        GameRegistry.registerBlock(this, "blockGrapes");
    }

    @Override
    public String getName(){
        return "blockGrapes";
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.icons = new IIcon[4];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }

    protected Item func_149866_i()
    {
        return ItemsInit.grapes;
    }

    protected Item func_149865_P()
    {
        return ItemsInit.grapes;
    }

    @Override
    public Block getBlock(){
        return this;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 7)
        {
            if (meta == 6)
            {
                meta = 5;
            }

            return this.icons[meta >> 1];
        }
        else
        {
            return this.icons[3];
        }
    }
}
