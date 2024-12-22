package com.thatoneaiguy.discharged.render;

import com.thatoneaiguy.discharged.Discharged;
import com.thatoneaiguy.discharged.DischargedConfig;
import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GauntletItemModel extends AnimatedGeoModel<RoselGauntletItem> {
	private static final Identifier MODEL = new Identifier(Discharged.MODID, "geo/item/rosel_gauntlet_red-item.geo.json");
	private static final Identifier DEFAULT_TEXTURE = new Identifier(Discharged.MODID, "textures/items/rosel_gauntlet_blue.png");
	private static final Identifier ANIMATION = new Identifier(Discharged.MODID, "animations/item/rat.animation.json");

	@Override
	public Identifier getModelResource(RoselGauntletItem object) {
		return MODEL;
	}

	@Override
	public Identifier getTextureResource(RoselGauntletItem object) {
		return DEFAULT_TEXTURE;
	}

	@Override
	public Identifier getAnimationResource(RoselGauntletItem animatable) {
		return ANIMATION;
	}
}
