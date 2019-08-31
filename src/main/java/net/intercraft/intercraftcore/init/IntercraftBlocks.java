package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.*;
import net.intercraft.intercraftcore.ore.ItemBlockHardOre;
import net.intercraft.intercraftcore.ore.elements.BlockOreCopper;
import net.intercraft.intercraftcore.ore.elements.BlockOreLead;
import net.intercraft.intercraftcore.ore.elements.BlockOreTin;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class IntercraftBlocks
{
    /**
     * Instantiate all block
     */

    public static final Block CABLECASE;
    public static final Block TREETAP;

    public static final Block CHUNKLOADER;
    public static final Block CHUNKLOADER_REDSTONE;
    public static final Block CHUNKLOADER_TIMER;

    /**
    * Ore Blocks
    * */

    public static final Block COPPERORE;
    public static final Block TINORE;
    public static final Block LEADORE;

    static {
        CABLECASE = new BlockCableCase();
        TREETAP = new BlockTreeTap();

        CHUNKLOADER = new BlockChunkloader("chunkloader");
        CHUNKLOADER_REDSTONE = new BlockChunkloaderRedstone();
        CHUNKLOADER_TIMER = new BlockChunkloaderTimer();

        COPPERORE = new BlockOreCopper();
        TINORE = new BlockOreTin();
        LEADORE = new BlockOreLead();


    }

    /**
     * Register all block
     */
    public static void register()
    {
        registerBlocks(IntercraftItemGroups.WIRING,true,CABLECASE);
        registerBlocks(ItemGroup.TOOLS,true,TREETAP);
        registerBlocks(IntercraftItemGroups.MACHINE,true,CHUNKLOADER,CHUNKLOADER_REDSTONE,CHUNKLOADER_TIMER);

        //registerOre(COPPERORE);

        registerOreBlocks(COPPERORE,TINORE,LEADORE);
    }

    /**
     * Register a block(s)
     */
    protected static void registerBlocks(ItemGroup group, final boolean item,Block...blocks)
    {

        for (Block block : blocks) {

            RegistrationHandler.blocks.add(block);
            if (item)
                RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName()));
        }
    }

    protected static void registerOreBlocks(Block...blocks)
    {

        for (Block block : blocks) {
            RegistrationHandler.blocks.add(block);
            //RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(IntercraftItemGroups.RESOURCES)).setRegistryName(block.getRegistryName()));
            RegistrationHandler.itemBlocks.add(new ItemBlockHardOre(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
        }
    }
}