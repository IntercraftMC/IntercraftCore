package net.intercraft.intercraftcore.api;

public class Util
{

    /**
     * Clockwise rotation
     *
     * Anti-Clockwise rotation
     */

    public static final float[][]
            TCW2D = {
            {0,-1},
            {1,0}
    },
            TACW2D = {
            {0,1},
            {-1,0}
    };

    /**
     * Generate 2D rotation Matrix
     *
     * @param d Degrees to generate matrix from.
     * @return Double-Array Matrix
     */

    public static float[][] generate2DMatrix(float d)
    {
        return new float[][] {
                {(float)Math.cos(d),(float)-Math.sin(d)},
                {(float)Math.sin(d),(float)Math.cos(d)}
        };
    }

    /**
     * Generate 3D rotation Matrix
     *
     * @param d Degrees to generate matrix from.
     * @param axis What axis to generate matrix from (x,y,z).
     * @return Double-Array Matrix
     */

    public static float[][] generate3DMatrix(float d, char axis)
    {
        switch (axis) {
            case 'x':
                return new float[][] {
                        {1, 0, 0},
                        {0, (float)Math.cos(d), (float)-Math.sin(d)},
                        {0, (float)Math.sin(d), (float)Math.cos(d)}
                };
            case 'z':
                return new float[][] {
                        {(float)Math.cos(d), 0, (float)Math.sin(d)},
                        {0, 1, 0},
                        {(float)-Math.sin(d), 0, (float)Math.cos(d)}
                };
            default: // 'y'
                return new float[][] {
                        {(float)Math.cos(d), (float)-Math.sin(d), 0},
                        {(float)Math.sin(d), (float)Math.cos(d), 0},
                        {0, 0, 1}
                };
        }
    }

    /**
     * Rotate 3D coordinates from Matrix
     *
     * @param matrix Rotation matrix to be used.
     * @return New transformed coordinates.
     */

    public static float[] rotate3D(float x, float y, float z, float[][] matrix)
    {
        return new float[] {matrix[0][0]*x+matrix[0][1]*y+matrix[0][2]*z,matrix[1][0]*x+matrix[1][1]*y+matrix[1][2]*z,matrix[2][0]*x+matrix[2][1]*y+matrix[2][2]*z};
    }

    public static short[] rotate3D(short x, short y, short z, float[][] matrix)
    {
        final float[] c = rotate3D((float)x,y,z,matrix);
        return new short[] {(short)Math.round(c[0]),(short)Math.round(c[1]),(short)Math.round(c[2])};
    }


    /**
     * Rotate 2D coordinates from Matrix in Y axis
     *
     * @param matrix Rotation matrix to be used.
     * @return New transformed coordinates.
     */

    public static float[] rotateY(float x, float y, float z, float[][] matrix)
    {
        return new float[] {matrix[0][0]*x+matrix[0][1]*z,y,matrix[1][0]*x+matrix[1][1]*z};
    }

    public static short[] rotateY(short x,short y,short z, float[][] matrix)
    {
        final float[] c = rotateY((float)x,y,z,matrix);
        return new short[] {(short)Math.round(c[0]),(short)Math.round(c[1]),(short)Math.round(c[2])};
    }

    /**
     * Rotate 2D coordinates from degrees in Y axis
     *
     * @param d Degrees to generate matrix from.
     * @return New transformed coordinates.
     */

    public static float[] rotateY(float x, float y, float z, float d)
    {
        return rotateY(x,y,z,generate2DMatrix(d));
    }

    public static short[] rotateY(short x, short y, short z, float d)
    {
        return rotateY(x,y,z,generate2DMatrix(d));
    }

    /**
     * Rotate coordinates from Matrix in X axis
     *
     * @param matrix Rotation matrix to be used.
     * @return New transformed coordinates.
     */

    public static float[] rotateX(float x,float y,float z,float[][] matrix)
    {
        //return new float[] {matrix[0][0]*x+matrix[0][1]*z,y,matrix[1][0]*x+matrix[1][1]*z};
        return new float[] {x,matrix[0][0]*y+matrix[0][1]*z,matrix[1][0]*y+matrix[1][1]*z};
    }

