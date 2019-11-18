package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.element.Element;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUranium extends ItemElement
{

    public ItemUranium(Element element,String registrySuffix)
    {
        super(element, registrySuffix);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        short amp = isSelected ? (short) 1 : (short) 0;

        EffectInstance effect = new EffectInstance(Effects.POISON, 60, amp);
        if (entityIn instanceof PlayerEntity)
            ((PlayerEntity) entityIn).addPotionEffect(effect);
    }
}
