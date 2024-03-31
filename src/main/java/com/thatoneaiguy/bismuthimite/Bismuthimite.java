package com.thatoneaiguy.bismuthimite;

import com.thatoneaiguy.bismuthimite.init.BismuthimiteBlockEntities;
import com.thatoneaiguy.bismuthimite.init.BismuthimiteBlocks;
import com.thatoneaiguy.bismuthimite.init.BismuthimiteScreenHandlers;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bismuthimite implements ModInitializer {

	public static final String MODID = "bismuthimite";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize(ModContainer mod) {

		LOGGER.info("https://www.youtube.com/watch?v=vKslJFFS0ZE");

		BismuthimiteBlocks.register();

		BismuthimiteBlockEntities.register();

		BismuthimiteScreenHandlers.register();

	}
}