    /**
     * Rotate coordinates from degrees in Y axis
     *
     * @param d Degrees to generate matrix from.
     * @return New transformed coordinates.
     */

    public static float[] rotateX(float x,float y,float z, float d)
    {
        return rotateX(x,y,z, generate2DMatrix(d));
    }



    /**
     * Convert Hex to rgb
     *
     * @param hex color hex value.
     * @return int array (red,green,blue) 0-255.
     */

    public static int[] hex2rgb(int hex)
    {
        int r = (hex & 0xFF0000) >> 16, g = (hex & 0xFF00) >> 8, b = (hex & 0xFF);
        return new int[]{r,g,b};
    }

    /**
     * Convert rgb to Hex
     *
     * @param rgb int array (red,green,blue,0-255).
     * @return int hex value.
     */

    public static int rgb2hex(int[] rgb)
    {
        String[] c = new String[rgb.length];
        StringBuilder builder;
        for (byte i=0;i<rgb.length;i++) {
            builder = new StringBuilder(Integer.toHexString(rgb[i]));
            while (builder.length() < 2) {
                builder.append('0');

            }
            c[i] = builder.toString().toUpperCase();
        }

        return Integer.parseInt(String.format("%s%s%s",c[0],c[1],c[2]),16);
    }

    /*public static float[] rgb2hsv(int[] rgb)
    {
        float computedH = 0,computedS = 0,computedV = 0;
        final float r = rgb[0]/255f,g = rgb[1]/255f,b = rgb[2]/255f;

        float minRGB = Math.min(r,Math.min(g,b));
        float maxRGB = Math.min(r,Math.min(g,b));

        if (minRGB == maxRGB)
            return new float[] {0,0,minRGB};

        float d = (r == minRGB) ? g-b : ((b==minRGB) ? r-g : b-r);
        float h = (r==minRGB) ? 3 : ((b==minRGB) ? 1 : 5);
        computedH = 60*(h - d/(maxRGB - minRGB));
        computedS = (maxRGB - minRGB)/maxRGB;
        computedV = maxRGB;

        return new float[] {computedH,computedS,computedV};

    }

    public static int[] hsv2rgb(float[] hsv)
    {
        int h = (int)(hsv[0] * 6);
        float f = hsv[0] * 6 - h;
        float p = hsv[2] * (1 - hsv[1]);
        float q = hsv[2] * (1 - f * hsv[1]);
        float t = hsv[2] * (1 - (1 - f) * hsv[1]);

        switch (h) {
            case 0: return new int[]{ (int) hsv[2]*256, (int) t*256,       (int) p*256};
            case 1: return new int[]{ (int) q*256,      (int) hsv[2]*256,  (int) p*256};
            case 2: return new int[]{ (int) p*256,      (int) hsv[2],      (int) t};
            case 3: return new int[]{ (int) p*256,      (int) q*256,       (int) hsv[2]*256};
            case 4: return new int[]{ (int) t*256,      (int) p*256,       (int) hsv[2]*256};
            case 5: return new int[]{ (int) hsv[2]*256, (int) p*256,       (int) q*256};
            default: throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hsv[0] + ", " + hsv[1] + ", " + hsv[2]);
        }

    }*/

    /**
     * Created Nov 17, 2010 by Andrew Butler, PSL
     * Lightens a colour by a given amount
     *
     * @param hex The colour to lighten.
     * @param amount The amount to lighten the color. 0 will leave the color unchanged; 1 will make the color completely white.
     * @return The bleached colour.
     */

    public static int bleach(int hex, float amount)
    {
        int[] c = Util.hex2rgb(hex);
        c[0] = (int) ((c[0] * (1 - amount) / 255 + amount) * 255);
        c[1] = (int) ((c[1] * (1 - amount) / 255 + amount) * 255);
        c[2] = (int) ((c[2] * (1 - amount) / 255 + amount) * 255);
        return Util.rgb2hex(c);
    }

}
