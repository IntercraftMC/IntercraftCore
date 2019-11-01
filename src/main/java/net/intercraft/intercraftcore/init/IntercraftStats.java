package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.entity.EntityType;
import net.minecraft.stats.StatType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

public class IntercraftStats
{
    //public static final StatType<EntityType<?>> NAME_HIDDEN = new



    //public static final StatType<EntityType<?>> NAME_HIDDEN;

    static {
        //NAME_HIDDEN = registerType("identity_hidden")
        //NAME_HIDDEN = registerType("identity_hidden",Registry.ENTITY_TYPE);
    }


    public static void register()
    {
        //registerStats(NAME_HIDDEN);
    }

    protected static void registerStats(StatType...statTypes)
    {
        //RegistrationHandler.stats.addAll(Arrays.asList(statTypes));
    }


    private static <T> StatType<T> registerType(String key, Registry<T> registry)
    {


        return Registry.register(Registry.STATS, Reference.MODID +":"+key, new StatType<>(registry));
    }
}
