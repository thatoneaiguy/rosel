package com.thatoneaiguy.rosel.mixin;

import com.sammy.lodestone.systems.rendering.particle.Easing;
import com.sammy.lodestone.systems.rendering.particle.ParticleBuilders;
import com.sammy.lodestone.systems.rendering.particle.SimpleParticleEffect;
import com.thatoneaiguy.rosel.Rosel;
import com.thatoneaiguy.rosel.RoselClient;
import com.thatoneaiguy.rosel.RoselConfig;
import com.thatoneaiguy.rosel.cca.RoselCoatingComponent;
import com.thatoneaiguy.rosel.init.RoselEnchantments;
import com.thatoneaiguy.rosel.init.RoselParticles;
import com.thatoneaiguy.rosel.item.RoselKopis;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.amymialee.mialeemisc.entities.IPlayerTargeting;

import java.util.List;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(method = "render", at = @At("TAIL"))
	private void rosel$renderCrosshair(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
		PlayerEntity player = this.client.player;
		if (!(player instanceof IPlayerTargeting targeting)) {
			return;
		}
		Entity target = targeting.mialeeMisc$getLastTarget();
		if (target == null || !target.isAlive() || target.isRemoved()) {
			return;
		}
		ItemStack mainHandStack = player.getMainHandStack();
		if (EnchantmentHelper.getRiptide(mainHandStack) > 0) {
			return;
		}
		if (!(mainHandStack.getItem() instanceof RoselKopis)) {
			return;
		}
		ClientPlayerInteractionManager interactionManager = this.client.interactionManager;
		if (interactionManager == null || interactionManager.getCurrentGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (this.client.world == null) {
			return;
		}


		boolean hasLunge = EnchantmentHelper.getLevel(RoselEnchantments.LUNGE, mainHandStack) > 0;
		if ( hasLunge ) {
			for (int i = 0; i < 4; i++) {
				Vec3d vec3d = new Vec3d(
					target.getX() + (MathHelper.sin((target.age + tickDelta) * 0.75f + i * 45) * target.getWidth() * 1.2),
					target.getBodyY(0.5f),
					target.getZ() + (MathHelper.cos((target.age + tickDelta) * 0.75f + i * 45) * target.getWidth() * 1.2));
				if (RoselConfig.particleTypes != RoselConfig.ParticleTypes.VANILLA) {
					ParticleBuilders.create(RoselParticles.ROSEL_CROSS)
						.overrideAnimator(SimpleParticleEffect.Animator.WITH_AGE)
						.setScale((.2f + player.world.random.nextFloat() / 3f))
						.setSpinOffset(player.world.random.nextFloat() * 360f)
						.setSpin((float) (player.world.random.nextGaussian() / 100f))
						.setAlpha(0, 0.3f, 0)
						.setAlphaEasing(Easing.QUINTIC_OUT)
						.enableNoClip()
						.setLifetime(5)
						.spawn(player.world, vec3d.getX(), vec3d.getY(), vec3d.getZ());
				} else if (RoselConfig.particleTypes != RoselConfig.ParticleTypes.LODESTONE) {
					this.client.world.addParticle(RoselClient.ROSEL_CROSS_V, vec3d.getX(), vec3d.getY(), vec3d.getZ(), 0.0, 0.0, 0.0);
				}
			}
		}
	}
}

