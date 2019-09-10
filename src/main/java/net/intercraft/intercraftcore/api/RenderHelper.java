package net.intercraft.intercraftcore.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;

public class RenderHelper
{
    public static void followLeftArmTransformation(final LivingEntity entitylivingbaseIn, RendererModel... renderers)
    {
        EntityRenderer render = Minecraft.getInstance().getRenderManager().getRenderer(entitylivingbaseIn);

        if (render instanceof LivingRenderer) {
            EntityModel model = ((LivingRenderer) render).getEntityModel();

            if (model instanceof BipedModel) {

                for (RendererModel renderer : renderers) {
                    renderer.copyModelAngles(((BipedModel) model).bipedLeftArm);
                }
            }
        }
    }
}
