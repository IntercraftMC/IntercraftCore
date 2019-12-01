package net.intercraft.intercraftcore.inventory.container;

import net.intercraft.intercraftcore.init.IntercraftContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;

public class ContainerChunkLoaderTimer extends Container
{

    private long duration;
    private int seconds, minutes, hours, days;


    /*private final IInventory inventory = new Inventory() {

    };*/

    public ContainerChunkLoaderTimer(int id, PlayerInventory playerInventory, PacketBuffer data) //, IWorldPosCallable posCallable
    {
        super(IntercraftContainerTypes.CHUNKLOADER_TIMER_INTERFACE, id);

        /*duration = data.getLong(0);
        seconds  = data.getInt(1);
        minutes  = data.getInt(2);
        hours    = data.getInt(3);
        days     = data.getInt(4);*/


        bindPlayerInventory(playerInventory);

        //addButtons(this.inventory);


    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }


    private void addButtons(IInventory inventory)
    {

    }

    private void bindPlayerInventory(PlayerInventory player)
    {
        for (int iy = 0; iy < 3; iy++) {
            for (int ix = 0; ix < PlayerInventory.getHotbarSize(); ix++) {
                addSlot(new Slot(player, ix + iy * 9 + PlayerInventory.getHotbarSize(), 8 + ix * 18, 84 + iy * 18));
            }
        }

        for (int ix = 0; ix < PlayerInventory.getHotbarSize(); ix++) {
            addSlot(new Slot(player, ix, 8 + ix * 18, 142));
        }
    }

}
