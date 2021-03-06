package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.wire.Wire;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemWireCoil extends Item
{

    private final Wire wire;
    private final int maxLength, maxSize, tint;
    private final boolean insulated;

    private static final float lessMinThreshold = 2/3f, shortMinThreshold = 1/3f;

    /**
     * ItemWireCoil Constructor
     *
     * @param name the metal element symbol.
     * @param wire What wire to place in the world.
     * @param maxSize max length that can fit in the coil.
     * @param maxLength how long a single cable point-to-point can be.
     * @param insulated if contact with the player causes damage.
     * @param tint the wire tint overlay.
     */

    public ItemWireCoil(String name, Wire wire, int maxSize, int maxLength, boolean insulated, int tint)
    {
        super(new Item.Properties().group(IntercraftItemGroups.WIRING).maxStackSize(1));

        this.wire = wire;
        this.maxSize    = maxSize;
        this.maxLength  = maxLength;
        this.insulated  = insulated;
        this.tint       = tint;

        addPropertyOverride(new ResourceLocation("less"),(stack, worldIn, entityLivingBase) -> ((float) getSize(stack)/maxSize) <= lessMinThreshold ? 1 : 0);
        addPropertyOverride(new ResourceLocation("short"),(stack, worldIn, entityLivingBase) -> ((float) getSize(stack)/maxSize) <= shortMinThreshold ? 1 : 0);
        addPropertyOverride(new ResourceLocation("empty"),(stack, worldIn, entityLivingBase) -> getSize(stack) <= 0 ? 1 : 0);


        if (insulated)
            setRegistryName("insulated_"+name+"_wire_coil");
        else
            setRegistryName(name+"_wire_coil");
    }

    public ItemWireCoil(String name, Wire wire, int maxSize, int maxLength, int tint)
    {
        this(name,wire,maxSize,maxLength,false,tint);
    }

    public double getResistance()
    {
        return wire.resistance;
    }

    public boolean isInsulated()
    {
        return insulated;
    }

    public double getMaxLength()
    {
        return maxLength;
    }

    public int getMaxSize()
    {
        return maxSize;
    }

    public int getSize(ItemStack stack)
    {
        return maxSize+1-stack.getDamage();
    }

    public int getTint()
    {
        return tint;
    }

    private ITextComponent colorText(String str, TextFormatting color)
    {
        return new StringTextComponent(str).setStyle(new Style().setColor(color));
    }

    private ITextComponent colorDurability(double dur, double percent)
    {
        if (percent > lessMinThreshold)
            return colorText((int)dur+"",TextFormatting.GREEN);
        else if (percent > shortMinThreshold)
            return colorText((int)dur+"",TextFormatting.YELLOW);
        return colorText((int)dur+"",TextFormatting.RED);
    }

    /*@Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack)
    {
        //stack.setDamage(1);
        return super.getAttributeModifiers(slot,stack);
    }*/

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return maxSize+1;
    }

    @Override
    public String getTranslationKey(ItemStack stack)
    {
        return getSize(stack) <= 0 ? "item."+Reference.MODID+".empty_wire_coil" : super.getTranslationKey(stack);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context)
    {

        System.out.println(context.getPos());

        // If it is a attachable block, save location to NBT, then draw line between that and next position. Abort if the distance > size or maxLength.




        return super.onItemUse(context);


    }

    @Override
    public ItemStack getDefaultInstance()
    {
        ItemStack stack = super.getDefaultInstance();
        stack.setDamage(1);
        return stack;
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

        float size = getSize(stack);
        ITextComponent sizeS = colorDurability(size,size/maxSize);

        final String key = "tooltip."+Reference.MODID+".";

        tooltip.add(new TranslationTextComponent(key+"length.size",sizeS,maxSize));
        tooltip.add(new TranslationTextComponent(key+"length.max",maxLength));
        tooltip.add(new TranslationTextComponent(key+"resistance",wire.resistance));
    }
}
