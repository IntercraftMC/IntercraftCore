package net.intercraft.intercraftcore.init.loot.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.api.BlockProperties;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
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
        ItemStack tool = context.get(LootParameters.TOOL);
        short fortune = tool != null ? (short) EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, tool) : 0;

        if (state.has(BlockProperties.DENSITY)) {
            int density = state.get(BlockProperties.DENSITY);
            IRandomRange range = new RandomValueRange(Math.min(fortune+1,density+1),density+1);

            stack.setCount(Math.round(range.generateInt(context.getRandom())*multiplier));

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