package net.intercraft.intercraftcore.wire;

public class Wire
{
    public final String name;
    public final float resistance;
    public final int tint;
    public final short thickness;
    public final boolean insulated;

    private float x1, y1, z1, x2, y2, z2;
    private boolean hasPos = false;

    /**
     * Wire Constructor
     *
     * @param name Name of the wire type.
     * @param tint The colour tint to apply.
     * @param thickness The width of the wire (mm).
     * @param insulated Whenever the wire is insulated or not.
     */

    // https://en.wikipedia.org/wiki/Wire_gauge

    public Wire(String name, float resistance, int tint, short thickness, boolean insulated)
    {
        this.name = insulated ? name + "_insulated" : name;
        this.resistance = resistance;
        this.tint = tint;
        this.thickness = thickness;
        this.insulated = insulated;
    }
    public Wire(String name, float resistance, int tint, short thickness)
    {
        this(name,resistance,tint,thickness,false);
    }

    public void setPos(float x1,float y1,float z1,float x2,float y2,float z2)
    {
        this.x1 = x1;this.y1 = y1;this.z1 = z1;
        this.x2 = x2;this.y2 = y2;this.z2 = z2;
        hasPos = true;
    }


    public void draw()
    {
        if (!hasPos) return;

        System.out.println(String.format("Drawing wire from [%f %f %f] to [%f %f %f]",x1,y1,z1,x2,y2,z2));
    }
}
