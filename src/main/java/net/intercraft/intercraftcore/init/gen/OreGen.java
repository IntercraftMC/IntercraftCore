package net.intercraft.intercraftcore.init.gen;

import net.intercraft.intercraftcore.init.IntercraftBlocks;
import net.intercraft.intercraftcore.api.BlockProperties;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class OreGen
{

    /*private static final Predicate<BlockState> IS_STONE = state -> state.getBlock() == Blocks.STONE;
    private static final Predicate<BlockState> IS_NETHERRACK = state -> state.getBlock() == Blocks.NETHERRACK;
    private static final Predicate<BlockState> IS_ENDSTONE = state -> state.getBlock() == Blocks.END_STONE;*/


    private static Random random = new Random();

    // Vein/Chunk Count, MinHeight, MaxHeightBase, MaxHeight
    private static final int copper_ore_veinsize = 10;
    private static final CountRangeConfig copper_ore_placement = new CountRangeConfig(77/copper_ore_veinsize,1,31,63);

    public static void setupOreGen()
    {
        for (Biome biome : ForgeRegistries.BIOMES) {


            switch (biome.getCategory()) {

                case NETHER: { // Nether biome.

                    break;
                }

                case THEEND: { // End biome.

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
                case MESA: { // Overworld biomes.
                    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                            IntercraftBlocks.COPPER.ORE.getDefaultState().with(BlockProperties.DENSITY, random.nextInt(4)), copper_ore_veinsize), Placement.COUNT_RANGE, copper_ore_placement));

                    break;
                }



            }

        }
    }

    private static void registerOreEntry(Biome biome, BlockState state, int size, CountRangeConfig config)
    {

    }

}
