package net.intercraft.intercraftcore.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IIdentityHidden;
import net.intercraft.intercraftcore.init.capabilities.identity_hidden.IdentityHiddenProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Collection;

public class IdentityHiddenDebugCommand
{

    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        System.out.println("Registering Identity Debug command.");

        dispatcher.register(Commands.literal("identity").requires((cmd) -> {
            return cmd.hasPermissionLevel(2);
        }).then(Commands.literal("get").then(Commands.argument("player", EntityArgument.player()).executes((cmd) -> {
            return get(cmd.getSource(),EntityArgument.getPlayers(cmd, "player"));
        }))).then(Commands.literal("set").then(Commands.argument("value", BoolArgumentType.bool()).then(Commands.argument("player",EntityArgument.player()).executes((cmd -> {
            return set(cmd.getSource(),EntityArgument.getPlayers(cmd,"player"),BoolArgumentType.getBool(cmd,"value"));
        }))))));


    }

    private static int get(CommandSource source, Collection<? extends Entity> targets)
    {

        for (Entity entity : targets) {

            IIdentityHidden hidden = entity.getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);

            source.sendFeedback(new TranslationTextComponent("commands."+Reference.MODID+".identity." + (hidden.getHidden() ? "hidden" : "visible"), entity.getDisplayName()),false);


        }
        return targets.size();
    }

    private static int set(CommandSource source, Collection<? extends Entity> targets, boolean value)
    {
        for (Entity entity : targets) {

            IIdentityHidden hidden = entity.getCapability(IdentityHiddenProvider.HID_CAP).orElseThrow(NullPointerException::new);

            source.sendFeedback(new TranslationTextComponent("commands."+Reference.MODID+".identity.set." + (value ? "hidden" : "visible"), entity.getDisplayName(), value),false);
            hidden.setHidden((PlayerEntity) entity,value);


        }
        return targets.size();
    }
}
