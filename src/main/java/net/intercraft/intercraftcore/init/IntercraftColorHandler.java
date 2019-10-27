package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.BlockElement;
import net.intercraft.intercraftcore.block.BlockFrame;
import net.intercraft.intercraftcore.block.group.BlockElementGroup;
import net.intercraft.intercraftcore.item.ItemElement;
import net.intercraft.intercraftcore.item.ItemWireCoil;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.block.BlockHardOre;
import net.intercraft.intercraftcore.item.ItemHardChunk;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class IntercraftColorHandler
{


    /**
     * Pre-determined items to be coloured
     */

    private static List<Item> items = new ArrayList<Item>(){{
        /*add(IntercraftItems.COPPER.CHUNK);
        add(IntercraftItems.TIN.CHUNK);
        add(IntercraftItems.LEAD.CHUNK);*/
        add(IntercraftItems.COPPERCOIL);
    }};

    /**
     * Pre-determined blocks to be coloured
     */

    private static List<Block> blocks = new ArrayList<Block>(){{
            /*add(IntercraftBlocks.COPPERORE);
            add(IntercraftBlocks.TINORE);
            add(IntercraftBlocks.LEADORE);*/
        }};


    @SubscribeEvent
    public static void ItemColorHandlerEvent(ColorHandlerEvent.Item event)
    {   System.out.println("Registering item colours ..");


        /**
         * Groups of items to be coloured
         */

        ItemElementGroup[] groups = {
                IntercraftItems.ALUMINIUM,
                IntercraftItems.COPPER,
                IntercraftItems.GOLD,
                IntercraftItems.IRIDIUM,
                IntercraftItems.IRON,
                IntercraftItems.LEAD,
                IntercraftItems.SILVER,
                IntercraftItems.THORIUM,
                IntercraftItems.TIN,
                IntercraftItems.TITANIUM,
                IntercraftItems.TUNGSTEN,
                IntercraftItems.URANIUM,
                IntercraftItems.ZINC,

                IntercraftItems.CARBON,
                IntercraftItems.SILICON,

                IntercraftItems.BRASS,
                IntercraftItems.BRONZE,
                IntercraftItems.STEEL
        };



        /**
         * Filter out- and placing the group's content into the @items variable
         */

        for (ItemElementGroup group: groups) {
            Item[] i = { group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE, group.GEAR, group.ROD, group.CHUNK };
            for (Item item: i)
                if ( item != null)
                    items.add(item);

        }

        /**
         * Registering Item colours
         */

        for (Item item : items) {

            if (item instanceof ItemHardChunk)
                event.getItemColors().register(new ItemColorHandler(((ItemHardChunk)item).getTint(), true),item);
            else if (item instanceof ItemElement)
                event.getItemColors().register(new ItemColorHandler(((ItemElement)item).getTint(), false),item);
            else if (item instanceof ItemWireCoil)
                event.getItemColors().register(new ItemColorHandler(((ItemWireCoil)item).getTint(), true),item);
        }
        for (Block block : blocks) {
            if (block instanceof BlockHardOre)
                event.getItemColors().register(new BlockColorHandler(((BlockHardOre) block).getTint()),block);
            else if (block instanceof BlockElement)
                event.getItemColors().register(new BlockColorHandler(((BlockElement) block).getTint()),block);
            else if (block instanceof BlockFrame)
                event.getItemColors().register(new BlockColorHandler(((BlockFrame) block).getTint()),block);
        }

        System.out.println("[Item Colours] Done.");
    }

    @SubscribeEvent
    public static void BlockColorHandlerEvent(ColorHandlerEvent.Block event)
    {   System.out.println("Registering block colours ..");


        /**
         * Groups of items to be coloured
         */

        BlockElementGroup[] groups = {
                IntercraftBlocks.ALUMINIUM,
                IntercraftBlocks.COPPER,
                IntercraftBlocks.GOLD,
                IntercraftBlocks.IRIDIUM,
                IntercraftBlocks.IRON,
                IntercraftBlocks.LEAD,
                IntercraftBlocks.SILVER,
                IntercraftBlocks.THORIUM,
                IntercraftBlocks.TIN,
                IntercraftBlocks.TITANIUM,
                IntercraftBlocks.TUNGSTEN,
                IntercraftBlocks.URANIUM,
                IntercraftBlocks.ZINC,

                IntercraftBlocks.BRASS,
                IntercraftBlocks.BRONZE,
                IntercraftBlocks.STEEL
        };

        /**
         * Filter out- and placing the group's content into the @blocks variable
         */

        for (BlockElementGroup group: groups) {
            Block[] i = { group.BLOCK, group.FRAME, group.ORE };
            for (Block block: i)
                if ( block != null)
                    blocks.add(block);

        }

        /**
         * Registering Block colours
         */

        for (Block block : blocks) {

            if (block instanceof BlockHardOre)
                event.getBlockColors().register(new BlockColorHandler(((BlockHardOre) block).getTint()),block);
            else if (block instanceof BlockElement)
                event.getBlockColors().register(new BlockColorHandler(((BlockElement) block).getTint()),block);
            else if (block instanceof BlockFrame)
                event.getBlockColors().register(new BlockColorHandler(((BlockFrame) block).getTint()),block);


        }


        System.out.println("[Block Colours] Done.");

    }


    private static class ItemColorHandler implements IItemColor
    {
        private int tint;
        private boolean layer;

        public ItemColorHandler(int tint, boolean layer)
        {
            this.tint = tint;
            this.layer = layer;
        }

        public int getColor(ItemStack stack, int tint)
        {

            if (this.layer)
                if (tint == 0)
                    return 0xFFFFFF;
            return this.tint;


        }
    }
    private static class BlockColorHandler implements IBlockColor, IItemColor
    {
        private int tint;

        public BlockColorHandler(int tint)
        {
            this.tint = tint;
        }



        public int getColor(ItemStack stack, int tint)
        {
            return this.tint;
        }

        @Override
        public int getColor(BlockState blockState, @Nullable IEnviromentBlockReader iEnviromentBlockReader, @Nullable BlockPos blockPos, int i)
        {
            return this.tint;
        }
    }
}
