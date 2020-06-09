package net.intercraft.intercraftcore.api;

import net.intercraft.intercraftcore.Authors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class UtilBlocks
{

    /**
     * Bit flags for the directions.
     */

    public static final byte
            NORTH = 0x1,
            EAST  = 0x2,
            SOUTH = 0x4,
            WEST  = 0x8,
            UP    = 0x10,
            DOWN  = 0x20;


    /**
     * Generate cuboid shape(s) values
     *
     * @param exclude Bit flags for directions to exclude.
     * @return 6x6 double array.
     */

    public static double[][] generateDirectionShapesValues(int exclude, double x1, double y1, double z1, double x2, double y2, double z2)
    {
        double[][] t = new double[6][6];
        Vec3d
                k1 = new Vec3d(x1 - 8, y1 - 8, z1 - 8),
                k2 = new Vec3d(x2 - 8, y2 - 8, z2 - 8);

        byte o = 1;

        for (byte i=0;i<6;i++) {

            if (!((exclude & (1 << i)) == 0)) {
                o++;
                continue;
            }

            if (i < 4) {
                k1 = k1.rotateYaw(o * (float) -Math.PI / 2f);
                k2 = k2.rotateYaw(o * (float) -Math.PI / 2f);
            } else {
                if (o > 1) {
                    k1 = k1.rotateYaw(o * (float) -Math.PI / 2f);
                    k2 = k2.rotateYaw(o * (float) -Math.PI / 2f);
                }
                k1 = k1.rotatePitch((i - 3) * (float) Math.PI / 2f);
                k2 = k2.rotatePitch((i - 3) * (float) Math.PI / 2f);
            }

            if (o > 1) o = 1;
            t[i][0] = k1.x + 8;
            t[i][1] = k1.y + 8;
            t[i][2] = k1.z + 8;

            t[i][3] = k2.x + 8;
            t[i][4] = k2.y + 8;
            t[i][5] = k2.z + 8;

        }

        return t;
    }

    public static double[][] generateDirectionShapesValues(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        return generateDirectionShapesValues(0,x1,y1,z1,x2,y2,z2);
    }

    /**
     * Generate cuboid SHAPE(s) (start from West)
     *
     * @param exclude Bit flags for directions to exclude.
     * @return 6 length VoxelShape array.
     */

    public static VoxelShape[] generateDirectionShapes(int exclude, double x1, double y1, double z1, double x2, double y2, double z2)
    {
        final double[][] j = generateDirectionShapesValues(exclude,x1,y1,z1,x2,y2,z2);
        VoxelShape[] t = new VoxelShape[j.length];

        for (int i=0;i<j.length;i++)
            t[i] = Block.makeCuboidShape(j[i][0],j[i][1],j[i][2],j[i][3],j[i][4],j[i][5]);

        return t;
    }

    public static VoxelShape[] generateDirectionShapes(double x1, double y1, double z1, double x2, double y2, double z2)
    {
        return generateDirectionShapes(0,x1,y1,z1,x2,y2,z2);
    }

    public static VoxelShape[] assembleShapes(double[][] values)
    {
        VoxelShape[] t = new VoxelShape[values.length];

        for (int i=0;i<values.length;i++)
            t[i] = Block.makeCuboidShape(values[i][0],values[i][1],values[i][2],values[i][3],values[i][4],values[i][5]);
        return t;
    }

    public static VoxelShape[] generateDirectionShapes(int exclude, double[] values)
    {
        return generateDirectionShapes(exclude,values[0],values[1],values[2],values[3],values[4],values[5]);
    }

    public static VoxelShape[] generateDirectionShapes(double[] values)
    {
        return generateDirectionShapes(0,values);
    }

    /**
     * Get TileEntity at position
     *
     * @param type    Class to cast to.
     * @param worldIn World
     * @param stateIn Supply BlockState straight away.
     * @param pos     BlockPos of target block.
     * @return TileEntity or null.
     */

    public static <T extends TileEntity> T getTileEntity(Class<T> type, World worldIn, BlockState stateIn, BlockPos pos)
    {
        BlockState state = stateIn == null ? worldIn.getBlockState(pos) : stateIn;
        TileEntity te = state.getBlock().hasTileEntity(state) ? worldIn.getTileEntity(pos) : null;
        return (T)te;
    }

    public static <T extends TileEntity> T getTileEntity(Class<T> type, World worldIn, BlockPos pos)
    {
        return getTileEntity(type,worldIn,null,pos);
    }

    /**
     * Get TileEntity at position or throw NullPointerException
     *
     * @param type    Class to cast to.
     * @param worldIn World
     * @param stateIn Supply BlockState straight away.
     * @param pos     BlockPos of target block.
     * @return TileEntity or null.
     */

    public static <T extends TileEntity> T getTileEntityThrowable(Class<T> type, World worldIn, BlockState stateIn, BlockPos pos)
    {
        T te = getTileEntity(type,worldIn,stateIn,pos);
        if (te == null) throw new NullPointerException(String.format("Could not find %s! %s",type.getSimpleName(), Authors.SIMON.fault()));
        return te;
    }

    public static <T extends TileEntity> T getTileEntityThrowable(Class<T> type, World worldIn, BlockPos pos)
    {
        return getTileEntityThrowable(type,worldIn,null,pos);
    }

    /**
     * Send a message to the player checking client-server data sync
     *
     * @param message        Data message.
     * @param player         Player to compare to.
     * @param playerReceiver Player message receiver.
     */

    public static void isSyncedTest(String message, PlayerEntity player, PlayerEntity playerReceiver)
    {
        if (player.getEntityWorld().isRemote)
            playerReceiver.sendMessage(new StringTextComponent("[CLIENT] "+message));
        else
            playerReceiver.sendMessage(new StringTextComponent("[SERVER] "+message));
    }

    /**
     * Send a message to the player checking client-server data sync
     *
     * @param message Data message.
     * @param player  Player to compare to and message receiver.
     */

    public static void isSyncedTest(String message, PlayerEntity player)
    {
        isSyncedTest(message,player,player);
    }

    /**
     * Convert int to fractal
     *
     * @param c       Values.
     * @param fractal The fractal to convert to.
     */

    public static float[] toFractal(int[] c, float fractal)
    {
        return new float[]{c[0]/fractal,c[1]/fractal,c[2]/fractal};
    }


    public enum Connections
    {
        NORTH((byte) 3),
        SOUTH((byte) 1),
        EAST((byte) 0),
        WEST((byte) 2),
        UP((byte) 5),
        DOWN((byte) 4);


        private final byte value;
        private static final Connections DEFAULT = NORTH;

        /**
         * Sides (connections) interpreted as numbers
         *
         * @param value The id value.
         */

        Connections(byte value)
        {
            this.value = value;
        }

        public byte getValue()
        {
            return value;
        }

        public Connections getOpposite()
        {
            switch (this) {
                case NORTH: return SOUTH;
                case SOUTH: return NORTH;
                case EAST: return WEST;
                case WEST: return EAST;
                case UP: return DOWN;
                case DOWN: return UP;
            }
            return DEFAULT;
        }

        /**
         * Get four neighboring sides as an array
         */
        
        public Connections[] getNeighbors()
        {
            switch (this) {
                case WEST: case EAST:
                    return new Connections[] {NORTH, SOUTH, UP, DOWN};
                case UP: case DOWN:
                    return new Connections[] {WEST, NORTH, EAST, SOUTH};
                default: // North + South
                    return new Connections[] {WEST, EAST, UP, DOWN};
            }
        }

        public static Connections getConnectionFromValue(byte value)
        {
            for (Connections c : Connections.values())
                if (c.getValue() == value)
                    return c;
            return DEFAULT;
        }
    }
}
