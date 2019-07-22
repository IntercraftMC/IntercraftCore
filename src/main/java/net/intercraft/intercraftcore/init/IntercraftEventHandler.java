package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.Radiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


@Mod.EventBusSubscriber
public class IntercraftEventHandler
{
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        //System.out.println(event.player.getName());
        //event.player.sendMessage(new TextComponentString("Spamming!!"));
        if (event.player.getCapability(RadiationProvider.RAD_CAP).isPresent()) {

            IRadiation cap = event.player.getCapability(RadiationProvider.RAD_CAP).orElse(RadiationProvider.RAD_CAP.getDefaultInstance());

            cap.tick();
        }

        // Not sure if it would be better to have a big server tick function lowering the player's @exposure value by 1 per tick or make a different clock for each player. Just do it on online players.
    }

    @SubscribeEvent
    public static void onServerChatEvent(ServerChatEvent event)
    {

        //System.out.println("\""+event.getMessage()+"\"");

        if (event.getPlayer().getCapability(RadiationProvider.RAD_CAP).isPresent()) {

            IRadiation cap = event.getPlayer().getCapability(RadiationProvider.RAD_CAP).orElse(RadiationProvider.RAD_CAP.getDefaultInstance());

            switch (event.getMessage()) {

                case "increase": {

                    cap.increase(6000);
                    event.getPlayer().sendMessage(new TextComponentString("Increased."));
                    break;
                }

                case "check": {
                    event.getPlayer().sendMessage(new TextComponentString(String.format("Exposure is %s and absorbed is %s.", cap.getExposure(), cap.getAbsorbed())));
                }
            }
        }
    }

    @SubscribeEvent
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
    public void entityCaps(AttachCapabilitiesEvent<Entity> event)
    {
        System.out.println("Checking stuff...");
        if (event.getObject() instanceof EntityAnimal || event.getObject() instanceof EntityPlayer) {
            System.out.println("Added radiation capability to "+event.getObject().getName().getFormattedText()+".");
            final IRadiation rad_cap = new RadiationHandler(event.getObject());
            event.addCapability(IntercraftCore.RAD_ID, new CapabilityProviderSimple<IRadiation>(IntercraftCore.radiation.RAD_CAP,null,rad_cap));
        }
    }*/

    /*@SubscribeEvent
    public void onEntityAdded(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityAnimal || event.getEntity() instanceof EntityPlayer) {
            event.getEntity().getCapability(IntercraftCore.radiation.RAD_CAP);
        }
    }*/


    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer) {// || event.getObject() instanceof EntityMob
            System.out.println("Adding Capability.");
            event.addCapability(IntercraftCore.RAD_ID, new RadiationProvider());
        }
    }




}
