package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.enchantment.EnchantmentAutoPickup;
import net.minecraft.enchantment.Enchantment;

import java.util.Arrays;

public class IntercraftEnchantments
{
    public static final Enchantment AUTO_PICKUP;


    static {
        AUTO_PICKUP = new EnchantmentAutoPickup();
    }

    protected static void registerEnchantment(Enchantment...enchantments)
    {
        RegistrationHandler.enchantments.addAll(Arrays.asList(enchantments));
    }

    public static void register()
    {
        registerEnchantment(AUTO_PICKUP);
    }

}
