package com.thatoneaiguy.bismuthimite;

import com.thatoneaiguy.bismuthimite.init.*;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.UUID;

public class Bismuthimite implements ModInitializer {

	public static final String MODID = "bismuthimite";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static final ArrayList<UUID> soulmouldTheft = new ArrayList<>();

	@Override
	public void onInitialize(ModContainer mod) {

		LOGGER.info("https://www.youtube.com/watch?v=vKslJFFS0ZE");

		soulmouldTheft.add(UUID.fromString("9da4f059-fb15-4b34-a2e5-54cfecf7c22e"));

		BismuthimiteBlocks.register();

		BismuthimiteBlockEntities.register();

		BismuthimiteScreenHandlers.register();

		BismuthimiteEntities.register();

		BismuthimiteItems.register();

	}
}
