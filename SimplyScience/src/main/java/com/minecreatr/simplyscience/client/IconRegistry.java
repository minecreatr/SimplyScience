package com.minecreatr.simplyscience.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gnu.trove.map.TMap;
import gnu.trove.map.hash.THashMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores icons by a string name
 *
 * @author minecreatr
 */
public class IconRegistry {

    private static TMap<String, IIcon> iconMap;

    private static Map<String, String> unregisteredIconMap = new HashMap<String, String>();

    static class IconRegistryEventListener {
        @SubscribeEvent
        public void onTextureStitch(TextureStitchEvent.Pre event) {
            if (event.map.getTextureType() != 0) {
                iconMap = new THashMap<String, IIcon>();
                for (String key : unregisteredIconMap.keySet()) {
                    iconMap.put(key, event.map.registerIcon(unregisteredIconMap.get(key)));
                }
            }
        }
    }



    /**
     * Registers an Icon
     * @param iconName The Name of the icon for getting it
     * @param iconPath The Path to the icon in the form MODID:path/inside/textures
     */
    public static void register(String iconName, String iconPath){
        unregisteredIconMap.put(iconName, iconPath);
    }

    /**
     * Registers an icon
     * @param iconName The Name of the icon for getting it
     * @param iconPath The Path to the icon in the form path/inside/textures
     * @param modId The MODID of the mod that has this icon
     */
    public static void register(String iconName, String iconPath, String modId){
        register(iconName, modId+":"+iconPath);
    }
}
