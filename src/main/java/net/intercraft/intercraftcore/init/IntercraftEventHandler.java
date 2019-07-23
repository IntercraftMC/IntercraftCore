package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.command.RadiationDebugCommand;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;


@Mod.EventBusSubscriber
public class IntercraftEventHandler
{
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        //System.out.println(event.player.getName());
        //event.player.sendMessage(new TextComponentString("Spamming!!"));
        if (event.player.getCapability(RadiationProvider.RAD_CAP).isPresent()) {

            IRadiation cap = event.player.getCapability(RadiationProvider.RAD_CAP).orElse(RadiationProvider.RAD_CAP.getDefaultInstance());

            cap.tick(event.player);
        }

        // Not sure if it would be better to have a big server tick function lowering the player's @exposure value by 1 per tick or make a different clock for each player. Just do it on online players.
    }

    public static void onPLayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        event.getPlayer().sendMessage(new TextComponentString(String.format("Hello %s.", event.getPlayer().getName().getFormattedText())));

        if (event.getPlayer().getCapability(RadiationProvider.RAD_CAP).isPresent()) {

            IRadiation cap = event.getPlayer().getCapability(RadiationProvider.RAD_CAP).orElse(RadiationProvider.RAD_CAP.getDefaultInstance());





            event.getPlayer().sendMessage(new TextComponentString(String.format("Exposure is %s and absorbed is %s.", cap.getExposure(), cap.getAbsorbed())));
        }

        //IRadiation radiation = ((IRadiation) event.getPlayer().getCapability(RadiationProvider.RAD_CAP, null));

        //System.out.println(event.getPlayer().getCapability(RadiationProvider.RAD_CAP));






        //event.getPlayer().sendMessage(new TextComponentString(String.format("Absorbed is: %s.", radiation.getAbsorbed())));

        //IRadiation radiation = event.getPlayer().getCapability();
        //IRadiation radiation = event.getPlayer().getCapability(RadiationProvider.RAD_CAP, null);


        // Check whenever the player already has a @exposure value and then add the capability radiation to them with the value, otherwise put it on the minimum threshold.
    }


    /*@SubscribeEvent
    public void onEntityAdded(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityAnimal || event.getEntity() instanceof EntityPlayer) {
            event.getEntity().getCapability(IntercraftCore.radiation.RAD_CAP);
        }
    }*/

    @SubscribeEvent
    public static void onServerStarting(final FMLServerStartingEvent event)
    {
        RadiationDebugCommand.register(event.getCommandDispatcher());
    }


    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer || event.getObject() instanceof EntityAnimal) {
            //System.out.println("Adding Capability.");
            event.addCapability(IntercraftCore.RAD_ID, new RadiationProvider());
        }
    }




}
