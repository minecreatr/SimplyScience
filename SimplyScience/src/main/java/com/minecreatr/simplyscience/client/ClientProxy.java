package com.minecreatr.simplyscience.client;

import com.minecreatr.simplyscience.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;

/**
 * The Clientside proxy
 *
 * @author minecreatr
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void preInit(){
        MinecraftForge.EVENT_BUS.register(new IconRegistry.IconRegistryEventListener());
    }
}
