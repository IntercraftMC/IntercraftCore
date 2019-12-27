package net.intercraft.intercraftcore.item.masks;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.client.models.ModelHazMatMask;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.curios.api.capability.ICurio;

public class ItemHazMatMask extends ItemMask
{
    public ItemHazMatMask()
    {
        super("hazmat_mask",new ResourceLocation(Reference.MODID,"textures/masks/hazmat_mask.png"));
    }

    @Override
    protected boolean hidesIdentity()
    {
        return false;
    }

    @Override
    protected void doRender(String identifier, LivingEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        //super.doRender(identifier, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        Minecraft.getInstance().getTextureManager().bindTexture(texture);

        ICurio.RenderHelper.followHeadRotations(entitylivingbaseIn, ModelHazMatMask.modelMask.bb_main);
        ICurio.RenderHelper.followHeadRotations(entitylivingbaseIn, ModelHazMatMask.modelMask.air_filter);

        ModelHazMatMask.modelMask.render(entitylivingbaseIn,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scale);
    }
}
