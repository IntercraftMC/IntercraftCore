package net.intercraft.intercraftcore.tileentity;

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

    private Item[] plates;

    public CableCaseTileEntity()
    {
        super(IntercraftTileEntities.CABLECASE);
        plates = new Item[6];
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

    public void setPlate(@Nullable Item plate, byte side)
    {
        plates[side] = plate;
    }

    private CompoundNBT writePlates(CompoundNBT compound)
    {
        ListNBT listNBT = new ListNBT();
        for (byte i=0;i<plates.length;i++) {
            CompoundNBT nbt = new CompoundNBT();
            if (plates[i] != null)
                nbt.putString("plate",plates[i].getRegistryName().toString());
            else
                nbt.putString("plate","null");
            listNBT.add(i,nbt);

        }
        compound.put("plates",listNBT);
        return compound;
    }

    private void readPlates(CompoundNBT compound)
    {
        ListNBT listNBT = (ListNBT) compound.get("plates");
        for (byte i=0;i<plates.length;i++) {
            String s = listNBT.getCompound(i).getString("plate");
            ResourceLocation rs = new ResourceLocation(s);
            Item item = !(s.equals("null")) ? ForgeRegistries.ITEMS.getValue(rs) : null;
            if (item == Items.AIR) item = ForgeRegistries.BLOCKS.getValue(rs).asItem();
            plates[i] = item;

        }
    }


    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        super.onDataPacket(net, pkt);
        CompoundNBT nbt = pkt.getNbtCompound();
        readPlates(nbt);
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT compound = super.getUpdateTag();
        compound = writePlates(compound);

        return compound;
    }


    @Override
    public void handleUpdateTag(CompoundNBT tag)
    {
        readPlates(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        compound = writePlates(compound);
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        readPlates(compound);

    }

    @Override
    public boolean hasFastRenderer()
    {
        return true;
    }
}