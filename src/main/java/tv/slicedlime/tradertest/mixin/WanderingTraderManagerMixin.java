package tv.slicedlime.tradertest.mixin;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WanderingTraderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WanderingTraderManager.class)
public interface WanderingTraderManagerMixin {
	@Invoker("trySpawn")
	public boolean trySpawn(ServerWorld world);
}
