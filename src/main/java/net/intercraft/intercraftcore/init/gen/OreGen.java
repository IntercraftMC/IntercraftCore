package net.intercraft.intercraftcore.init.gen;

import net.intercraft.intercraftcore.init.IntercraftBlocks;
import net.intercraft.intercraftcore.ore.BlockProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;
import java.util.function.Predicate;

public class OreGen
{

    /*private static final Predicate<BlockState> IS_STONE = state -> state.getBlock() == Blocks.STONE;
    private static final Predicate<BlockState> IS_NETHERRACK = state -> state.getBlock() == Blocks.NETHERRACK;
    private static final Predicate<BlockState> IS_ENDSTONE = state -> state.getBlock() == Blocks.END_STONE;*/


    // Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
    private static final CountRangeConfig copper_ore_placement = new CountRangeConfig(7,20,20,100);
    private static final int copper_ore_veinsize = 8;

    public static void setupOreGen()
    {
        for (Biome biome : ForgeRegistries.BIOMES) {


            switch (biome.getCategory()) {

                case NETHER: { // Nether ores.

                    break;
                }

                case THEEND: { // End ores.

                    break;
                }

                case BEACH:
                case ICY:
                case OCEAN:
                case RIVER:
                case SWAMP:
                case TAIGA:
                case DESERT:
                case FOREST:
                case JUNGLE:
                case PLAINS:
                case SAVANNA:
                case MUSHROOM:
                case EXTREME_HILLS:
                case MESA: { // Overworld and other dimensions.
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                            IntercraftBlocks.COPPERORE.getDefaultState().with(BlockProperties.DENSITY, 3), copper_ore_veinsize), Placement.COUNT_RANGE, copper_ore_placement));

                    break;
                }



            }

        }
    }

    private static void registerOreEntry(Biome biome, BlockState state, int size, CountRangeConfig config)
    {

    }

}
