package net.intercraft.intercraftcore.tileentity;

import net.intercraft.intercraftcore.api.UtilBlocks;
import net.intercraft.intercraftcore.api.enumProperties.CableCaseFaces;
import net.intercraft.intercraftcore.block.cablecase.BlockCableCase;
import net.intercraft.intercraftcore.init.IntercraftTileEntities;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CableCaseTileEntity extends TileEntity
{
    private List<CableCaseWire> wires;
    private static final byte maxCap = 32;

    private final Item[] plates, modules;

    public CableCaseTileEntity()
    {
        super(IntercraftTileEntities.CABLECASE);
        plates = new Item[6];
        modules = new Item[6];
        wires = new ArrayList<>();
    }

    public byte getMaxCapacity()
    {
        return maxCap;
    }

    public byte getCapacity()
    {
        byte a=0;for(byte i=0;i<wires.size();i++)a+=wires.get(i).getCapacity();return a;
    }

    public Item getPlate(byte side)
    {
        return plates[side];
    }

    public Item[] getPlates()
    {
        return plates;
    }

    public Item getModule(byte side)
    {
        return modules[side];
    }

    public Item[] getModules()
    {
        return modules;
    }

    public void setPlate(@Nullable Item plate, byte side, boolean sendMessage)
    {
        plates[side] = plate;
    }

    public void setPlate(@Nullable Item plate, byte side)
    {
        setPlate(plate,side,false);
    }

    public void setModule(@Nullable Item module, byte side, boolean sendMessage)
    {
        getBlockState().with(BlockCableCase.getPropertyFromConnection(UtilBlocks.Connections.getConnectionFromValue(side)), module == null ? CableCaseFaces.NONE : CableCaseFaces.MODULE);
        modules[side] = module;
    }

    public void setModule(@Nullable Item module, byte side)
    {
        setModule(module,side,false);
    }

    private CompoundNBT writeSides(CompoundNBT compound, Item[] array, String prefix)
    {
        ListNBT listNBT = new ListNBT();
        for (byte i=0;i<array.length;i++) {
            CompoundNBT nbt = new CompoundNBT();
            if (array[i] != null)
                nbt.putString(prefix,array[i].getRegistryName().toString());
            else
                nbt.putString(prefix,"null");
            listNBT.add(i,nbt);

        }
        compound.put(prefix+"s",listNBT);
        return compound;
    }

    private void readSides(CompoundNBT compound, Item[] array, String prefix)
    {
        ListNBT listNBT = (ListNBT) compound.get(prefix+"s");
        for (byte i=0;i<array.length;i++) {
            String s = listNBT.getCompound(i).getString(prefix);
            ResourceLocation rs = new ResourceLocation(s);
            Item item = !(s.equals("null")) ? ForgeRegistries.ITEMS.getValue(rs) : null;
            if (item == Items.AIR) item = ForgeRegistries.BLOCKS.getValue(rs).asItem();
            array[i] = item;

        }
    }


    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        super.onDataPacket(net, pkt);
        CompoundNBT nbt = pkt.getNbtCompound();
        readSides(nbt,plates,"plate");
        readSides(nbt,modules,"module");
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound = writeSides(compound,plates,"plate");
        compound = writeSides(compound,modules,"module");

        return compound;
    }


    @Override
    public void handleUpdateTag(CompoundNBT tag)
    {
        readSides(tag,plates,"plate");
        readSides(tag,modules,"module");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound = writeSides(compound,plates,"plate");
        compound = writeSides(compound,modules,"module");
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        readSides(compound,plates,"plate");
        readSides(compound,modules,"module");

    }

    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }
}