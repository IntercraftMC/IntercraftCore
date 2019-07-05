package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.BlockCableCase;
import net.intercraft.intercraftcore.ore.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;

public class IntercraftBlocks
{
    /**
     * Instantiate all block
     */

    public static final Block CABLECASE;

    /**
    * Ore Blocks with their drop
    * */

    /*public static final HardOre COPPERORE;
    public static final HardOre TINORE;*/


    public static final Block COPPERORE;
    public static final Block TINORE;

    static {
        CABLECASE = new BlockCableCase();
        COPPERORE = new BlockOreCopper();
        TINORE = new BlockOreTin();
        /*COPPERORE = new Copper();
        TINORE = new Copper();*/



    }

    /**
     * Register all block
     */
    public static void register()
    {
        registerBlock(CABLECASE, IntercraftItemGroups.WIRING);

        //registerOre(COPPERORE);

        registerOreBlock(COPPERORE);
        registerOreBlock(TINORE);
    }

    /**
     * Register a block
     */
    protected static void registerBlock(Block block, ItemGroup group)
    {
        RegistrationHandler.blocks.add(block);
        RegistrationHandler.itemBlocks.add(new ItemBlock(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName()));
        //ItemBlock itemBlock = new ItemBlock(block,new Item.Properties());
    }

    protected static void registerOreBlock(Block block) {
        RegistrationHandler.blocks.add(block);
        RegistrationHandler.itemBlocks.add(new ItemBlockHardOre(block, new Item.Properties().group(IntercraftItemGroups.RESOURCES)).setRegistryName(block.getRegistryName()));
    }


    /*protected static void registerOre(HardOre ore) {

        registerOreBlock(ore.ORE);
        RegistrationHandler.items.add(ore.CHUNK);

    }*/
}