package com.minecreatr.simplyscience.common.init;

import com.minecreatr.simplyscience.common.item.ItemGrape;

/**
 * Initializes the Items from the SimplyScience Mod
 *
 * @author minecreatr
 */
public class ItemsInit {

    public static ItemGrape grapes;


    public static void init(){
        grapes = new ItemGrape();
    }

}
