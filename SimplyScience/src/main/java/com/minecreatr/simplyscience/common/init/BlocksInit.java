package com.minecreatr.simplyscience.common.init;

import com.minecreatr.simplyscience.Reference;
import com.minecreatr.simplyscience.common.block.BlockGrapes;
import com.minecreatr.simplyscience.common.block.ISSBlock;
import com.minecreatr.simplyscience.common.block.SimpleMetadataBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Where all the blocks are made
 */
public class BlocksInit implements Reference{

    private static final List<ISSBlock> blocklist = new ArrayList<ISSBlock>(){{
        /**
         * All Blocks added by the mod are actually added here
         */
        add(new SimpleMetadataBlock("ssOre", Material.rock) {
            @Override
            protected MetadataVariation[] getVariations() {
                return new MetadataVariation[] {new MetadataVariation("uranium", "oreUranium", "pickaxe", 3, 1, 3f)};
            }
        });
        add(new BlockGrapes());
    }};

    private static Map<String, ISSBlock> blockMap;

    public static Block getBlock(String name){
        if (blockMap.containsKey(name)){
            return blockMap.get(name).getBlock();
        }
        else {
            return null;
        }
    }


    public static void createBlockMap(){
        if (blockMap!=null){
            return;
        }
        blockMap = new HashMap<String, ISSBlock>();
        for (ISSBlock block : blocklist){
            blockMap.put(block.getName(), block);
        }
    }


}
