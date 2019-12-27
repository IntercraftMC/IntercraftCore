package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.capabilities.radiation.Radiation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemHazMatSuit extends ArmorItem
{

    public static final ResourceLocation texture = new ResourceLocation(Reference.MODID,"textures/models/armor/hazmat/hazmat_suit.png");
    //public final BipedModel<LivingEntity> model;

    private final float[] protectionDivision;
    private final int tint;

    public ItemHazMatSuit(String name, EquipmentSlotType slot, float[] protectionDivision, Item.Properties properties, int tint)
    {
        super(ArmorMaterial.LEATHER, slot, properties);

        //model = new ModelHazMatSuit(tint);
        this.protectionDivision = protectionDivision;
        this.tint = tint;
        setRegistryName(name);
    }

    public ItemHazMatSuit(String name, EquipmentSlotType slot, float[] protectionDivision, Item.Properties properties)
    {
        this(name,slot,protectionDivision,properties,-1);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        return texture.toString();
    }

    /*@OnlyIn(Dist.CLIENT)
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default)
    {
        model.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;
        model.bipedHead.showModel = armorSlot == EquipmentSlotType.HEAD;

        model.bipedBody.showModel = armorSlot == EquipmentSlotType.CHEST;

        model.bipedLeftArm.showModel = armorSlot == EquipmentSlotType.CHEST;
        model.bipedRightArm.showModel = armorSlot == EquipmentSlotType.CHEST;

        model.bipedLeftLeg.showModel = armorSlot == EquipmentSlotType.LEGS;
        model.bipedRightArm.showModel = armorSlot == EquipmentSlotType.LEGS;

        model.isChild = _default.isChild;
        model.isSneak = _default.isSneak;
        model.isSitting = _default.isSitting;

        model.leftArmPose = _default.leftArmPose;
        model.rightArmPose = _default.rightArmPose;


        return (A) model;
    }*/

    public void onEquipped()
    {

    }

    public void onUnequipped()
    {

    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
    {
        //System.out.println(String.format("%s's %s armour item ticked",player.getDisplayName().getFormattedText(),stack.getDisplayName().getFormattedText()));
    }

    public float getProtection(Radiation.ExposureEntryPoint entryPoint)
    {
        switch (entryPoint) {
            case TOP:
                return protectionDivision[0];
            case BOTTOM:
                return protectionDivision[2];
            default:
                return protectionDivision[1];

        }
    }


    public int getTint()
    {
        return tint;
    }
}
