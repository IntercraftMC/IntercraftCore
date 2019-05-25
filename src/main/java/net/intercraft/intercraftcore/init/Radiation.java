package net.intercraft.intercraftcore.init;

import com.mojang.authlib.GameProfile;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class Radiation extends EntityPlayer
{
    public Radiation(World worldIn, GameProfile playerProfile)
    {
        super(worldIn, playerProfile);

        getEntityData().setLong(Reference.MODID + "_radiation_exposure", 0);
    }

    public void tick()
    {
        long exposure = getEntityData().getLong(Reference.MODID + "_radiation_exposure");
        if (exposure > 0) {
            getEntityData().setLong(Reference.MODID + "_radiation_exposure", exposure - 1);
            System.out.println(exposure);
        }
    }

    public void expose(int intensity)
    {
        long exposure = getEntityData().getLong(Reference.MODID + "_radiation_exposure");

        //player.getEntityData().getInt(Reference.MODID+"_radiation_exposure") += intensity;

        getEntityData().setLong(Reference.MODID + "_radiation_exposure", exposure + intensity);
    }
}
