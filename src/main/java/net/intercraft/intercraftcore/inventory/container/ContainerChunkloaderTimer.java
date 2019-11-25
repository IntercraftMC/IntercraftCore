package net.intercraft.intercraftcore.inventory.container;

import net.intercraft.intercraftcore.init.IntercraftContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

public class ContainerChunkloaderTimer extends Container
{


    public ContainerChunkloaderTimer(int id, PlayerInventory playerInventory, PacketBuffer data) //, IWorldPosCallable posCallable
    {
        super(IntercraftContainerTypes.CHUNKLOADER_TIMER_INTERFACE, id);




    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }
}
