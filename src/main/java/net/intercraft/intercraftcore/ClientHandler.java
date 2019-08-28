package net.intercraft.intercraftcore;

import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler
{

    @SubscribeEvent
    public static void attachTER(final FMLClientSetupEvent event)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TreeTapTileEntity.class, new TreeTapTileEntityRenderer<>());
    }

}
