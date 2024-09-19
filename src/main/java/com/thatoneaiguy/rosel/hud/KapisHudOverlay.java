package com.thatoneaiguy.rosel.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thatoneaiguy.rosel.Rosel;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KapisHudOverlay implements HudRenderCallback {
	public static final Identifier NO_HITS_OF_SIX = new Identifier(Rosel.MODID,
		"textures/hud/no_hits_of_six");
	public static final Identifier ONE_HITS_OF_SIX = new Identifier(Rosel.MODID,
		"textures/hud/one_hits_of_six");
	public static final Identifier TWO_HITS_OF_SIX = new Identifier(Rosel.MODID,
		"textures/hud/two_hits_of_six");
	public static final Identifier THREE_HITS_OF_SIX = new Identifier(Rosel.MODID,
		"textures/hud/three_hits_of_six");
	public static final Identifier FOUR_HITS_OF_SIX = new Identifier(Rosel.MODID,
		"textures/hud/four_hits_of_six");
	public static final Identifier FIVE_HITS_OF_SIX = new Identifier(Rosel.MODID,
		"textures/hud/five_hits_of_six");
	public static final Identifier SIX_HITS_OF_SIX = new Identifier(Rosel.MODID,
		"textures/hud/six_hits_of_six");

	@Override
	public void onHudRender(MatrixStack matrixStack, float tickDelta) {
		int x = 0;
		int y = 0;
		MinecraftClient client = MinecraftClient.getInstance();

		if ( client != null ) {
			int width = client.getWindow().getScaledWidth();
			int height = client.getWindow().getScaledHeight();

			x = width / 2;
			y = height;
		}

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, NO_HITS_OF_SIX);

		DrawableHelper.drawTexture(matrixStack, x - 280, y - 100, 0, 0, 14, 4, 14, 4);
	}
}
