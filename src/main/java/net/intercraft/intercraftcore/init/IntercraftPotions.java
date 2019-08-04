package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.potion.PotionRadiationSickness;
import net.minecraft.potion.Effect;

public class IntercraftPotions
{
    public static final Effect RADIATION;

    /**
     * Instantiate all effects
     */
    static {
        RADIATION = new PotionRadiationSickness();
    }

    /**
     * Register all effects
     */
    public static void register()
    {
        registerPotion(RADIATION);
    }

    /**
     * Register a potion
     */
    protected static void registerPotion(Effect effect)
    {
        RegistrationHandler.effects.add(effect);
    }
}
