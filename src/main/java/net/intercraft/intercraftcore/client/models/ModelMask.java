package net.intercraft.intercraftcore.client.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelMask extends EntityModel
{

    public final RendererModel mask;

    public static final ModelMask modelMask = new ModelMask();

    public ModelMask()
    {
        this.textureWidth = 34;
        this.textureHeight = 17;

        this.mask = new RendererModel(this);
        this.mask.setRotationPoint(0,0,0);
        this.mask.addBox(-4.0F, -8.0F, -4.998F, 8, 8, 9, 0.001F);

    }


    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.mask.render(scale);
    }
}
