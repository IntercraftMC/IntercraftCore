package net.intercraft.intercraftcore.client.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class ModelHazMatSuit extends BipedModel<LivingEntity>
{
    public ModelHazMatSuit(int tint)
    {
        textureWidth = 76;
        textureHeight = 37;

        bipedHead = new RendererModel(this);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 0, -5.0F, -9.0F, -5.0F, 10, 9, 10, 0.0F, false));
        bipedHead.cubeList.add(new ModelBox(bipedHead, 30, 0, -4.0F, -8.0F, -5.0F, 8, 8, 1, 0.0F, false));

        bipedRightLeg = new RendererModel(this);
        bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 19, -2.75F, 0.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedLeftLeg = new RendererModel(this);
        bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 0, 19, -2.25F, 0.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedRightArm = new RendererModel(this);
        bipedRightArm.setRotationPoint(-7.0F, 0, 0);
        bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 22, 19, -3.0F, 0.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedLeftArm = new RendererModel(this);
        bipedLeftArm.setRotationPoint(7.0F, 0, 0);
        bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 22, 19, -2.0F, 0.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedBody = new RendererModel(this);
        bipedBody.setRotationPoint(0.0F, 6.0F, 0.0F);
        bipedBody.cubeList.add(new ModelBox(bipedBody, 44, 19, -5.0F, -6.0F, -3.0F, 10, 12, 6, 0.0F, false));
    }
    

    /*public void setRotationAngle(RendererModel RendererModel, float x, float y, float z)
    {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }*/
}
