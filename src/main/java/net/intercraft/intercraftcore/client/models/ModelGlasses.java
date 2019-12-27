package net.intercraft.intercraftcore.client.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelGlasses extends EntityModel
{

    public RendererModel mask;

    public static final ModelGlasses modelGlasses = new ModelGlasses();

    public ModelGlasses()
    {
        this.textureWidth = 24;
        this.textureHeight = 16;

        this.mask = new RendererModel(this,0,0);
        this.mask.setRotationPoint(0,0,0);
        this.mask.addBox(-4.0F, -8.0F, -4.2F, 8, 8, 8, 0.01F);

    }


    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.mask.render(scale);
    }
}
