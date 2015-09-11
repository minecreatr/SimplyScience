package com.minecreatr.simplyscience.common.block;

import cofh.lib.util.helpers.ItemHelper;
import com.minecreatr.simplyscience.Reference;
import com.minecreatr.simplyscience.common.item.ItemBlockMeta;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StringUtils;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

/**
 * Simple Metadata block, this is meant for NON tileentities only. Such as ores or decorative blocks
 *
 * @author minecreatr
 */
public abstract class SimpleMetadataBlock extends SSBlock implements IMetadataBlock , Reference{

    private MetadataVariation[] variations;
    private IIcon[] icons;

    public SimpleMetadataBlock(String name, Material material) {
        super(name, material, ItemBlockMeta.class);
        variations = getVariations();
        for (int i = 0; i < variations.length; i++) {
            if (StringUtils.isNullOrEmpty(variations[i].oreDictName)){
                continue;
            }
            ItemStack stack = new ItemStack(this, 1, i);
            ItemHelper.registerWithHandlers(variations[i].oreDictName, stack);
            setHarvestLevel(variations[i].harvestTool, variations[i].harvestLevel, i);
        }
    }


    /**
     * Return an array of the different metadata variations for this block
     * @return A List of metadata variations
     */
    protected abstract MetadataVariation[] getVariations();

    @Override
    public String getVariantName(int meta){
        return getVariation(meta).name;
    }

    @Override
    public String[] getVariants(){
        String[] names = new String[variations.length];
        for (int i = 0 ; i < variations.length ; i++){
            names[i] = variations[i].name;
        }
        return names;
    }

    @Override
    public String getOredictName(int meta){
        return getVariation(meta).oreDictName;
    }

    @Override
    public String getHarvestTool(int meta){
        return getVariation(meta).harvestTool;
    }

    @Override
    public int getHarvestLevel(int meta){
        return getVariation(meta).harvestLevel;
    }

    @Override
    public int getLightLevel(int meta){
        return getVariation(meta).lightLevel;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        icons = new IIcon[variations.length];
        for (int i = 0 ; i < variations.length ; i++){
            icons[i] = ir.registerIcon(MODID+":"+getName()+"/"+variations[i].name);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return icons[metadata];
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return getLightLevel(world.getBlockMetadata(x, y, z));
    }

    @Override
    public int damageDropped(int i) {
        return i;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0 ; i < variations.length ; i++){
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public float getHardness(int meta){
        return getVariation(meta).hardness;
    }

    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        return getHardness(world.getBlockMetadata(x, y, z));
    }

    private MetadataVariation getVariation(int meta){
        try {
            return variations[meta];
        } catch (Exception exception){
            return variations[0];
        }
    }



    public static class MetadataVariation {

        private String name;
        private String oreDictName;
        private String harvestTool;
        private int harvestLevel;
        private int lightLevel;
        private float hardness;

        public MetadataVariation(String name, String oreDictName, String harvestTool, int harvestLevel, int lightLevel, float hardness){
            this.name = name;
            this.oreDictName = oreDictName;
            this.harvestTool = harvestTool;
            this.harvestLevel = harvestLevel;
            this.lightLevel = lightLevel;
            this.hardness = hardness;
        }

    }
}
