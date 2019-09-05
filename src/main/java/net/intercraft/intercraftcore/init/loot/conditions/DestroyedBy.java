package net.intercraft.intercraftcore.init.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class DestroyedBy implements ILootCondition
{

    private final String destroyer;

    private DestroyedBy(String destroyer)
    {
        this.destroyer = destroyer;
    }


    //public static final DestroyedBy INSTANCE = new DestroyedBy();


    @Override
    public boolean test(LootContext lootContext)
    {

        //Block block = lootContext.get(LootParameters.BLOCK_STATE).getBlock();

        //System.out.println(destroyer);


        /**
         * [16:44:43.824] [Server thread/INFO] [STDOUT/]: [net.intercraft.intercraftcore.init.loot.conditions.DestroyedBy:test:35]: ServerPlayerEntity['Dev'/122, l='New World', x=6.22, y=108.00, z=151.05]
         * [16:44:43.824] [Server thread/INFO] [STDOUT/]: [net.intercraft.intercraftcore.init.loot.conditions.DestroyedBy:test:36]: 1 diamond_pickaxe
         * [16:44:56.414] [Server thread/INFO] [STDOUT/]: [net.intercraft.intercraftcore.init.loot.conditions.DestroyedBy:test:35]: null
         * [16:44:56.414] [Server thread/INFO] [STDOUT/]: [net.intercraft.intercraftcore.init.loot.conditions.DestroyedBy:test:36]: 1 air
        * */


        System.out.println(lootContext.get(LootParameters.THIS_ENTITY)); //[16:43:09.141] [Server thread/INFO] [STDOUT/]: [net.intercraft.intercraftcore.init.loot.conditions.DestroyedBy:test:35]: ServerPlayerEntity['Dev'/137, l='New World', x=6.01, y=108.00, z=150.81]
        System.out.println(lootContext.get(LootParameters.KILLER_ENTITY));




        switch (destroyer) {
            case "minecraft:explosion": {

                if (lootContext.get(LootParameters.THIS_ENTITY) == null) {



                    System.out.println("It was a explosion!");
                    return true;
                }
                break;

            }

            case "minecraft:player": {
                if (lootContext.get(LootParameters.THIS_ENTITY) instanceof PlayerEntity) {
                    System.out.println("It's a player!");
                    return true;
                }
                break;
            }



            case "mob": {
                if (lootContext.get(LootParameters.THIS_ENTITY) instanceof LivingEntity && !(lootContext.get(LootParameters.THIS_ENTITY) instanceof PlayerEntity)) {
                    System.out.println("It's a mob!");
                    return true;
                }
                break;
            }
        }



        //System.out.println(String.format("Damage type is \"%s\"",source.getDamageType()));

        return false;//source.getDamageType().equals(destroyer);

    }


    /*public static ILootCondition.IBuilder builder()
    {
        return () -> {
            return INSTANCE;
        };
    }*/

    public static class Serializer extends ILootCondition.AbstractSerializer<DestroyedBy>
    {

        public Serializer()
        {
            super(new ResourceLocation(Reference.MODID,"destroyed_by"),DestroyedBy.class);
        }

        @Override
        public void serialize(JsonObject json, DestroyedBy value, JsonSerializationContext context)
        {
            json.addProperty("destroyer",value.destroyer);
        }

        @Override
        public DestroyedBy deserialize(JsonObject json, JsonDeserializationContext context)
        {
            return new DestroyedBy(json.has("destroyer") ? JSONUtils.getString(json,"destroyer") : "minecraft:player");
        }
    }
}
