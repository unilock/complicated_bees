package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.genetics.effect.DebugEffect;
import com.accbdd.complicated_bees.genetics.effect.IBeeEffect;
import com.accbdd.complicated_bees.genetics.effect.PollenicEffect;
import com.accbdd.complicated_bees.genetics.effect.PotionEffect;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BeeEffectRegistration {
    public static final ResourceKey<Registry<IBeeEffect>> BEE_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(MODID, "bee_effect"));
    public static final Registry<IBeeEffect> BEE_EFFECT_REGISTRY = new RegistryBuilder<>(BEE_REGISTRY_KEY)
            .create();
    public static final DeferredRegister<IBeeEffect> EFFECTS = DeferredRegister.create(BEE_REGISTRY_KEY, MODID);

    public static final Supplier<DebugEffect> DEBUG = EFFECTS.register("debug", DebugEffect::new);
    public static final Supplier<PollenicEffect> POLLENIC = EFFECTS.register("pollenic", PollenicEffect::new);
    public static final Supplier<PotionEffect> BEATIFIC = EFFECTS.register("beatific", () ->
            new PotionEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1, true, true), 80));
    public static final Supplier<PotionEffect> VENOMOUS = EFFECTS.register("venomous", () ->
            new PotionEffect(new MobEffectInstance(MobEffects.POISON, 40, 1, true, true), 80));
}
