package com.thatoneaiguy.discharged.item;

/*public class DiscItem extends BaseRoselItem {

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
			discEntity.setOwner(user);
			discEntity.setPosition(x, y, z);
			world.spawnEntity(discEntity);
		}
		return super.use(world, user, hand);
	}

}*/
