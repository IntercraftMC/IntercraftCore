package net.intercraft.intercraftcore.item.masks;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.client.models.ModelHazMatMask;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHazMatMask extends ItemMask
{
    public ItemHazMatMask()
    {
        super("hazmat_mask",new ResourceLocation(Reference.MODID,"textures/masks/hazmat_mask.png"));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip.intercraftcore.easter_egg.hazmat_mask"));
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
