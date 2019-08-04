package net.intercraft.intercraftcore.ore;

import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;

public class ItemBlockHardOre extends BlockItem {
    public ItemBlockHardOre(Block block, Item.Properties properties) {
        super(block,properties.group(IntercraftItemGroups.RESOURCES));


    }



    /*public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        this.getBlock().addInformation(stack, worldIn, tooltip, flagIn);

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInt("density", IntercraftCore.defDensity);

        stack.setTag(nbt);
    }*/
}
