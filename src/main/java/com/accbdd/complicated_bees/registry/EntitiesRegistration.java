package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.entity.BeeStaffProjectile;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class EntitiesRegistration {
    public static final Supplier<EntityType<BeeStaffProjectile>> BEE_STAFF_MOUNT = () -> Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(MODID, "bee_staff_mount"),
            EntityType.Builder.<BeeStaffProjectile>of(BeeStaffProjectile::new, MobCategory.MISC).sized(0.5f, 0.5f).build("bee_staff_mount"));

    public static void register() {}
}
