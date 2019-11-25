package net.intercraft.intercraftcore.inventory.container;

import com.mojang.blaze3d.platform.GlStateManager;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ContainerScreenChunkloaderTimer extends ContainerScreen<ContainerChunkloaderTimer>
{

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID,"textures/gui/container/item_itemstack.png");

    public ContainerScreenChunkloaderTimer(ContainerChunkloaderTimer container, PlayerInventory inventory, ITextComponent text)
    {
        super(container,inventory,text);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 8.0F, 4.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 94), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        this.renderBackground();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        blit(k, l, 0, 0, this.xSize, this.ySize);


    }
}
