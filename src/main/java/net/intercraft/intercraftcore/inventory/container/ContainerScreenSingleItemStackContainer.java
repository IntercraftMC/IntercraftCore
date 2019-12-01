package net.intercraft.intercraftcore.inventory.container;

import com.mojang.blaze3d.platform.GlStateManager;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ContainerScreenSingleItemStackContainer extends ContainerScreen<ContainerSingleItemStackContainer>
{

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID,"textures/gui/container/item_itemstack.png");
    private static final ResourceLocation BACKGROUND_SLOT_TEXTURE = new ResourceLocation(Reference.MODID,"textures/gui/container/slot.png");
    private static final ResourceLocation SLOT_DISABLED_TEXTURE = new ResourceLocation("minecraft","textures/item/barrier.png");


    public ContainerScreenSingleItemStackContainer(ContainerSingleItemStackContainer container, PlayerInventory playerInventory, ITextComponent name)
    {
        super(container,playerInventory,name);
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_)
    {
        this.renderBackground();
        super.render(p_render_1_, p_render_2_, p_render_3_);
        this.renderHoveredToolTip(p_render_1_, p_render_2_);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items (apparently not))
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.font.drawString(this.title.getFormattedText(), 8.0F, 4.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 94), 4210752);

        this.minecraft.getTextureManager().bindTexture(BACKGROUND_SLOT_TEXTURE);

        //int k = (this.width - this.xSize) / 2, l = (this.height - this.ySize) / 2;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        blit(8 + container.stuckHotbarSlot * 18,142,0,0,16,16);

    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        //this.renderBackground();
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        //GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        int k = (this.width - this.xSize) / 2, l = (this.height - this.ySize) / 2;

        blit(k, l, 0, 0, this.xSize, this.ySize);

        //this.minecraft.getTextureManager().deleteTexture(BACKGROUND_TEXTURE);

        this.minecraft.getTextureManager().bindTexture(BACKGROUND_SLOT_TEXTURE);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        blit(k+container.slotX-1,l+container.slotY-1,0,0,18,18);



    }
}
