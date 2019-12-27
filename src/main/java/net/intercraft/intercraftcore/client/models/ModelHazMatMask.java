package net.intercraft.intercraftcore.client.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelHazMatMask extends EntityModel
{

    public final RendererModel bb_main, air_filter;
    public static final ModelHazMatMask modelMask = new ModelHazMatMask();

    public ModelHazMatMask()
    {
        textureWidth = 50;
        textureHeight = 16;

        bb_main = new RendererModel(this);
        bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 10, 0, -4.0F, -8.0F, -3.9F, 8, 8, 8, 0.0F, false));
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -7.0F, -4.9F, 8, 7, 1, 0.0F, false));

        air_filter = new RendererModel(this);
        air_filter.setRotationPoint(0.0F, 24.0F, 0.0F);
        air_filter.cubeList.add(new ModelBox(air_filter, 0, 8, -1.0F, -1.9292F, -6.4409F, 2, 3, 3, 0.0F, false));
        air_filter.cubeList.add(new ModelBox(air_filter, 34, 4, -3.0F, 1.0708F, -6.4409F, 6, 2, 2, 0.0F, false));
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        bb_main.render(scale + 0.001f);
        air_filter.rotateAngleX += -0.4363F;
        air_filter.render(scale);
    }
}
