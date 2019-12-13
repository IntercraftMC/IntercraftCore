package net.intercraft.intercraftcore.inventory.container;

import com.mojang.blaze3d.platform.GlStateManager;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ContainerScreenChunkLoaderTimer extends ContainerScreen<ContainerChunkLoaderTimer>
{

    private final int middleW = this.width / 2 ,middleH = this.height / 2;

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MODID,"textures/gui/container/chunkloader_timer.png");

    private TextFieldWidget seconds, minutes, hours, days;


    public ContainerScreenChunkLoaderTimer(ContainerChunkLoaderTimer container, PlayerInventory inventory, ITextComponent text)
    {
        super(container,inventory,text);
    }


    @Override
    public void tick()
    {
        super.tick();

        this.seconds.tick();
    }

    @Override
    protected void init()
    {
        super.init();

        this.seconds = new TextFieldWidget(this.font, middleW - 150,middleH-40,160,20,"Seconds");
        this.children.add(this.seconds);
    }

    @Override
    public void render(int i, int j, float k) {
        this.renderBackground();

        super.render(i, j, k);
        this.renderHoveredToolTip(i, j);

        seconds.render(i,j,k);


    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        /*this.font.drawString(this.title.getFormattedText(), 8.0F, 4.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 94), 4210752);*/


    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        //this.renderBackground();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);

        //TextFieldWidget widget = new TextFieldWidget(this.font,20,60,100,20,"HereBeText");

        this.addButton(new Button(40,20,40,20,"ffff",(button) -> {
            System.out.println("Umm, clicked?");
        }));

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        blit(k, l, 0, 0, this.xSize, this.ySize);


    }
}
