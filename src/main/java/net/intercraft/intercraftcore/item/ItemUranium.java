package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.element.Element;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemUranium extends ItemElement
{
    /**
     * Create a generic Uranium element item
     * @param element
     * @param registrySuffix
     */
    public ItemUranium(Element element,String registrySuffix) {
        super(element, registrySuffix);
    }

    /**
     * Tick the inventory to add the potion effect
     * @param stack
     * @param worldIn
     * @param entityIn
     * @param itemSlot
     * @param isSelected
     */
    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        int amp = 0;

        if (isSelected)
            amp = 1;

        PotionEffect effect = new PotionEffect(MobEffects.POISON, 60, amp);
        if (entityIn instanceof EntityPlayer)
            ((EntityPlayer) entityIn).addPotionEffect(effect);
    }
}