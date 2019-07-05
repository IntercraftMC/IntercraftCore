package net.intercraft.intercraftcore.ore;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHardChunk extends Item implements IItemColor {


    private int tint;
    private ElementComposition[] composition;

    public ItemHardChunk(Item.Properties builder, final String name, int tint, final ElementComposition[] composition) {
        super(builder);

        this.tint = tint;
        this.composition = composition;

        setRegistryName(name+"_chunk");


    }


    public ElementComposition[] getCompositions() {
        return this.composition;
    }

    public ElementComposition getCompositions(int index) {
        return this.composition[index];
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {



        if (flagIn.isAdvanced()) {
            //tooltip.add(new TextComponentString("Composition"));
            for (ElementComposition element : this.composition)
                tooltip.add(new TextComponentString(element.toString()));
        }


    }


    public int getColor(ItemStack stack, int tint) {

        if (tint == 0)
            return 0xFFFFFF;
        else
            return this.tint;
    }
}
