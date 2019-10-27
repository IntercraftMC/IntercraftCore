package net.intercraft.intercraftcore.item;

import com.google.common.collect.Multimap;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemWireCoil extends Item
{

    private final double resistance;
    private final int maxLength, maxSize, tint;
    private final boolean insulated;

    public ItemWireCoil(String name, double resistance, int maxSize, int maxLength, boolean insulated, int tint)
    {
        super(new Item.Properties().group(IntercraftItemGroups.WIRING).maxStackSize(1));

        this.resistance = resistance;
        this.maxSize = maxSize;
        this.maxLength = maxLength;
        this.insulated = insulated;
        this.tint = tint;


        addPropertyOverride(new ResourceLocation(Reference.MODID+":insulated"),(itemStack, worldIn, entityLivingBase) -> insulated ? 1 : 0);


        setRegistryName(name+"_wire_coil");

    }

    public double getResistance()
    {
        return resistance;
    }

    public double getMaxLength()
    {
        return maxLength;
    }

    public boolean isInsulated()
    {
        return insulated;
    }

    public int getTint()
    {
        return tint;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack)
    {
        stack.setDamage(1);
        return super.getAttributeModifiers(slot,stack);
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return maxSize+1;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {

        System.out.println(context.getPos());

        // If it is a attachable block, save location to NBT, then draw line between that and next position.




        return super.onItemUse(context);


    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        if (!worldIn.isRemote()) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            if (playerIn.isSneaking() && stack.getTag().getBoolean(Reference.MODID + ":point1")) {
                playerIn.sendMessage(new TranslationTextComponent("info." + Reference.MODID + ".wiring.cleared"));
                stack.removeChildTag(Reference.MODID + ":point1");
            }
        }


        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(new TranslationTextComponent("tooltip.intercraftcore.length.size",maxSize+1-stack.getDamage(), maxSize));
        tooltip.add(new TranslationTextComponent("tooltip.intercraftcore.length.max",maxLength));
        tooltip.add(new TranslationTextComponent("tooltip.intercraftcore.resistance",resistance));
    }
}
