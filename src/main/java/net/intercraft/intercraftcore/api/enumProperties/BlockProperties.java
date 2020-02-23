package net.intercraft.intercraftcore.api.enumProperties;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.EnumProperty;

public class BlockProperties
{   // If we divided it up into different mods, this would make it easier to have it here.
    public static final IntegerProperty DENSITY = IntegerProperty.create("density",0,3);
    public static final IntegerProperty RS_OUTPUT_STRENGTH = IntegerProperty.create("output_strength",1,15);
    public static final EnumProperty<BucketType> BUCKET = EnumProperty.create("bucket", BucketType.class);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public static final EnumProperty<CableCaseFaces>
            CONNECTED_NORTH = EnumProperty.create("north", CableCaseFaces.class),
            CONNECTED_SOUTH = EnumProperty.create("south", CableCaseFaces.class),
            CONNECTED_EAST = EnumProperty.create("east", CableCaseFaces.class),
            CONNECTED_WEST = EnumProperty.create("west", CableCaseFaces.class),
            CONNECTED_UP = EnumProperty.create("up", CableCaseFaces.class),
            CONNECTED_DOWN = EnumProperty.create("down", CableCaseFaces.class);


    //public static final EnumProperty<FluidType> FLUIDTYPE = EnumProperty.create("fluid_type", FluidType.class);
    //public static final IntegerProperty VOLUME = IntegerProperty.create("volume",0,4);

}

