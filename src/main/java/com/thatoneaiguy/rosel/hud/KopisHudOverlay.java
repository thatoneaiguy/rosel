package com.thatoneaiguy.rosel.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.RoselClient;
import com.thatoneaiguy.rosel.RoselConfig;
import com.thatoneaiguy.rosel.cca.KopisComponent;
import com.thatoneaiguy.rosel.init.RoselItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KopisHudOverlay implements HudRenderCallback {

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
		//RenderSystem.setShaderTexture(0, RoselClient.roselKopisHUDTextureSelecter());


		assert client != null;
		assert client.player != null;

		if ( client.player.isHolding(RoselItems.ROSEL_KOPIS) ) {
			if (RoselConfig.comboBarLocation == RoselConfig.ComboBarLocation.CROSSHAIR) {
				DrawableHelper.drawTexture(matrixStack, x, (y / 2) - 30, 2, 7, 0, 0, 14, 6, 16, 10);
			} else {
				// work out the values for hotabr, must b eon the left of the hptbar so we don't interfere with the hit cooldown on the right
			}
		}
	}
}
