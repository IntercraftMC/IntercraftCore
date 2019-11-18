package net.intercraft.intercraftcore.block;

import net.intercraft.intercraftcore.element.Element;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSolidUranium extends BlockSolidElement
{
    public BlockSolidUranium(Element element, String registrySuffix)
    {
        super(element,registrySuffix);
    }


    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        super.onEntityWalk(worldIn, pos, entityIn);

        EffectInstance effect = new EffectInstance(Effects.POISON, 60, 0);
        if (entityIn instanceof PlayerEntity)
            ((PlayerEntity) entityIn).addPotionEffect(effect);
    }
}
