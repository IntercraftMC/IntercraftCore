package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.api.FluidType;
import net.intercraft.intercraftcore.block.BlockAutoCraftingTable;
import net.intercraft.intercraftcore.block.BlockSolidElement;
import net.intercraft.intercraftcore.block.BlockFrameElement;
import net.intercraft.intercraftcore.block.group.BlockElementGroup;
import net.intercraft.intercraftcore.item.ItemBucketNonFluid;
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

        add(IntercraftItems.RUBBER_RESIN_BUCKET_OAK);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_SPRUCE);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_BIRCH);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_JUNGLE);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_ACACIA);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_DARK_OAK);
        add(IntercraftItems.RUBBER_RESIN_BUCKET);

        add(IntercraftItems.COPPER_COIL);
        add(IntercraftItems.INSULATED_COPPER_COIL);
    }};

    /**
     * Pre-determined blocks to be coloured
     */

    private static List<Block> blocks = new ArrayList<Block>(){{
        add(IntercraftBlocks.AUTOCRAFTINGTABLE);
    }};


    @SubscribeEvent
    public static void ItemColorHandlerEvent(ColorHandlerEvent.Item event)
    {   System.out.println("Registering item colours ..");


        /**
         * Groups of items to be coloured
         */

        final ItemElementGroup[] groups = {
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
         * Registering Item + ItemBlock colours
         */

        for (Item item : items) {

            if (item instanceof ItemHardChunk)
                event.getItemColors().register(new ItemColorHandler(((ItemHardChunk)item).getTint(),1),item);
            else if (item instanceof ItemElement)
                event.getItemColors().register(new ItemColorHandler(((ItemElement)item).getTint()),item);
            else if (item instanceof ItemWireCoil)
                event.getItemColors().register(new ItemColorHandler(((ItemWireCoil)item).getTint(),1),item);
            else if (item instanceof ItemBucketNonFluid)
                event.getItemColors().register(new ItemColorHandler(FluidType.RUBBER_RESIN.getTint(),1),item);
        }
        for (Block block : blocks) {
            if (block instanceof BlockHardOre)
                event.getItemColors().register(new BlockColorHandler(((BlockHardOre)block).getTint()),block);
            else if (block instanceof BlockSolidElement)
                event.getItemColors().register(new BlockColorHandler(((BlockSolidElement)block).getTint()),block);
            else if (block instanceof BlockFrameElement)
                event.getItemColors().register(new BlockColorHandler(((BlockFrameElement)block).getTint()),block);
            else if (block instanceof BlockAutoCraftingTable)
                event.getItemColors().register(new BlockColorHandler(((BlockAutoCraftingTable)block).getTint()),block);
        }

        System.out.println("[Item Colours] Done.");
    }

    @SubscribeEvent
    public static void BlockColorHandlerEvent(ColorHandlerEvent.Block event)
    {   System.out.println("Registering block colours ..");


        /**
         * Groups of blocks to be coloured
         */

        final BlockElementGroup[] groups = {
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
            else if (block instanceof BlockSolidElement)
                event.getBlockColors().register(new BlockColorHandler(((BlockSolidElement) block).getTint()),block);
            else if (block instanceof BlockFrameElement)
                event.getBlockColors().register(new BlockColorHandler(((BlockFrameElement) block).getTint()),block);
            else if (block instanceof BlockAutoCraftingTable)
                event.getBlockColors().register(new BlockColorHandler(((BlockAutoCraftingTable) block).getTint()),block);



        }


        System.out.println("[Block Colours] Done.");
    }


    private static class ItemColorHandler implements IItemColor
    {
        private int tint;
        private int layer;

        /**
         * ColorHandler Constructor for particular texture layer.
         *
         * @param tint colour to be tinted with.
         * @param layer layer to be coloured.
         */
        public ItemColorHandler(int tint, int layer)
        {
            this.tint  = tint;
            this.layer = layer;
        }

        /**
         * ColorHandler Constructor
         *
         * @param tint colour to be tinted with.
         */
        public ItemColorHandler(int tint)
        {
            this(tint,-1);
        }

        public int getColor(ItemStack stack, int tint)
        {

            if (this.layer == -1)
                return this.tint;
            return this.layer == tint ? this.tint : 0xFFFFFF;

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
