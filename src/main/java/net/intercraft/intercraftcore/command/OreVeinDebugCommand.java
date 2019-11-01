package net.intercraft.intercraftcore.command;

import com.mojang.brigadier.CommandDispatcher;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.IOreVeins;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.OreVeinProvider;
import net.intercraft.intercraftcore.init.capabilities.ore_veins.VeinTypes;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.chunk.Chunk;

import java.util.Arrays;

public class OreVeinDebugCommand
{



    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        System.out.println("Registering Ore Vein Debug command.");

        dispatcher.register(Commands.literal("ores").requires((cmd) -> {
            return cmd.hasPermissionLevel(2);
        }).then(Commands.literal("all").executes((cmd) -> {
            return all(cmd.getSource());
        })));
    }




    private static int all(CommandSource source)
    {
        Chunk chunk = source.getWorld().getChunk(source.getEntity().chunkCoordX,source.getEntity().chunkCoordZ);
        IOreVeins cap = chunk.getCapability(OreVeinProvider.VEIN_CAP).orElse(OreVeinProvider.VEIN_CAP.getDefaultInstance());

        Arrays.asList(VeinTypes.values()).forEach((vein) -> {

            source.sendFeedback(new TranslationTextComponent("commands."+Reference.MODID+".orevein.get",cap.getName(vein),cap.getWeight(vein),source.getEntity().chunkCoordX,source.getEntity().chunkCoordZ), false);
        });

        return 2;
    }
}
