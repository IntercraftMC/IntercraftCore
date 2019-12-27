package net.intercraft.intercraftcore.client.models;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class ModelHazMatSuit extends BipedModel<LivingEntity>
{

    //public RendererModel model;
    //public final RendererModel bipedHead,bipedBody,bipedLeftArm,bipedRightArm,bipedLeftLeg,bipedRightLeg;

    public ModelHazMatSuit(int tint)
    {
        textureWidth = 56;
        textureHeight = 28;

        bipedHead = new RendererModel(this);
        bipedHead.setRotationPoint(0.0F, -2.0F, 0.0F);
        bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 9, -5.0F, -7.0F, -5.0F, 10, 9, 10, 0.0F, false));
        bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 0, -4.0F, -6.0F, -5.0F, 8, 8, 1, 0.0F, false));

        bipedBody = new RendererModel(this);
        bipedBody.setRotationPoint(0.0F, 7.0F, 0.0F);
        bipedBody.cubeList.add(new ModelBox(bipedBody, 24, 10, -5.0F, -7.0F, -3.0F, 10, 12, 6, 0.0F, false));

        bipedLeftArm = new RendererModel(this);
        bipedLeftArm.setRotationPoint(7.0F, -9.0F, 0.0F);
        bipedBody.addChild(bipedLeftArm);
        bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 34, 10, -2.0F, 2.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedRightArm = new RendererModel(this);
        bipedRightArm.setRotationPoint(-7.0F, -9.0F, 0.0F);
        bipedBody.addChild(bipedRightArm);
        bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 34, 10, -3.0F, 2.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedLeftLeg = new RendererModel(this);
        bipedLeftLeg.setRotationPoint(2.0F, 3.0F, 0.0F);
        bipedBody.addChild(bipedLeftLeg);
        bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 34, 10, -2.0F, 2.0F, -3.0F, 5, 12, 6, 0.0F, false));

        bipedRightLeg = new RendererModel(this);
        bipedRightLeg.setRotationPoint(-2.0F, 3.0F, 0.0F);
        bipedBody.addChild(bipedRightLeg);
        bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 34, 10, -3.0F, 2.0F, -3.0F, 5, 12, 6, 0.0F, false));

        /*model = new RendererModel(this,0,0);
        model.setRotationPoint(0,0,0);*/


    }
}
