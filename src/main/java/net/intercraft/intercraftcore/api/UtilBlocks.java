package net.intercraft.intercraftcore.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;

public class UtilBlocks
{

    public static final byte
            WEST  = 0x1,
            NORTH = 0x2,
            EAST  = 0x4,
            SOUTH = 0x8,
            UP    = 0x10,
            DOWN  = 0x20;


    /**
     * Generate cuboid shape(s) (start from West)
     *
     * @param exclude Bit flags for directions to exclude.
     * @return 6 length VoxelShape array.
     */

    public static VoxelShape[] generateDirectionShapes(int exclude, int x1, int y1, int z1, int x2, int y2, int z2)
    {
        VoxelShape[] t = new VoxelShape[6];

        Vec3d k1 = new Vec3d(x1 - 8, y1 - 8, z1 - 8);
        Vec3d k2 = new Vec3d(x2 - 8, y2 - 8, z2 - 8);

        byte o = 1;

        // West, North, East, South, Top, Bottom
        for (byte i=0;i<6;i++) {

            if (!((exclude & (1 << i)) == 0)) {
                o += 1;
                continue;
            }

            if (i > 0) {
                if (i < 4) {
                    k1 = k1.rotateYaw(o * (float) -Math.PI / 2f);
                    k2 = k2.rotateYaw(o * (float) -Math.PI / 2f);
                } else {
                    k1 = k1.rotatePitch((i - 3) * (float) Math.PI / 2f);
                    k2 = k2.rotatePitch((i - 3) * (float) Math.PI / 2f);
                }
            }
            if ((exclude & (1 << i)) == 0) {
                if (o > 1) o = 1;
                t[i] = Block.makeCuboidShape(k1.x + 8, k1.y + 8, k1.z + 8, k2.x + 8, k2.y + 8, k2.z + 8);
            }

        }
        return t;
    }

    public static VoxelShape[] generateDirectionShapes(int x1, int y1, int z1, int x2, int y2, int z2)
    {
        return generateDirectionShapes(0,x1,y1,z1,x2,y2,z2);
    }

    /**
     * Send a message to the player checking client-server data sync
     *
     * @param message Data message.
     * @param player Player to compare to.
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
     * @param player Player to compare to and message receiver.
     */

    public static void isSyncedTest(String message, PlayerEntity player)
    {
        isSyncedTest(message,player,player);
    }

    /**
     * Convert int to fractal
     *
     * @param c Values.
     * @param fractal The fractal to convert to.
     */

    public static float[] toFractal(int[] c, float fractal)
    {
        return new float[]{c[0]/fractal,c[1]/fractal,c[2]/fractal};
    }

    private static byte getCode(byte connection)
    {
        switch (connection) {
            case 0:
                return WEST;
            case 2:
                return EAST;
            case 3:
                return SOUTH;
            case 4:
                return UP;
            case 5:
                return DOWN;
            default: // 1
                return NORTH;
        }
    }


    public enum Connections
    {
        NORTH((byte) 1),
        SOUTH((byte) 3),
        EAST((byte) 2),
        WEST((byte) 0),
        UP((byte) 4),
        DOWN((byte) 5);


        private final byte value;

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
                default: return NORTH;

            }
        }
        
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
            return NORTH;
        }
    }
}
