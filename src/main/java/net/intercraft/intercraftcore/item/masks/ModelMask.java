package net.intercraft.intercraftcore.item.masks;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelMask extends EntityModel
{

    public RendererModel mask;

    public static final ModelMask modelMask = new ModelMask();

    public ModelMask()
    {
        this.textureWidth = 32;
        this.textureHeight = 16;

        this.mask = new RendererModel(this,0,0);
        this.mask.setRotationPoint(0,0,0);
        this.mask.addBox(-4.0F, -8.0F, -4F, 8, 8, 8, 0.0F);

    }


    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.mask.render(scale);
    }
}