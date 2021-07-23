package tv.slicedlime.tradertest;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.world.WanderingTraderManager;
import net.minecraft.world.gen.Spawner;
import org.jetbrains.annotations.Nullable;
import tv.slicedlime.tradertest.mixin.ServerWorldMixin;
import tv.slicedlime.tradertest.mixin.WanderingTraderManagerMixin;

import java.util.List;

public class SpawnTraderCommand {
    public static void register(CommandDispatcher dispatcher) {
        dispatcher.register(CommandManager.literal("spawntrader")
                .executes(
                    SpawnTraderCommand::run
                ));
    }

    public static int run(CommandContext<ServerCommandSource> context) {
        ServerWorld world = context.getSource().getWorld();
        WanderingTraderManager manager = getWanderingTraderManager(world);
        if (manager != null) {
            for (int i = 0; i < 1000; i++) {
                boolean success = ((WanderingTraderManagerMixin) manager).trySpawn(world);
                if (success) {
                    context.getSource().sendFeedback(new LiteralText("Success"), false);
                    return 1;
                }
            }
            throw new CommandException(new LiteralText("All spawn attempts failed"));
        } else {
            throw new CommandException(new LiteralText("No spawner available"));
        }
    }

    @Nullable
    static WanderingTraderManager getWanderingTraderManager(ServerWorld world) {
        List<Spawner> spawners = ((ServerWorldMixin) world).getSpawners();
        for (Spawner spawner : spawners) {
            if (spawner instanceof WanderingTraderManager) {
                return (WanderingTraderManager) spawner;
            }
        }
        return null;
    }

}
