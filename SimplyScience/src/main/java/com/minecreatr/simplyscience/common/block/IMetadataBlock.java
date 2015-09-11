package com.minecreatr.simplyscience.common.block;

/**
 * Interface for blocks that have different variations for different meta values
 *
 * @author minecreatr
 */
public interface IMetadataBlock {

    /**
     * Get the name of the variant for the specified metadata value
     * @param meta The Meta value
     * @return The Name of this variant
     */
    String getVariantName(int meta);

    /**
     * Get a list of all the variants of this block, with the index corresponding to the meta of the variant
     * @return A List of the variants of this block
     */
    String[] getVariants();

    /**
     * The OreDictionary name of this variant
     * @param meta The meta of this variant
     * @return The Ore Dictionary name of this variant
     */
    String getOredictName(int meta);

    /**
     * Gets the tool required to harvest this variant
     * @param meta The Meta of the variant
     * @return The Tool required
     */
    String getHarvestTool(int meta);

    /**
     * The Harvest level of the tool required
     * @param meta The meta of the variant
     * @return The Harvest Level
     */
    int getHarvestLevel(int meta);

    /**
     * Gets the light level for the specified variant
     * @param meta The Metadata value
     * @return THe Light level for the specified variation
     */
    int getLightLevel(int meta);

    /**
     * Get the hardness of the block from its meta
     * @param meta The Meta
     * @return The Hardness
     */
    float getHardness(int meta);


}
