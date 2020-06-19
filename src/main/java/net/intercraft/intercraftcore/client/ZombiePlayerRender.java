package net.intercraft.intercraftcore.client;

import net.intercraft.intercraftcore.client.models.ModelZombiePlayer;
import net.intercraft.intercraftcore.entity.monster.EntityZombiePlayer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class ZombiePlayerRender extends BipedRenderer<EntityZombiePlayer, ModelZombiePlayer<EntityZombiePlayer>>
{

    private static final ResourceLocation steve = new ResourceLocation("minecraft","textures/entity/steve");

    public ModelZombiePlayer thin = new ModelZombiePlayer(0,true);

    public ZombiePlayerRender(EntityRendererManager manager)
    {
        super(manager,new ModelZombiePlayer<>(0,false),0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityZombiePlayer entity)
    {
        return steve;
    }
}
