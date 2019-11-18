package net.intercraft.intercraftcore.init.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class ConditionIsFull implements ILootCondition
{
    private ConditionIsFull()
    {

    }

    @Override
    public boolean test(LootContext lootContext)
    {
        TileEntity tile = lootContext.get(LootParameters.BLOCK_ENTITY);

        if (tile instanceof TreeTapTileEntity)
            return ((TreeTapTileEntity)tile).volume >= TreeTapTileEntity.maxVolume;
        return false;
    }

    public static class Serializer extends ILootCondition.AbstractSerializer<ConditionIsFull>
    {

        public Serializer()
        {
            super(new ResourceLocation(Reference.MODID,"is_full"),ConditionIsFull.class);
        }

        @Override
        public void serialize(JsonObject json, ConditionIsFull isFull, JsonSerializationContext context) { }

        @Override
        public ConditionIsFull deserialize(JsonObject json, JsonDeserializationContext context)
        {
            return new ConditionIsFull();
        }
    }
}
