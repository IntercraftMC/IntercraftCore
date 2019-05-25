package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.potion.PotionRadiationSickness;
import net.minecraft.potion.Potion;

public class IntercraftPotions {

    public static final Potion RADIATION;

    /**
     * Instantiate all potions
     */
    static
    {
        RADIATION = new PotionRadiationSickness(true, 1);
    }

    /**
     * Register all potions
     */
    public static void register()
    {
        registerPotion(RADIATION);
    }

    /**
     * Register a potion
     */
    protected static void registerPotion(Potion potion)
    {
        RegistrationHandler.potions.add(potion);
    }
}
