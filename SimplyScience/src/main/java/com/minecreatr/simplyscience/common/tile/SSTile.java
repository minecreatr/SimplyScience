package com.minecreatr.simplyscience.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Basic TileEntity for the SimplyScience mod
 *
 * @author minecreatr
 */
public abstract class SSTile extends TileEntity {


    protected String name;

    public SSTile(String name){
        this.name = name;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        writeData(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        readData(compound);
    }

    protected abstract void writeData(NBTTagCompound compound);

    protected abstract void readData(NBTTagCompound compound);

    /**
     * Write data that that is needed by the client
     * This data should also be included in the generic writeData
     */
    protected abstract void writeClientData(NBTTagCompound compound);

    /**
     * Read data that is needed by the client
     * This data should also be included in the generic readData
     */
    protected abstract void readClientData(NBTTagCompound compound);

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeClientData(tagCompound);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }
}
