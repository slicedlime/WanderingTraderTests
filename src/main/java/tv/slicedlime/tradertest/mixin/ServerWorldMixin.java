package tv.slicedlime.tradertest.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WanderingTraderManager;
import net.minecraft.world.gen.Spawner;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ServerWorld.class)
public interface ServerWorldMixin {
    @Accessor
    List<Spawner> getSpawners();
}
