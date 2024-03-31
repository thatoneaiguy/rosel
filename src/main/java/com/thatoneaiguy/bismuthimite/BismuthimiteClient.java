package com.thatoneaiguy.bismuthimite;

import com.thatoneaiguy.bismuthimite.init.BismuthimiteScreenHandlers;
import com.thatoneaiguy.bismuthimite.screen.KilnScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class BismuthimiteClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {

		HandledScreens.register(BismuthimiteScreenHandlers.KILN_SCREEN_HANDLER, KilnScreen::new);

	}
}
