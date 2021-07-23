package tv.slicedlime.tradertest;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class WanderingTraderTestMod implements ModInitializer {
	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			SpawnTraderCommand.register(dispatcher);
		});
	}
}
