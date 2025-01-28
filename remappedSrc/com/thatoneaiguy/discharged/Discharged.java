package com.thatoneaiguy.discharged;

import com.thatoneaiguy.discharged.cca.DiscComponent;
import com.thatoneaiguy.discharged.cca.KopisComponent;
import com.thatoneaiguy.discharged.cca.RoselCoatingComponent;
import com.thatoneaiguy.discharged.entity.DiscEntity;
import com.thatoneaiguy.discharged.entity.RoselCrystalPlayerShapeEntity;
import com.thatoneaiguy.discharged.init.*;
import com.thatoneaiguy.discharged.item.RoselGauntletItem;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;
import org.quiltmc.qsl.lifecycle.api.event.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class Discharged implements ModInitializer, EntityComponentInitializer {
	public static final String MODID = "discharged";
	public static int ticks = -1;
	public static final Logger LOGGER = LoggerFactory.getLogger("Discharged");

	private static <T extends
		Entity> EntityType<T> registerEntityType(String name, SpawnGroup group, EntityType.EntityFactory<T> entityFactory,
												 float width, float height) {
		Identifier entityId = new Identifier(MODID, name);
		QuiltEntityTypeBuilder<T> entityTypeBuilder = QuiltEntityTypeBuilder.create(group, entityFactory)
			.setDimensions(EntityDimensions.fixed(width, height))
			.maxBlockTrackingRange(4)
			.trackingTickInterval(10);
		return Registry.register(Registry.ENTITY_TYPE, entityId, entityTypeBuilder.build());
	}

	public static final Identifier MODE_CHANGE_PACKET = new Identifier(MODID, "mode_change");
	public static final Identifier SYNC_ITEM_MODE_PACKET = new Identifier(MODID, "sync_item_mode");

	//public static final EntityType<DiscItem.> GrenadeProjectileEntityType = registerEntityType("grenade", SpawnGroup.MISC, ExplosiveItem.GrenadeEntity::new, 1F, 2F);

	// CCA
	public static final ComponentKey<RoselCoatingComponent> ROSEL_COATING_COMPONENT =
		ComponentRegistry.getOrCreate(id("rosel_coating"), RoselCoatingComponent.class);
	public static final ComponentKey<KopisComponent> ROSEL_KOPIS_COMPENENT =
		ComponentRegistry.getOrCreate(id("rosel_kopis"), KopisComponent.class);
	public static final ComponentKey<DiscComponent> ROSEL_DISC_COMPONENT =
		ComponentRegistry.getOrCreate(id("rosel_disc"), DiscComponent.class);
	//public static final ComponentKey<RoselParryingComponent> ROSEL_PARRY_COMPONENT =
	//	ComponentRegistry.getOrCreate(id("rosel_parry"), RoselParryingComponent.class);

	@Override
	public void onInitialize(ModContainer mod) {
		GeckoLib.initialize();

		DischargedItems.register();
		DischargedItemGroup.register();
		DischargedBlocks.registerAll();
		// Block Entities
		//Loot table modifiers
		DischargedSounds.registerSounds();
		//Block Entities
		//RoselEntities.register();
		DischargedLodestoneParticles.init();
		//effects-|
		//potions-|
		// Fluid
		//entity attributes registry

		DischargedEnchantments.register();
		DischargedDamageSources.register();

		MidnightConfig.init("discharged", DischargedConfig.class);

		UseEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) -> {
			if ( entity instanceof RoselCrystalPlayerShapeEntity ) {
				if ( !player.isHolding(DischargedItems.ROSEL_SHARD)) {
					return ActionResult.FAIL;
				}
				//RoselCrystalPlayerShapeEntity.alive = true;

				return ActionResult.CONSUME;
			}
			return ActionResult.FAIL;
		});

		ServerLivingEntityEvents.ALLOW_DAMAGE.register(this::onLivingEntityDamage);

		ServerTickEvents.END.register(server -> {
			if (ticks > 0) {
				ticks--;
			} else if (ticks == 0) {
				ticks = -1;
			}
		});
	}

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(PlayerEntity.class, ROSEL_COATING_COMPONENT, RoselCoatingComponent::new);
		registry.registerFor(PlayerEntity.class, ROSEL_KOPIS_COMPENENT, KopisComponent::new);
		registry.registerFor(DiscEntity.class, ROSEL_DISC_COMPONENT, DiscComponent::new);
	}

	private boolean onLivingEntityDamage(LivingEntity entity, DamageSource source, float amount) {
		return RoselGauntletItem.handleDamageReflection(entity, source, amount);
	}

	public static Identifier id(String name) {
		return new Identifier("discharged", name);
	}
}
