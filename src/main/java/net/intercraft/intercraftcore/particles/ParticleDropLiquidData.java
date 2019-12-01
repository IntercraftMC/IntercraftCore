package net.intercraft.intercraftcore.particles;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.intercraft.intercraftcore.init.IntercraftParticles;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;

import javax.annotation.Nonnull;
import java.util.Locale;

public class ParticleDropLiquidData implements IParticleData
{

    public final float maxAge;
    public final short r,g,b;



    public static ParticleDropLiquidData dropLiquid(short r, short g, short b)
    {
        return new ParticleDropLiquidData(30,r,g,b);
    }



    public ParticleDropLiquidData(float maxAge, short r, short g, short b)
    {
        this.maxAge = maxAge;
        this.r      = r;
        this.g      = g;
        this.b      = b;
    }


    @Override
    public void write(PacketBuffer buffer)
    {
        buffer.writeFloat(maxAge);
        buffer.writeShort(r);
        buffer.writeShort(g);
        buffer.writeShort(b);
    }

    @Override
    public String getParameters()
    {
        return String.format(Locale.ROOT,"%f %o %o %o",maxAge,r,g,b);
    }

    @Override
    public ParticleType<?> getType()
    {
        return IntercraftParticles.LIQUID_DRIP;
    }


    public static final IParticleData.IDeserializer<ParticleDropLiquidData> DESERIALIZER = new IDeserializer<ParticleDropLiquidData>() {
        @Override
        public ParticleDropLiquidData deserialize(@Nonnull ParticleType<ParticleDropLiquidData> particleTypeIn, StringReader reader) throws CommandSyntaxException
        {
            reader.expect(' ');
            float maxAge = reader.readFloat();
            reader.expect(' ');
            int r = reader.readInt();
            reader.expect(' ');
            int g = reader.readInt();
            reader.expect(' ');
            int b = reader.readInt();

            return new ParticleDropLiquidData(maxAge,(short)r,(short)g,(short)b);

        }

        @Override
        public ParticleDropLiquidData read(@Nonnull ParticleType<ParticleDropLiquidData> particleTypeIn, PacketBuffer buffer)
        {
            return new ParticleDropLiquidData(buffer.readFloat(),buffer.readShort(),buffer.readShort(),buffer.readShort());
        }
    };
}
