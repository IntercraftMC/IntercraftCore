package net.intercraft.intercraftcore.entity.monster;

import java.util.Random;

public enum WorldZombiePlayers
{
    // Mojang
    STEVE("Steve"),
    NOTCH("Notch"),
    DINNERBONE("Dinnerbone"),
    JEB_("jeb_"),

    // OpenComputer
    SANGAR("Sangar"),
    PAYONEL("payonel");

    private final String name;

    private static final Random r = new Random();

    /**
     * Player Zombies that can spawn in the world
     *
     * @param name The player name.
     */

    WorldZombiePlayers(String name)
    {
        this.name = name;
    }

    public static WorldZombiePlayers getRandom()
    {
        return WorldZombiePlayers.values()[r.nextInt(WorldZombiePlayers.values().length)];
    }

    public String getName()
    {
        return name;
    }
}
