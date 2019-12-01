package net.intercraft.intercraftcore.inventory.container;

import net.intercraft.intercraftcore.init.IntercraftContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class ContainerSingleItemStackContainer extends Container
{


    private IInventory itemStorageInventory;
    public short slotX = 8+4*18, slotY = 20;
    public final short stuckHotbarSlot;
    //private final PacketBuffer data;


    public ContainerSingleItemStackContainer(int id, PlayerInventory playerInventory, PacketBuffer data)
    {
        super(IntercraftContainerTypes.ITEMITEMSTACK_CONTAINER, id);
        //this.data = data;

        stuckHotbarSlot = (short) playerInventory.currentItem;

        //int tint = data.getInt(0);

        ItemStack itemStorage = playerInventory.getCurrentItem();
        //ItemStack itemStorage = ((PlayerEntity) playerInventory.player.world.getEntityByID(data.getInt(0))).getActiveItemStack();

        itemStorageInventory = new ContainerSingleItemStackInventory(itemStorage);

        addSlot(new Slot(itemStorageInventory,0,slotX,slotY));

        bindPlayerInventory(playerInventory);
    }


    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }


    private void bindPlayerInventory(PlayerInventory player)
    {
        for (int iy = 0; iy < 3; iy++) {
            for (int ix = 0; ix < PlayerInventory.getHotbarSize(); ix++) {
                addSlot(new Slot(player, ix + iy * 9 + PlayerInventory.getHotbarSize(), 8 + ix * 18, 84 + iy * 18));
            }
        }

        for (int ix = 0; ix < PlayerInventory.getHotbarSize(); ix++) {
            if (ix == stuckHotbarSlot)
                addSlot(new StuckSlot(player, ix, 8 + ix * 18, 142));
            else
                addSlot(new Slot(player, ix, 8 + ix * 18, 142));
        }
    }

    private static class StuckSlot extends Slot
    {
        //private static final ResourceLocation SLOT_DISABLED_TEXTURE = new ResourceLocation("minecraft","textures/item/barrier.png");
        private final Item item;

        public StuckSlot(IInventory inventoryIn, int index, int xPosition, int yPosition)
        {
            super(inventoryIn,index,xPosition,yPosition);
            item = getStack().getItem();
            //Minecraft.getInstance().getTextureManager().bindTexture(SLOT_DISABLED_TEXTURE);
        }



        @Override
        public boolean canTakeStack(PlayerEntity playerIn)
        {
            return false;
        }
    }
}
