package net.intercraft.intercraftcore.inventory.container;

import net.intercraft.intercraftcore.init.IntercraftContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerItemItemStack extends Container
{


    private final PacketBuffer data;

    public ContainerItemItemStack(int id, PlayerInventory playerInventory, PacketBuffer data)
    {
        super(IntercraftContainerTypes.ITEMITEMSTACK_CONTAINER, id);
        this.data = data;

        ItemStack stack = playerInventory.player.getActiveItemStack();

        //addStorageSlots((short) 4);
        bindPlayerInventory(playerInventory);
    }

    public PacketBuffer getData()
    {
        return data;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }


    private void addStorageSlots(Inventory inventory, short slots)
    {
        final short middle = 8+3*18;
        for (short i=0;i<slots;i++) {
            addSlot(new Slot(inventory,i,middle+i*18,30));
        }
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
