package com.thatoneaiguy.rosel.util;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireworkParryHandler {

	public static void register() {
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof FireworkRocketEntity firework && hand == Hand.MAIN_HAND) {
				return handleFireworkParry(player, world, firework, hitResult);
			}
			return ActionResult.PASS;
		});
	}

	private static ActionResult handleFireworkParry(net.minecraft.entity.player.PlayerEntity player, World world, FireworkRocketEntity firework, EntityHitResult hitResult) {
		if (!world.isClient) {
			// Check distance from firework to player
			Vec3d playerPos = player.getPos();
			Vec3d fireworkPos = firework.getPos();

			if (playerPos.distanceTo(fireworkPos) < 3.0) { // Parry within 3 blocks
				// Redirect firework back to shooter
				Entity shooter = firework.getOwner();
				if (shooter != null) {
					Vec3d direction = shooter.getPos().subtract(fireworkPos).normalize();
					firework.setVelocity(direction.x, direction.y, direction.z, 1.5F, 0.0F); // Adjust speed as needed
				} else {
					firework.setVelocity(player.getRotationVector().multiply(1.5)); // Parry forward if no owner
				}
				return ActionResult.SUCCESS;
			}
		}
		return ActionResult.PASS;
	}
}
