package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.genetics.effect.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import static com.accbdd.complicated_bees.ComplicatedBees.BEE_EFFECT_REGISTRY;
import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BeeEffectRegistration {
    public static final DebugEffect DEBUG = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "debug"), new DebugEffect());
    public static final PollenicEffect POLLENIC = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "pollenic"), new PollenicEffect());
    public static final TributeEffect TRIBUTE = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "tribute"), new TributeEffect());
    public static final ExplorerEffect EXPLORER = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "explorer"), new ExplorerEffect());
    public static final ChampionEffect CHAMPION = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "champion"), new ChampionEffect());
    public static final AggressiveEffect AGGRESSIVE = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "aggressive"), new AggressiveEffect());
    public static final FlamingEffect FLAMING = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "flaming"), new FlamingEffect());
    public static final HostileEffect HOSTILE = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "hostile"), new HostileEffect());
    public static final CursedEffect CURSED = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "cursed"), new CursedEffect());
    public static final EndsEffect ENDS = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "ends"), new EndsEffect());
    public static final ResurrectionEffect RESURRECTION = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "resurrection"), new ResurrectionEffect());
    public static final PotionEffect BEATIFIC = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "beatific"),
            new PotionEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1, true, true), 80));
    public static final PotionEffect VENOMOUS = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "venomous"),
            new PotionEffect(new MobEffectInstance(MobEffects.POISON, 40, 1, true, true), 80));
    public static final PotionEffect SPECTRAL = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "spectral"),
            new PotionEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 1, true, true), 80));
    public static final PotionEffect UNHEALTHY = Registry.register(BEE_EFFECT_REGISTRY, new ResourceLocation(MODID, "unhealthy"),
            new PotionEffect(new MobEffectInstance(MobEffects.HUNGER, 40, 1, true, true), 80));

    public static void register() {}

}
