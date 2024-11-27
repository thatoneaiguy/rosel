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

import java.util.random.RandomGenerator;

public class KopisHudOverlay implements HudRenderCallback {
	/*private static final Identifier TEXTURE = new Identifier(Rosel.MODID,
		"textures/hud/rosel_bar_1.png");*/

	MinecraftClient client = MinecraftClient.getInstance();

	@Override
	public void onHudRender(MatrixStack matrixStack, float tickDelta) {
		int scaleFactor = 1;
		int textureWidth = 16 * scaleFactor;
		int textureHeight = 10 * scaleFactor;
		int padding = 5;

		if (client != null) {
			if ( client.player != null) {
				int screenWidth = client.getWindow().getScaledWidth();
				int screenHeight = client.getWindow().getScaledHeight();
				double scale = client.getWindow().getScaleFactor();

				int x, y;

				if (RoselConfig.comboBarLocation == RoselConfig.ComboBarLocation.CROSSHAIR) {
					// Position above the crosshair
					x = (screenWidth / 2) - (textureWidth / 2);
					y = (screenHeight / 2) - textureHeight - padding;
				} else {
					// Position next to the hotbar
					int hotbarWidth = 182;
					int hotbarHeight = 22;
					int hotbarX = (screenWidth / 2) - (hotbarWidth / 2);
					int hotbarY = screenHeight - hotbarHeight - 4;

					x = hotbarX - textureWidth - padding;
					y = hotbarY + (hotbarHeight / 2) - (textureHeight / 2);
				}

				if (client.player.isHolding(RoselItems.ROSEL_KOPIS)) {
					RenderSystem.setShader(GameRenderer::getPositionTexShader);
					RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
					RenderSystem.setShaderTexture(0, roselKopisHUDTextureSelecter());

					DrawableHelper.drawTexture(matrixStack, x, y, textureWidth, textureHeight,
						0, 0, 16, 10, 16, 10);
				}
			}
		}
	}

	public Identifier roselKopisHUDTextureSelecter() {
		if (client != null) {
			int attacks = KopisComponent.get(client.player).getLevel();

			if (attacks == 0) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_1.png");
				return TEXTURE;
			} else if (attacks == 2) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_2.png");
				return TEXTURE;
			} else if (attacks == 3) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_3.png");
				return TEXTURE;
			} else if (attacks == 4) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_4.png");
				return TEXTURE;
			} else if (attacks == 5) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_5.png");
				return TEXTURE;
			} else if (attacks == 6) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_6.png");
				return TEXTURE;
			} else if (attacks == 7) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_7.png");
				return TEXTURE;
			} else if (attacks == 8) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_8.png");
				return TEXTURE;
			} else if (attacks == 9) {
				Identifier TEXTURE = new Identifier(Rosel.MODID,
					"textures/hud/rosel_bar_9.png");
				return TEXTURE;
			}
		}
		return null;
	}
}
