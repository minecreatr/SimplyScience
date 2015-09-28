package com.minecreatr.machinelib;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.inventory.IInventoryHandler;
import cofh.api.tileentity.IAugmentable;
import cofh.api.tileentity.IEnergyInfo;
import cofh.api.tileentity.IPortableData;
import cofh.api.tileentity.IReconfigurableFacing;
import cofh.api.tileentity.IReconfigurableSides;
import cofh.api.tileentity.IRedstoneControl;
import cofh.api.tileentity.ISecurable;
import cofh.api.tileentity.ISidedTexture;
import cofh.api.tileentity.ITileInfo;
import cofh.lib.audio.ISoundSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;

import java.util.List;

/**
 * Basic TileEntity for a single block
 *
 * @author minecreatr
 */
public class TileEntityMachine extends BasicTile implements ITileInfo, IPortableData, ISidedInventory,
        ISecurable, IRedstoneControl, ISoundSource, IEnergyReceiver, IReconfigurableFacing,
        IReconfigurableSides, ISidedTexture, IEnergyInfo, IInventoryHandler, IAugmentable {


    @Override
    public void getTileInfo(List<IChatComponent> info, ForgeDirection side, EntityPlayer player, boolean debug){
        info.add(new ChatComponentText(name));
        info.add(new ChatComponentText("RF: "+energy.getEnergyStored()+"/"+energy.getMaxEnergyStored()));
    }

    @Override
    public String getDataType(){
        return this.name;
    }

    @Override
    public void readPortableData(EntityPlayer player, NBTTagCompound tag){
        readData(tag);
    }

    @Override
    public void writePortableData(EntityPlayer player, NBTTagCompound tag){
        writeData(tag);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side){
        return inventorySections[getSideData(side).inventorySection];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side){
        SideData data = getSideData(side);
        for (int validSlot : inventorySections[data.inventorySection]){
            if (validSlot == slot){

            }
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack){

    }

    protected SideData getSideData(int side){
        if (side>=sideData.length){
            return noSideData;
        }
        else {
            return sideData[side];
        }
    }


    /**
     * The Name of this tile entity
     */
    protected String name;

    /**
     * The Progress of the current item
     */
    protected int currentProgress;

    /**
     * The EnergyStorage for this block
     */
    protected EnergyStorage energy;

    /**
     * The Direction that this machine is facing
     */
    protected ForgeDirection facing;

    /**
     * The Actual contents of this inventory
     */
    protected ItemStack[] inventory;

    /**
     * An Array of Arrays, each sub array is an array of index's which represent inventory section that can be used by a certain side
     */
    protected int[][] inventorySections;

    /**
     * An Array of all the fluid tanks
     */
    protected FluidTank[] fluidTanks;

    /**
     * The Base icon that this machine uses
     */
    protected String baseIcon;

    /**
     * An Array of SideData objects which store all the data about a specific side
     */
    protected SideData[] sideData = new SideData[6];

    /**
     * The Side data for an unknown side
     */
    protected SideData noSideData;

    /**
     * The Data about a specific side
     */
    public static class SideData {

        /**
         * The index of the tank this side is connected to
         */
        protected int tank;

        /**
         * The Index in inventorySections that this inventory section is
         */
        protected int inventorySection;

        /**
         * Whether this side is configurable. If False will use the texture in textureOverride
         */
        protected boolean isConfigurable;

        /**
         * The Type of connection this side has
         */
        protected SideConnectionType connectionType;

        /**
         * The Actual side this side data represents
         */
        protected ForgeDirection side;

        /**
         * The Name of the icon specific to this side, will be rendered on top of the baseIcon
         */
        protected String icon;

        /**
         * Constructor for SideData
         * @param type The Type of connection that this side has by default
         * @param side The ForgeDirection side that this side data represents
         * @param tank The id of the tank that this side is connected to
         * @param inventorySection The id of the inventory section that this side is connected to
         * @param icon The Icon to use for this side setting
         * @param isConfigurable Whether this side is configurable, if it is not configurable nothing here can change
         */
        public SideData(SideConnectionType type, ForgeDirection side, int tank, int inventorySection, String icon, boolean isConfigurable){
            this.connectionType = type;
            this.side = side;
            this.tank = tank;
            this.inventorySection = inventorySection;
            this.isConfigurable = isConfigurable;
        }

        public SideData(SideConnectionType type, ForgeDirection side, int tank, int inventorySection, String icon){
            this(type, side, tank, inventorySection, icon, true);
        }


        public enum SideConnectionType {
            INPUT_ITEMS(Direction.INPUT, ConnectionMaterial.ITEMS, 0),
            OUTPUT_ITEMS(Direction.OUTPUT, ConnectionMaterial.ITEMS, 1),
            INPUT_FLUIDS(Direction.INPUT, ConnectionMaterial.FLUIDS, 2),
            OUTPUT_FLUIDS(Direction.OUTPUT, ConnectionMaterial.FLUIDS, 3),
            ANY(Direction.NONE, ConnectionMaterial.NONE, 4),
            NONE(Direction.NONE, ConnectionMaterial.NONE, 5);

            private Direction direction;
            private ConnectionMaterial material;
            private int index;

            public static final SideConnectionType[] VALUES = {INPUT_ITEMS, OUTPUT_ITEMS, INPUT_FLUIDS,
                    OUTPUT_FLUIDS, ANY, NONE};

            SideConnectionType(Direction dir, ConnectionMaterial material, int index){
                this.direction = dir;
                this.material = material;
                this.index = index;
            }

            public Direction getDirection(){
                return this.direction;
            }

            public static SideConnectionType fromIndex(int index){
                return VALUES[index];
            }

            public SideConnectionType next(){
                return increment(1);
            }

            public SideConnectionType increment(int amount){
                int total = this.index + amount;
                if (total >= VALUES.length){
                    return fromIndex(total - VALUES.length);
                }
                else {
                    return fromIndex(total);
                }
            }

            public int getIndex(){
                return this.index;
            }

            public ConnectionMaterial getMaterial(){
                return this.material;
            }

            public enum Direction {
                OUTPUT,
                INPUT,
                NONE
            }

            public enum ConnectionMaterial {
                ITEMS("Items"),
                FLUIDS("Fluid"),
                NONE("None");

                private String name;

                ConnectionMaterial(String name){
                    this.name = name;
                }

                public String getName(){
                    return this.name;
                }
            }
        }
    }


}
