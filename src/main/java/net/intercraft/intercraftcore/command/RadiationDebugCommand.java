package net.intercraft.intercraftcore.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.capabilities.radiation.IRadiation;
import net.intercraft.intercraftcore.init.capabilities.radiation.RadiationProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;

public class RadiationDebugCommand
{

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        System.out.println("Registering Radiation Debug command.");

        dispatcher.register(Commands.literal("radiation").requires((cmd) -> {
            return cmd.hasPermissionLevel(2);
        }).then(Commands.literal("get").then(Commands.argument("entity", EntityArgument.entity()).executes((cmd) -> {
            return get(cmd.getSource(),EntityArgument.getEntities(cmd, "entity"));
        }))).then(Commands.literal("set").then(Commands.literal("exposure").then(Commands.argument("value",IntegerArgumentType.integer(0,1000000)).then(Commands.argument("entity",EntityArgument.entity()).executes((cmd) -> {
            return set(cmd.getSource(),EntityArgument.getEntities(cmd, "entity"),"exposure",IntegerArgumentType.getInteger(cmd,"value"));
        })))).then(Commands.literal("absorbed").then(Commands.argument("value",IntegerArgumentType.integer(0,1000000)).then(Commands.argument("entity",EntityArgument.entity()).executes((cmd) -> {
            return set(cmd.getSource(),EntityArgument.getEntities(cmd, "entity"),"absorbed",IntegerArgumentType.getInteger(cmd,"value"));
        }))))));




    }

    private static int get(CommandSource source, Collection<? extends Entity> targets)
    {

        for (Entity entity : targets) {

            if (entity.getCapability(RadiationProvider.RAD_CAP).isPresent()) {

                IRadiation cap = entity.getCapability(RadiationProvider.RAD_CAP).orElse(RadiationProvider.RAD_CAP.getDefaultInstance());

                source.sendFeedback(new TranslationTextComponent("commands."+Reference.MODID+".radiation.get", cap.getExposure(), cap.getAbsorbed(), entity.getDisplayName()), false);

                } else {
                    source.sendErrorMessage(new TranslationTextComponent("commands."+Reference.MODID+".radiation.error.invalid",entity.getDisplayName()));
                    break;
                }
        }
        return targets.size();
    }

    private static int set(CommandSource source, Collection<? extends Entity> targets, String type, long value)
    {

        String typeL = type.toLowerCase();


        for (Entity entity : targets) {

            if (entity.getCapability(RadiationProvider.RAD_CAP).isPresent()) {

                IRadiation cap = entity.getCapability(RadiationProvider.RAD_CAP).orElse(RadiationProvider.RAD_CAP.getDefaultInstance());
                switch (typeL) {

                    case "exposure": {
                        cap.setExposure(value);
                        source.sendFeedback(new TranslationTextComponent("commands."+Reference.MODID+".radiation.exposure.set", value, entity.getDisplayName()), true);
                        break;
                    }
                    case "absorbed": {
                        cap.setAbsorbed(value);
                        source.sendFeedback(new TranslationTextComponent("commands."+Reference.MODID+".radiation.absorbed.set", value, entity.getDisplayName()), true);
                        break;
                    }
                    default:
                        source.sendErrorMessage(new TranslationTextComponent("commands."+Reference.MODID+".radiation.error.typo", type));
                }
            } else {
                source.sendErrorMessage(new TranslationTextComponent("commands."+Reference.MODID+".radiation.error.invalid",entity.getDisplayName()));
                break;
            }
        }
        return targets.size();
    }
}
