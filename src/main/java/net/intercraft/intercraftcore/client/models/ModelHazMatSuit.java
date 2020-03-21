package net.intercraft.intercraftcore.client.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class ModelHazMatSuit extends BipedModel<LivingEntity>
{

    //private final RendererModel bipedHead, bipedHead, bipedLeftArm, bipedRightArm, bipedLeftLeg, bipedRightLeg;

    public ModelHazMatSuit(int tint)
    {
        textureWidth = 72;
        textureHeight = 37;

        bipedHead = new RendererModel(this);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 0, -5.0F, -9.0F, -5.0F, 10, 9, 10, 0.0F, false));
        bipedHead.cubeList.add(new ModelBox(bipedHead, 44, 18, -4.0F, -8.0F, -5.0F, 8, 8, 1, 0.0F, false));

        bipedRightLeg = new RendererModel(this);
        bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 19, -3.0F, 0.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedLeftLeg = new RendererModel(this);
        bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 0, 19, -2.0F, 0.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedRightArm = new RendererModel(this);
        bipedRightArm.setRotationPoint(-7.0F, 1.0F, 0.0F);
        bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 22, 19, -3.0F, -1.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedLeftArm = new RendererModel(this);
        bipedLeftArm.setRotationPoint(7.0F, 1.0F, 0.0F);
        bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 22, 19, -2.0F, -1.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedBody = new RendererModel(this);
        bipedBody.setRotationPoint(0.0F, 6.0F, 0.0F);
        bipedBody.cubeList.add(new ModelBox(bipedBody, 40, 0, -5.0F, -6.0F, -3.0F, 10, 12, 6, 0.0F, false));
    }

    /*@Override
    public void render(PlayerEntity playerEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float bipedHeadPitch, float scale)
    {
        bipedHead.render(scale);
        bipedBody.render(scale);
    }*/

    /*public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }*/
}
