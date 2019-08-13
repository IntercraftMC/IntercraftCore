package net.intercraft.intercraftcore.api;

import net.minecraft.state.IntegerProperty;
import net.minecraft.state.EnumProperty;

public class BlockProperties
{   // If we divided it up into different mods, this would make it easier to have it here.
    public static final IntegerProperty DENSITY = IntegerProperty.create("density",0,3);
    //public static final IntegerProperty VOLUME = IntegerProperty.create("volume",0,4);
    public static final EnumProperty<BucketType> BUCKET = EnumProperty.create("bucket", BucketType.class);
    //public static final EnumProperty<FluidType> FLUIDTYPE = EnumProperty.create("fluid_type", FluidType.class);


}

