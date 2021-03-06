package net.intercraft.intercraftcore.item;

import net.intercraft.intercraftcore.element.Element;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.intercraft.intercraftcore.api.ElementComposition;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHardChunk extends Item
{


    private int tint;
    private ElementComposition[] composition;

    public ItemHardChunk(Element element, String suffix)
    {
        super(new Item.Properties().group(IntercraftItemGroups.RESOURCES));

        this.tint = element.tintSec;
        this.composition = element.composition;

        setRegistryName(element.symbol+"_chunk");


    }


    public ElementComposition[] getCompositions() {
        return this.composition;
    }

    public ElementComposition getCompositions(int index) {
        return this.composition[index];
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {

        if (flagIn.isAdvanced()) {
            //tooltip.add(new TextComponentString("Composition"));
            for (ElementComposition element : this.composition) {
                //tooltip.add(new TranslationTextComponent("tooltip.intercraftcore.composition",element.getSymbol(true),element.getPercentage()*100));
                //tooltip.add(new TranslationTextComponent(I18n.format("tooltip.intercraftcore.composition", TextFormatting.AQUA, TextFormatting.RESET),element.getSymbol(true),element.getPercentage()*100));

                TranslationTextComponent text = new TranslationTextComponent("tooltip.intercraftcore.composition",element.getSymbol(true),element.getPercentage(true));

                //text.getStyle().setColor();

                tooltip.add(text);


            }
        }


    }

    public int getTint() {
        return this.tint;
    }
}