package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderBaseTileEntity;
import net.intercraft.intercraftcore.tileentity.ChunkLoaderTimerTileEntity;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.Arrays;

public class IntercraftTileEntities
{

    public static final TileEntityType<TreeTapTileEntity> TREETAP;
    public static final TileEntityType<ChunkLoaderBaseTileEntity> CHUNKLOADER;
    public static final TileEntityType<ChunkLoaderBaseTileEntity> CHUNKLOADER_TIMER;


    static {
        TREETAP =           buildTE("treetap",TileEntityType.Builder.create(TreeTapTileEntity::new,IntercraftBlocks.TREETAP));
        CHUNKLOADER =       buildTE("chunkloader",TileEntityType.Builder.create(ChunkLoaderBaseTileEntity::new,IntercraftBlocks.CHUNKLOADER,IntercraftBlocks.CHUNKLOADER_REDSTONE));
        CHUNKLOADER_TIMER = buildTE("chunkloader_timer",TileEntityType.Builder.create(ChunkLoaderTimerTileEntity::new,IntercraftBlocks.CHUNKLOADER_TIMER));
    }


    public static void register()
    {
        registerTileEntities(TREETAP,CHUNKLOADER,CHUNKLOADER_TIMER);
    }


    protected static void registerTileEntities(TileEntityType...tileEntityTypes)
    {
        RegistrationHandler.tileentities.addAll(Arrays.asList(tileEntityTypes));
    }

    private static TileEntityType buildTE(String registryName, TileEntityType.Builder builder)
    {
        /*Type<?> typeB = null;

        try {
            typeB = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(SharedConstants.getVersion().getWorldVersion())).getChoiceType(TypeReferences.BLOCK_ENTITY, registryName);
        } catch (IllegalArgumentException illegalstateexception) {
            if (SharedConstants.developmentMode) {
                throw illegalstateexception;
            }
        }*/


        TileEntityType type = builder.build(null);
        type.setRegistryName(Reference.MODID,registryName);
        return type;
    }



}
