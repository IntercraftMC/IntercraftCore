package net.intercraft.intercraftcore.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.intercraft.intercraftcore.item.ItemSingleStackContainer;
import net.intercraft.intercraftcore.item.ItemSingleStackGlassContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SingleStackGlassContainerItemRender extends ItemStackTileEntityRenderer
{
    public static final SingleStackGlassContainerItemRender INSTANCE = new SingleStackGlassContainerItemRender();

    @Override
    public void renderByItem(ItemStack itemStackIn)
    {
        System.out.println("Rendering it I guess.");

        //ItemSingleStackGlassContainer container = (ItemSingleStackGlassContainer) itemStackIn.getItem();
        if (itemStackIn.getItem() instanceof ItemSingleStackGlassContainer) {

            //ItemStack itemStack = ItemSingleStackContainer.getContainedItemStack(itemStackIn);


            if (/*itemStack != ItemStack.EMPTY*/false) {
                GlStateManager.pushMatrix();



                //Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.FIXED);


                /*Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND);
                Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND);
                Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
                Minecraft.getInstance().getItemRenderer().renderItem(itemStack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND);*/
                GlStateManager.popMatrix();
            }
        }
    }
}
