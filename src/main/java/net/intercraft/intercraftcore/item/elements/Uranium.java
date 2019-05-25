package net.intercraft.intercraftcore.item.elements;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Uranium extends ItemElement
{
    /*
     *
     * */
    public Uranium()
    {
        super("u", "uranium", 0x92d67d);
    }

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