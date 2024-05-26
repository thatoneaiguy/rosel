package com.thatoneaiguy.rosel.item;

import com.thatoneaiguy.rosel.entity.DiscEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DiscItem extends Item {

	public DiscItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		ItemStack itemStack = user.getStackInHand(hand);

		Vec3d lookVector = user.getRotationVec(1.0F);

		double x = user.getX();
		double y = user.getEyeY();
		double z = user.getZ();

		if (!world.isClient) {
			DiscEntity discEntity = new DiscEntity(world, user, lookVector.x, lookVector.y, lookVector.z);
			discEntity.setPosition(x, y, z);
			world.spawnEntity(discEntity);
		}
		return super.use(world, user, hand);
	}

}
