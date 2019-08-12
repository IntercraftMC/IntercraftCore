package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.tileentity.TreeTapTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class IntercraftTileEntities
{

    public static final TileEntityType<TreeTapTileEntity> TREETAP;


    static {
        TREETAP = buildTE("treetap",TileEntityType.Builder.create(TreeTapTileEntity::new,IntercraftBlocks.TREETAP));
    }



    public static void register()
    {
        registerTileEntity(TREETAP);
    }


    protected static void registerTileEntity(TileEntityType tileEntityType)
    {
        RegistrationHandler.tileentities.add(tileEntityType);
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
