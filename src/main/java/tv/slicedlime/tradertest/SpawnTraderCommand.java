package tv.slicedlime.tradertest;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandRuntimeException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import net.minecraft.world.level.CustomSpawner;
import tv.slicedlime.tradertest.mixin.ServerWorldMixin;
import tv.slicedlime.tradertest.mixin.WanderingTraderManagerMixin;

import java.util.List;

public class SpawnTraderCommand {
    public static void register(CommandDispatcher dispatcher) {
        dispatcher.register(Commands.literal("spawntrader")
                .executes(
                    SpawnTraderCommand::run
                ));
    }

    public static int run(CommandContext<CommandSourceStack> context) {
        ServerLevel world = context.getSource().getLevel();
        WanderingTraderSpawner manager = getWanderingTraderManager(world);
        if (manager != null) {
            for (int i = 0; i < 1000; i++) {
                boolean success = ((WanderingTraderManagerMixin) manager).spawn(world);
                if (success) {
                    context.getSource().sendSuccess(Component.literal("Success"), false);
                    return 1;
                }
            }
            throw new CommandRuntimeException(Component.literal("All spawn attempts failed"));
        } else {
            throw new CommandRuntimeException(Component.literal("No spawner available"));
        }
    }

    static WanderingTraderSpawner getWanderingTraderManager(ServerLevel world) {
        List<CustomSpawner> spawners = ((ServerWorldMixin) world).getCustomSpawners();
        for (CustomSpawner spawner : spawners) {
            if (spawner instanceof WanderingTraderSpawner traderSpawner) {
                return traderSpawner;
            }
        }
        return null;
    }

}
