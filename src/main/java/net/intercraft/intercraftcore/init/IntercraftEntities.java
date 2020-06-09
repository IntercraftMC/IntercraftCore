package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.entity.monster.EntityZombiePlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import java.util.Arrays;

public class IntercraftEntities
{
    public static final EntityType<EntityZombiePlayer> ZOMBIE_PLAYER;


    static {
        ZOMBIE_PLAYER = buildEntity("zombie_player",EntityType.Builder.create((type, world) -> new EntityZombiePlayer(world), EntityClassification.MONSTER));
    }

    private static <T extends Entity> EntityType<T> buildEntity(String name, EntityType.Builder<T> builder)
    {
        EntityType<T> type = builder.build(name);
        type.setRegistryName(Reference.MODID,name);
        return type;
    }

    public static void register()
    {
        registerEntities(ZOMBIE_PLAYER);
    }

    protected static void registerEntities(EntityType...entityTypes)
    {
        RegistrationHandler.entities.addAll(Arrays.asList(entityTypes));
    }
}
