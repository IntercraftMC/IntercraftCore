package net.intercraft.intercraftcore.entity.monster;

import com.mojang.authlib.GameProfile;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityZombiePlayer extends ZombieEntity
{
    private final String name;
    private GameProfile profile;

    public EntityZombiePlayer(World worldIn,String name)
    {
        super(worldIn);
        this.name = name;
        profile = new GameProfile(null,name);
    }

    public EntityZombiePlayer(World worldIn)
    {
        this(worldIn,WorldZombiePlayers.getRandom().getName());
    }

    public static EntityZombiePlayer spawnFromPlayer(World world, PlayerEntity player)
    {
        return spawnFromPlayer(world,player.posX,player.posY,player.posZ,player.getGameProfile());
    }

    public static EntityZombiePlayer spawnFromPlayer(World world, double x, double y, double z, GameProfile profile)
    {
        EntityZombiePlayer zo = new EntityZombiePlayer(world);
        zo.setPosition(x,y,z);
        zo.profile = profile;
        zo.enablePersistence();
        world.addEntity(zo);

        return zo;
    }

    public void setProfile(GameProfile profile)
    {
        this.profile = profile;
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
    {
        ILivingEntityData data = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

        setChild(false);

        enablePersistence();
        setCustomName(new StringTextComponent(name));
        setCustomNameVisible(true);

        return data;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent("entity."+ Reference.MODID +".zombie_player",name);
    }

    @Override
    protected boolean shouldDrown()
    {
        return false;
    }
}
