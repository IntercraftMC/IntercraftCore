package net.intercraft.intercraftcore.api;

public class Util
{
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
