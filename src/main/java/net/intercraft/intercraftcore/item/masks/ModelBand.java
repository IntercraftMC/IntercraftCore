package net.intercraft.intercraftcore.item.masks;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;

public class ModelBand extends EntityModel
{

    public RendererModel wrist;

    public static final ModelBand modelClock = new ModelBand();

    public ModelBand()
    {
        this.textureWidth = 16;
        this.textureHeight = 8;

        this.wrist = new RendererModel(this,0,0);
        this.wrist.setRotationPoint(0,0,0);
        this.wrist.addBox(-1.0F, 5.0F, -2.0F, 4, 4, 4, 0.1F);

    }


    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.wrist.render(scale);
    }
}
