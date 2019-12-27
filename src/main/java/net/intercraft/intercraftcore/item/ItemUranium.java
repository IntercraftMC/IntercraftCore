package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.init.capabilities.radiation.Radiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.api.RadiationEmitterProvider;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemUranium extends ItemElement
{

    public ItemUranium(Element element,String registrySuffix)
    {
        super(element, registrySuffix);
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        return new RadiationEmitterProvider(80);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        /*short amp = isSelected ? (short) 1 : (short) 0;

        EffectInstance effect = new EffectInstance(Effects.POISON, 60, amp);
        if (entityIn instanceof PlayerEntity)
            ((PlayerEntity) entityIn).addPotionEffect(effect);*/

        if (worldIn.getGameTime() % Radiation.tickrate == 0) {
            stack.getCapability(RadiationEmitterProvider.RAE_CAP).ifPresent(cap -> cap.addToEntity(entityIn,1.35f,1f,1.35f));
        }

    }
}
