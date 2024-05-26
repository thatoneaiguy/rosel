package com.thatoneaiguy.rosel.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class RaycastUtil {

	public static HitResult performRaycast(PlayerEntity user, double distance) {

		Vec3d eyePos = user.getCameraPosVec(1F);
		Vec3d eyeRot = user.getRotationVec(1F).multiply(distance);
		Vec3d targetPos = eyePos.add(eyeRot);

		// create raycast context
		RaycastContext context = new RaycastContext(
			eyePos,
			targetPos,
			RaycastContext.ShapeType.OUTLINE,
			RaycastContext.FluidHandling.NONE,
			user
		);

		// performing the raycast
        return user.world.raycast(context);

    }

}
