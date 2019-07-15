package net.intercraft.intercraftcore.init;


import net.intercraft.intercraftcore.item.ItemElement;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.ore.BlockHardOre;
import net.intercraft.intercraftcore.ore.ItemHardChunk;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReaderBase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class IntercraftColorHandler
{
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void ItemColorHandlerEvent(ColorHandlerEvent.Item event)
    {   System.out.println("Registering the item colours ..");

        /**
         * Groups which are supposed to be coloured.
        * */

        ItemElementGroup[] groups = {
                IntercraftItems.COPPER,
                IntercraftItems.LEAD,
                IntercraftItems.SILVER,
                IntercraftItems.TIN,
                IntercraftItems.TUNGSTEN,
                IntercraftItems.URANIUM
        };


        /**
         * Pre-determined items to be coloured.
        * */

        List<Item> items = new ArrayList<>();
        items.add(IntercraftItems.COPPERCHUNK);
        items.add(IntercraftItems.TINCHUNK);

        /**
         * Pre-determined items to be coloured.
        * */

        List<Block> blocks = new ArrayList<>();
        blocks.add(IntercraftBlocks.COPPERORE);
        blocks.add(IntercraftBlocks.TINORE);

        /**
         * Filter out- and placing the group's content into the @items variable.
        * */

        for (ItemElementGroup group: groups) {
            Item[] i = { group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE };
            for (Item item: i)
                if ( item != null)
                    items.add(item);

        }

        /**
         * Registering Item colours.
        * */

        for (Item item : items) {

            if (item instanceof ItemHardChunk)
                event.getItemColors().register(new ItemColorHandler(((ItemHardChunk)item).getTint(), true),item);
            else if (item instanceof ItemElement)
                event.getItemColors().register(new ItemColorHandler(((ItemElement)item).getTint(), false),item);
        }
        for (Block block : blocks) {
            event.getItemColors().register(new BlockColorHandler(((BlockHardOre) block).getTint()),block);
        }

        System.out.println("[Item Colours] Done.");
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void BlockColorHandlerEvent(ColorHandlerEvent.Block event)
    {   System.out.println("Registering the block colours ..");


        /**
         * Pre-determined blocks to be coloured.
        * */

        List<Block> blocks = new ArrayList<>();
        blocks.add(IntercraftBlocks.COPPERORE);
        blocks.add(IntercraftBlocks.TINORE);


        /**
         * Registering Block colours.
        * */

        for (Block block : blocks) {
            event.getBlockColors().register(new BlockColorHandler(((BlockHardOre) block).getTint()),block);

        }


        System.out.println("[Block Colours] Done.");

    }


    private static class ItemColorHandler implements IItemColor
    {
        private int tint;
        private boolean layer;

        public ItemColorHandler(int tint, boolean layer) {
            this.tint = tint;
            this.layer = layer;
        }


        public int getColor(ItemStack stack, int tint) {

            if (this.layer)
                if (tint == 0)
                    return 0xFFFFFF;
                else
                    return this.tint;
            else
                return this.tint;
        }
    }
    private static class BlockColorHandler implements IBlockColor, IItemColor
    {
        private int tint;

        public BlockColorHandler(int tint) {
            this.tint = tint;
        }

        public int getColor(IBlockState state, IWorldReaderBase world, BlockPos pos, int tintIndex) {
            return this.tint;
        }

        public int getColor(ItemStack stack, int tint) {
            return this.tint;
        }
    }



}
