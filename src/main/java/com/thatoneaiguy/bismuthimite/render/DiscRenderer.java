package com.thatoneaiguy.bismuthimite.render;

import com.thatoneaiguy.bismuthimite.Bismuthimite;
import com.thatoneaiguy.bismuthimite.entity.DiscEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class DiscRenderer extends ProjectileEntityRenderer<DiscEntity> {
	public static final Identifier TEXTURE = new Identifier(Bismuthimite.MODID, "textures/entity/vampire_knife.png");

	public DiscRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(DiscEntity entity) {
		return TEXTURE;
	}
}
