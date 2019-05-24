package net.intercraft.intercraftcore.init;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PotionRegistry {

    public static final Potion RADIATION;

    static {
        RADIATION = new PotionRadiation(true,1);
    }

    public static void registerPotions(final RegistryEvent.Register<Potion> event) {
        if (event.getGenericType() == Potion.class)
            event.getRegistry().register(RADIATION);
    }
}
