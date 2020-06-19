package net.intercraft.intercraftcore.client;

import net.intercraft.intercraftcore.entity.monster.EntityZombiePlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ZombiePlayerRenderFactory implements IRenderFactory<EntityZombiePlayer>
{
    public static final ZombiePlayerRenderFactory INSTANCE = new ZombiePlayerRenderFactory();

    @Override
    public EntityRenderer<? super EntityZombiePlayer> createRenderFor(EntityRendererManager manager)
    {
        return new ZombiePlayerRender(manager);
    }
}
