package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class IntercraftFluids
{

    //private static final ForgeFlowingFluid.Properties still = properties(() -> FLUIDELEMENT, () -> FLOWING_FLUIDELEMENT);




    private static <T extends Fluid> T createFluid(String name, T fluid) {
        ResourceLocation id = new ResourceLocation(Reference.MODID,name);
        fluid.setRegistryName(id);
        //ForgeRegistries.FLUIDS.register(fluid);
        return fluid;
    }

    private static ForgeFlowingFluid.Properties properties(Supplier<Fluid> still, Supplier<Fluid> flowing)
    {
        //String tex = "block/" + name;
        return new ForgeFlowingFluid.Properties(still,flowing, FluidAttributes.builder(new ResourceLocation(Reference.MODID,"still"),new ResourceLocation(Reference.MODID,"flowing")));
    }
}
