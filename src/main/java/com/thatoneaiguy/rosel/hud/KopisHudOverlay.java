package com.thatoneaiguy.rosel.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thatoneaiguy.rosel.Rosel;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KopisHudOverlay implements HudRenderCallback {
	public static final Identifier ROSEL_BAR_01 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_01");
	public static final Identifier ROSEL_BAR_02 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_02");
	public static final Identifier ROSEL_BAR_03 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_03");
	public static final Identifier ROSEL_BAR_04 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_04");
	public static final Identifier ROSEL_BAR_05 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_05");
	public static final Identifier ROSEL_BAR_06 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_06");
	public static final Identifier ROSEL_BAR_07 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_07");
	public static final Identifier ROSEL_BAR_08 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_08");
	public static final Identifier ROSEL_BAR_09 = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_09");

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
		//RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, ROSEL_BAR_09);

		DrawableHelper.drawTexture(matrixStack, x, ( y / 2 ) - 30, 20, 70, 0, 1, 140, 60,160, 100);
	}
}
