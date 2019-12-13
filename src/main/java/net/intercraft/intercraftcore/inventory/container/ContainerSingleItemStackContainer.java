package net.intercraft.intercraftcore.inventory.container;

import net.intercraft.intercraftcore.init.IntercraftContainerTypes;
import net.intercraft.intercraftcore.init.capabilities.stackContainer.StackContainerProvider;
import net.intercraft.intercraftcore.item.ItemSingleStackContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import top.theillusivec4.curios.api.inventory.CurioStackHandler;

import javax.annotation.Nonnull;

public class ContainerSingleItemStackContainer extends Container
{


    //private IInventory itemStorageInventory;
    private StackContainerProvider provider;
    public short slotX = 8+4*18, slotY = 33;
    public final short stuckHotbarSlot;
    private final ItemStack itemStorage;
    //private final PacketBuffer data;


    public ContainerSingleItemStackContainer(int id, PlayerInventory playerInventory, PacketBuffer data)
    {
        super(IntercraftContainerTypes.ITEMITEMSTACK_CONTAINER, id);
        //this.data = data;
        stuckHotbarSlot = (short) playerInventory.currentItem;

        //int tint = data.getInt(0);

        itemStorage = playerInventory.getCurrentItem();

        itemStorage.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> addSlot(new SlotItemHandlerContainer(handler,0,slotX,slotY)));

        //addSlot(new SlotItemHandler(((ItemSingleStackContainer) itemStorage.getItem()).getStackHandler(),0,slotX,slotY));

        //ItemStack itemStorage = ((PlayerEntity) playerInventory.player.world.getEntityByID(data.getInt(0))).getActiveItemStack();

        //itemStorageInventory = new ContainerSingleItemStackInventory(itemStorage);

        //addSlot(new Slot(handler,0,slotX,slotY));

        bindPlayerInventory(playerInventory);
    }


    @Override
    public void onContainerClosed(PlayerEntity playerIn)
    {
        super.onContainerClosed(playerIn);

        //System.out.println(handler.getStackInSlot(0).getDisplayName().getFormattedText());

        /*if (!playerIn.world.isRemote()) {
            IItemHandler handler = getHandler(itemStorage);

            ItemStack stack = Minecraft.getInstance().player.getActiveItemStack();
            IItemHandlerModifiable handler1 = (IItemHandlerModifiable) getHandler(stack);
            handler1.setStackInSlot(stuckHotbarSlot,handler.getStackInSlot(stuckHotbarSlot));


        }*/


        /*ItemStack containedStack = getHandler(itemStorage).getStackInSlot(0);
        if (playerIn.world.isRemote) {
            IItemHandler handler = getHandler(itemStorage);
            ((IItemHandlerModifiable) handler).setStackInSlot(0,containedStack);
        }*/

    }

    private static IItemHandler getHandler(ItemStack stack)
    {
        return stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 1) {
                if (!this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
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
                addSlot(new SlotStuck(player, ix, 8 + ix * 18, 142));
            else
                addSlot(new Slot(player, ix, 8 + ix * 18, 142));
        }
    }

    private static class SlotItemHandlerContainer extends SlotItemHandler
    {
        private SlotItemHandlerContainer(IItemHandler itemHandler, int index, int xPosition, int yPosition)
        {
            super(itemHandler,index,xPosition,yPosition);
        }

        @Override
        public boolean isItemValid(@Nonnull ItemStack stack)
        {
            //return stack.isEmpty() ? !(stack.getItem() instanceof ItemSingleStackContainer) : super.isItemValid(stack);
            if (stack.isEmpty() || stack.getItem() instanceof ItemSingleStackContainer)
                return false;
            return super.isItemValid(stack);
        }
    }

    private static class SlotStuck extends Slot
    {
        //private static final ResourceLocation SLOT_DISABLED_TEXTURE = new ResourceLocation("minecraft","textures/item/barrier.png");
        //private final Item item;

        private SlotStuck(IInventory inventoryIn, int index, int xPosition, int yPosition)
        {
            super(inventoryIn,index,xPosition,yPosition);
            //item = getStack().getItem();
            //Minecraft.getInstance().getTextureManager().bindTexture(SLOT_DISABLED_TEXTURE);
        }



        @Override
        public boolean canTakeStack(PlayerEntity playerIn)
        {
            return false;
        }
    }
}
