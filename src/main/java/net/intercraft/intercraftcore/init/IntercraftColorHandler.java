package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.BlockAutoCraftingTable;
import net.intercraft.intercraftcore.block.BlockSolidElement;
import net.intercraft.intercraftcore.block.BlockFrameElement;
import net.intercraft.intercraftcore.block.group.BlockElementGroup;
import net.intercraft.intercraftcore.item.*;
import net.intercraft.intercraftcore.item.group.itemColoredGroup.ItemColoredGroup;
import net.intercraft.intercraftcore.item.group.ItemElementGroup;
import net.intercraft.intercraftcore.block.BlockHardOre;
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
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class IntercraftColorHandler
{


    /**
     * Pre-determined items to be coloured
     */

    public static List<Item> items = new ArrayList<Item>(){{

        add(IntercraftItems.RUBBER_RESIN_BUCKET_OAK);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_SPRUCE);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_BIRCH);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_JUNGLE);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_ACACIA);
        add(IntercraftItems.RUBBER_RESIN_BUCKET_DARK_OAK);
        add(IntercraftItems.RUBBER_RESIN_BUCKET);
        
        add(IntercraftItems.RESIN_BUCKET_OAK);
        add(IntercraftItems.RESIN_BUCKET_SPRUCE);
        add(IntercraftItems.RESIN_BUCKET_BIRCH);
        add(IntercraftItems.RESIN_BUCKET_JUNGLE);
        add(IntercraftItems.RESIN_BUCKET_ACACIA);
        add(IntercraftItems.RESIN_BUCKET_DARK_OAK);
        add(IntercraftItems.RESIN_BUCKET);
        
        add(IntercraftItems.WATER_BUCKET_OAK);
        add(IntercraftItems.WATER_BUCKET_SPRUCE);
        add(IntercraftItems.WATER_BUCKET_BIRCH);
        add(IntercraftItems.WATER_BUCKET_JUNGLE);
        add(IntercraftItems.WATER_BUCKET_ACACIA);
        add(IntercraftItems.WATER_BUCKET_DARK_OAK);

        add(IntercraftItems.LEAD_BOX);
        add(IntercraftItems.STEEL_BOX);

        add(IntercraftItems.COPPER_COIL);
        add(IntercraftItems.INSULATED_COPPER_COIL);
        add(IntercraftItems.ELECTRUM_COIL);
        add(IntercraftItems.INSULATED_ELECTRUM_COIL);
    }};

    /**
     * Pre-determined blocks to be coloured
     */

    public static List<Block> blocks = new ArrayList<Block>(){{
        add(IntercraftBlocks.AUTOCRAFTINGTABLE);
    }};


    @SubscribeEvent(priority = EventPriority.LOWEST)
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
                IntercraftItems.LITHIUM,
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
                IntercraftItems.ELECTRUM,
                IntercraftItems.STEEL
        };
        final ItemColoredGroup<?>[] coloredGroup = {
                IntercraftItems.LARGE_TINTED_GLASS_JAR,

                IntercraftItems.HAZMAT_HELMET,
                IntercraftItems.HAZMAT_CHESTPLATE,
                IntercraftItems.HAZMAT_PANTS,

                IntercraftItems.CROWBAR
        };


        /**
         * Filter out- and placing the group's content into the @items variable
         */

        for (ItemElementGroup group: groups) {
            for (Item item: new Item[] {group.INGOT, group.NUGGET, group.DUST, group.DUST_SMALL, group.PLATE, group.GEAR, group.ROD, group.CHUNK})
                if (item != null) items.add(item);
        }
        for (ItemColoredGroup<?> group : coloredGroup) { // Don't need to register white, as it should be unaffected.
            for (Item item : new Item[] {group.ORANGE, group.MAGENTA, group.LIGHT_BLUE, group.YELLOW, group.LIME, group.PINK, group.GRAY, group.LIGHT_GRAY, group.CYAN, group.PURPLE, group.BLUE, group.BROWN, group.GREEN, group.RED, group.BLACK})
                if (item != null) items.add(item);
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
            else if (item instanceof ItemBucketWood)
                event.getItemColors().register(new ItemColorHandler(((ItemBucketWood)item).getTint(),1),item);
            else if (item instanceof ItemBucketNonFluid)
                event.getItemColors().register(new ItemColorHandler(((ItemBucketNonFluid)item).getTint(),1),item);
            else if (item instanceof ItemHazMatSuit)
                event.getItemColors().register(new ItemColorHandler(((ItemHazMatSuit)item).getTint(),1),item);
            else if (item instanceof ItemCrowbar)
                event.getItemColors().register(new ItemColorHandler(((ItemCrowbar)item).getTint(),1),item);
            else if (item instanceof ItemSingleStackContainer && ((ItemSingleStackContainer)item).getTint() != -1) {
                if (item instanceof ItemSingleStackGlassContainer)
                    event.getItemColors().register(new ItemColorHandler(((ItemSingleStackContainer) item).getTint()), item);
                else
                    event.getItemColors().register(new ItemColorHandler(((ItemSingleStackContainer) item).getTint(), 0), item);
            }
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

        System.out.println("[Item Colours] Done");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
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
                IntercraftBlocks.LITHIUM,
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
                if (block != null)
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
            /*else if (block instanceof BlockCableCase)
                event.getBlockColors().register(new BlockColorHandler(((BlockCableCase) block).getTint()),block);*/



        }


        System.out.println("[Block Colours] Done");
    }


    private static class ItemColorHandler implements IItemColor
    {
        private final int tint,layer;

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
         * ColorHandler Constructor for all texture layers.
         *
         * @param tint colour to be tinted with.
         */
        public ItemColorHandler(int tint)
        {
            this(tint,-1);
        }

        @Override
        public int getColor(@Nonnull ItemStack stack, int tintIndex)
        {
            if (layer == -1)
                return tint;
            return layer == tintIndex ? tint : 0xFFFFFF;
        }
    }
    private static class BlockColorHandler implements IBlockColor, IItemColor
    {
        private final int tint;

        public BlockColorHandler(int tint)
        {
            this.tint = tint;
        }


        @Override
        public int getColor(@Nonnull ItemStack stack, int tintIndex)
        {
            return tint;
        }

        @Override
        public int getColor(BlockState blockState, @Nullable IEnviromentBlockReader iEnviromentBlockReader, @Nullable BlockPos blockPos, int tintIndex)
        {
            return tint;
        }
    }
}
