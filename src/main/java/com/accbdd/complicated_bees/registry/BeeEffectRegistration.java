package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.genetics.effect.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.BEE_EFFECT_REGISTRY;
import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BeeEffectRegistration {
    public static final Supplier<DebugEffect> DEBUG = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "debug"), new DebugEffect());
    public static final Supplier<PollenicEffect> POLLENIC = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "pollenic"), new PollenicEffect());
    public static final Supplier<TributeEffect> TRIBUTE = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "tribute"), new TributeEffect());
    public static final Supplier<ExplorerEffect> EXPLORER = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "explorer"), new ExplorerEffect());
    public static final Supplier<ChampionEffect> CHAMPION = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "champion"), new ChampionEffect());
    public static final Supplier<AggressiveEffect> AGGRESSIVE = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "aggressive"), new AggressiveEffect());
    public static final Supplier<FlamingEffect> FLAMING = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "flaming"), new FlamingEffect());
    public static final Supplier<HostileEffect> HOSTILE = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "hostile"), new HostileEffect());
    public static final Supplier<CursedEffect> CURSED = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "cursed"), new CursedEffect());
    public static final Supplier<EndsEffect> ENDS = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "ends"), new EndsEffect());
    public static final Supplier<ResurrectionEffect> RESURRECTION = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "resurrection"), new ResurrectionEffect());
    public static final Supplier<PotionEffect> BEATIFIC = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "beatific"),
            new PotionEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1, true, true), 80));
    public static final Supplier<PotionEffect> VENOMOUS = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "venomous"),
            new PotionEffect(new MobEffectInstance(MobEffects.POISON, 40, 1, true, true), 80));
    public static final Supplier<PotionEffect> SPECTRAL = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "spectral"),
            new PotionEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 1, true, true), 80));
    public static final Supplier<PotionEffect> UNHEALTHY = () -> Registry.register(BEE_EFFECT_REGISTRY.get(), new ResourceLocation(MODID, "unhealthy"),
            new PotionEffect(new MobEffectInstance(MobEffects.HUNGER, 40, 1, true, true), 80));

    public static void register() {}

}
