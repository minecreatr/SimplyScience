package com.minecreatr.simplyscience;

import com.enderio.core.IEnderMod;
import com.minecreatr.simplyscience.common.CommonProxy;
import com.minecreatr.simplyscience.common.SSBlocks;
import com.minecreatr.simplyscience.common.init.BlocksInit;
import com.minecreatr.simplyscience.common.init.ItemsInit;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * SimplyScience mod for minecraft
 * List of things to add:
 * - Chemistry
 * - Virus/Desease
 *
 *
 * - DNA
 * - Physics/Particle accelerators
 * - FUN STUFF
 *
 * @author minecreatr
 */
@Optional.Interface(iface = "com.enderio.core.IEnderMod", modid = "endercore")
@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class SimplyScience implements IEnderMod, Reference{

    public static final CreativeTabs tab = new CreativeTabs("tabSimplyScience") {
        @Override
        public Item getTabIconItem() {
            return null;
        }
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack(){
            return new ItemStack(SSBlocks.ssOre);
        }
    };


    @Mod.Instance(MODID)
    public static SimplyScience instance;

    @SidedProxy(serverSide = "com.minecreatr.simplyscience.CommonProxy", clientSide = "com.minecreatr.simplyscience.client.ClientProxy", modId = MODID)
    public static CommonProxy proxy;


    @Override
    public String modid(){
        return MODID;
    }

    @Override
    public String name(){
        return MODNAME;
    }

    @Override
    public String version(){
        return VERSION;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        BlocksInit.createBlockMap();
        ItemsInit.init();

        proxy.preInit();
    }
}
