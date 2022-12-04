package tv.slicedlime.tradertest.mixin;
;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.WanderingTraderSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WanderingTraderSpawner.class)
public interface WanderingTraderManagerMixin {
	@Invoker("spawn")
	public boolean spawn(ServerLevel world);
}
