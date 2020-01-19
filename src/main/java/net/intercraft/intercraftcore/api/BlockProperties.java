package net.intercraft.intercraftcore.api;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.EnumProperty;

import java.util.HashMap;
import java.util.Map;

public class BlockProperties
{   // If we divided it up into different mods, this would make it easier to have it here.
    public static final IntegerProperty DENSITY = IntegerProperty.create("density",0,3);
    public static final IntegerProperty RS_OUTPUT_STRENGTH = IntegerProperty.create("output_strength",1,15);
    public static final EnumProperty<BucketType> BUCKET = EnumProperty.create("bucket", BucketType.class);
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public static final BooleanProperty
            CONNECTED_NORTH = BooleanProperty.create("north"),
            CONNECTED_SOUTH = BooleanProperty.create("south"),
            CONNECTED_EAST = BooleanProperty.create("east"),
            CONNECTED_WEST = BooleanProperty.create("west"),
            CONNECTED_UP = BooleanProperty.create("up"),
            CONNECTED_DOWN = BooleanProperty.create("down");


    //public static final EnumProperty<FluidType> FLUIDTYPE = EnumProperty.create("fluid_type", FluidType.class);
    //public static final IntegerProperty VOLUME = IntegerProperty.create("volume",0,4);

}

