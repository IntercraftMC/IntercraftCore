package net.intercraft.intercraftcore.init.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.BlockProperties;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

/**
 * Thanks Draco18s. :)
 */

public class FunctionHarderSetCount extends LootFunction
{
    private final float multiplier;

    private FunctionHarderSetCount(ILootCondition[] conditions, float multiplier)
    {
        super(conditions);
        this.multiplier = multiplier;
    }

    public ItemStack doApply(ItemStack stack, LootContext context)
    {
        BlockState state = context.get(LootParameters.BLOCK_STATE);

        if (state.has(BlockProperties.DENSITY)) {
            int density = state.get(BlockProperties.DENSITY);
            stack.setCount(Math.round(((int) multiplier)*(density+1)));
            return stack;
        }
        stack.setCount(1);
        return stack;
    }


    public static LootFunction.Builder<?> func_215932_a(float multiplier)
    {
        return builder((p_215934_1_) -> new FunctionHarderSetCount(p_215934_1_, multiplier));
    }

    public static class Serializer extends LootFunction.Serializer<FunctionHarderSetCount>
    {
        public Serializer()
        {
            super(new ResourceLocation(Reference.MODID,"set_count"), FunctionHarderSetCount.class);
        }

        public void serialize(JsonObject json, FunctionHarderSetCount functionHarderSetCount, JsonSerializationContext serializationContext)
        {
            super.serialize(json, functionHarderSetCount, serializationContext);
            json.addProperty("multiplier", functionHarderSetCount.multiplier);
        }

        public FunctionHarderSetCount deserialize(JsonObject json, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn)
        {
            float multiplier = json.get("multiplier").getAsFloat();
            return new FunctionHarderSetCount(conditionsIn, multiplier);
        }
    }
}