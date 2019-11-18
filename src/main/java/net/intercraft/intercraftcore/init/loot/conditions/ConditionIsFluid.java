package net.intercraft.intercraftcore.init.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

public class ConditionIsFluid implements ILootCondition
{

    private final String fluid;

    private ConditionIsFluid(String fluid)
    {
        this.fluid = fluid;
    }

    @Override
    public boolean test(LootContext lootContext)
    {
        TileEntity tile = lootContext.get(LootParameters.BLOCK_ENTITY);

        if (tile instanceof TreeTapTileEntity)
            return ((TreeTapTileEntity)tile).fluidType.getName().equals(fluid);
        return false;
    }

    public static class Serializer extends AbstractSerializer<ConditionIsFluid>
    {

        public Serializer()
        {
            super(new ResourceLocation(Reference.MODID,"is_fluid"), ConditionIsFluid.class);
        }

        @Override
        public void serialize(JsonObject json, ConditionIsFluid isFluid, JsonSerializationContext context)
        {
            json.addProperty("fluid",isFluid.fluid);
        }

        @Override
        public ConditionIsFluid deserialize(JsonObject json, JsonDeserializationContext context)
        {
            return new ConditionIsFluid(json.has("fluid") ? JSONUtils.getString(json,"fluid") : "none");
        }
    }
}
