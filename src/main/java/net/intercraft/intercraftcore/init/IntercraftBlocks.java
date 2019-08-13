package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.block.BlockCableCase;
import net.intercraft.intercraftcore.block.BlockTreeTap;
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

    /**
    * Ore Blocks with their drop
    * */

    /*public static final HardOre COPPERORE;
    public static final HardOre TINORE;*/


    public static final Block COPPERORE;
    public static final Block TINORE;
    public static final Block LEADORE;

    static {
        CABLECASE = new BlockCableCase();
        TREETAP = new BlockTreeTap();

        COPPERORE = new BlockOreCopper();
        TINORE = new BlockOreTin();
        LEADORE = new BlockOreLead();


    }

    /**
     * Register all block
     */
    public static void register()
    {
        registerBlock(CABLECASE, IntercraftItemGroups.WIRING);
        registerBlock(TREETAP, ItemGroup.TOOLS);

        //registerOre(COPPERORE);

        registerOreBlocks(COPPERORE,TINORE,LEADORE);
    }

    /**
     * Register a block
     */
    protected static void registerBlock(Block block, ItemGroup group)
    {
        RegistrationHandler.blocks.add(block);
        RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(group)).setRegistryName(block.getRegistryName()));
        //ItemBlock itemBlock = new ItemBlock(block,new Item.Properties());
    }

    /*protected static void registerOreBlock(Block block)
    {
        RegistrationHandler.blocks.add(block);
        RegistrationHandler.itemBlocks.add(new ItemBlockHardOre(block, new Item.Properties().group(IntercraftItemGroups.RESOURCES)).setRegistryName(block.getRegistryName()));
    }*/
    protected static void registerOreBlocks(Block...blocks)
    {

        for (Block block : blocks) {
            RegistrationHandler.blocks.add(block);
            RegistrationHandler.itemBlocks.add(new BlockItem(block, new Item.Properties().group(IntercraftItemGroups.RESOURCES)).setRegistryName(block.getRegistryName()));
        }
    }


    /*protected static void registerOre(HardOre ore) {

        registerOreBlock(ore.ORE);
        RegistrationHandler.items.add(ore.CHUNK);

    }*/
}