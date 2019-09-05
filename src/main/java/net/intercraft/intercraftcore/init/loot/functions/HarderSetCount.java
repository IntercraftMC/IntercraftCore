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

public class HarderSetCount extends LootFunction
{
    float multiplier = 1;

    private HarderSetCount(ILootCondition[] p_i51222_1_, float mod)
    {
        super(p_i51222_1_);
        multiplier = mod;
    }

    public ItemStack doApply(ItemStack stack, LootContext context)
    {
        BlockState state = context.get(LootParameters.BLOCK_STATE);

        if(state.has(BlockProperties.DENSITY)) {
            int density = state.get(BlockProperties.DENSITY);
            stack.setCount(Math.round(((int) multiplier)*(density+1)));
            return stack;
        }
        stack.setCount(1);
        return stack;
    }


    public static LootFunction.Builder<?> func_215932_a(float mod) {
        return builder((p_215934_1_) -> new HarderSetCount(p_215934_1_, mod));
    }

    public static class Serializer extends LootFunction.Serializer<HarderSetCount> {
        public Serializer()
        {
            super(new ResourceLocation(Reference.MODID+":set_count"), HarderSetCount.class);
        }

        public void serialize(JsonObject object, HarderSetCount functionClazz, JsonSerializationContext serializationContext)
        {
            super.serialize(object, functionClazz, serializationContext);
            object.addProperty("multiplier", functionClazz.multiplier);
        }

        public HarderSetCount deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn)
        {
            float mod = object.get("multiplier").getAsFloat();
            return new HarderSetCount(conditionsIn, mod);
        }
    }
}