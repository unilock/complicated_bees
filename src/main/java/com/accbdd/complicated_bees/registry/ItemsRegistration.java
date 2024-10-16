package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.config.Config;
import com.accbdd.complicated_bees.genetics.BeeHousingModifier;
import com.accbdd.complicated_bees.genetics.gene.enums.EnumTolerance;
import com.accbdd.complicated_bees.item.ArmorMaterials;
import com.accbdd.complicated_bees.item.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class ItemsRegistration {
    public static final Supplier<DroneItem> DRONE = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "drone"), new DroneItem(new Item.Properties()));
    public static final Supplier<PrincessItem> PRINCESS = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "princess"), new PrincessItem(new Item.Properties()));
    public static final Supplier<QueenItem> QUEEN = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "queen"), new QueenItem(new Item.Properties()));
    public static final Supplier<CombItem> COMB = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "comb"), new CombItem(new Item.Properties()));
    public static final Supplier<ScoopItem> SCOOP = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "scoop"), new ScoopItem(new Item.Properties()));
    public static final Supplier<MeterItem> METER = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "meter"), new MeterItem(new Item.Properties()));
    public static final Supplier<AnalyzerItem> ANALYZER = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "analyzer"), new AnalyzerItem(new Item.Properties()));
    public static final Supplier<ExpDropItem> EXP_DROP = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "exp_drop"), new ExpDropItem(new Item.Properties()));
    public static final Supplier<BeeswaxItem> BEESWAX = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "beeswax"), new BeeswaxItem(new Item.Properties()));

    public static final Supplier<Item> HONEY_DROPLET = registerSimpleItem("honey_droplet");
    public static final Supplier<Item> ROYAL_JELLY = registerSimpleItem("royal_jelly");
    public static final Supplier<Item> POLLEN = registerSimpleItem("pollen");
    public static final Supplier<Item> PROPOLIS = registerSimpleItem("propolis");
    public static final Supplier<Item> SILK_WISP = registerSimpleItem("silk_wisp");
    public static final Supplier<Item> WOVEN_MESH = registerSimpleItem("woven_mesh");
    public static final Supplier<Item> PEARL_SHARD = registerSimpleItem("pearl_shard");
    public static final Supplier<Item> WAXED_STICK = registerSimpleItem("waxed_stick");
    public static final Supplier<Item> HONEYED_STICK = registerSimpleItem("honeyed_stick");
    public static final Supplier<Item> BEE_STAFF = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "bee_staff"), new BeeStaffItem(new Item.Properties(), Config.CONFIG.beeStaff));
    public static final Supplier<Item> HONEY_BREAD = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "honey_bread"), new DisableableItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.4f).build()), Config.CONFIG.honeyBread));
    public static final Supplier<Item> HONEY_PORKCHOP = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "honey_porkchop"), new DisableableItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(12).saturationMod(0.5f).build()), Config.CONFIG.honeyPorkchop));
    public static final Supplier<Item> AMBROSIA = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "ambrosia"), new DisableableItem(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.JUMP, 1200, 2), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1), 1.0F)
            .alwaysEat()
            .build()).rarity(Rarity.RARE), Config.CONFIG.ambrosia) {
        @Override
        public boolean isFoil(ItemStack pStack) {
            return true;
        }
    });

    public static final Supplier<FrameItem> FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().productivity(1.1f).build(), Config.CONFIG.frame));
    public static final Supplier<FrameItem> WAXED_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "waxed_frame"),
            new FrameItem(new Item.Properties().durability(240), new BeeHousingModifier.Builder().productivity(1.25f).build(), Config.CONFIG.waxedFrame));
    public static final Supplier<FrameItem> HONEYED_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "honeyed_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().productivity(1.35f).lifespan(0.9f).build(), Config.CONFIG.honeyFrame));
    public static final Supplier<FrameItem> TWISTING_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "twisting_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().productivity(0.6f).lifespan(0.75f).mutation(1.5f).build(), Config.CONFIG.twistingFrame));
    public static final Supplier<FrameItem> SOOTHING_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "soothing_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().productivity(0.75f).lifespan(1.5f).mutation(0.8f).build(), Config.CONFIG.soothingFrame));
    public static final Supplier<FrameItem> COLD_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "cold_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().temperature(EnumTolerance.DOWN_1).lifespan(0.8f).build(), Config.CONFIG.coldFrame));
    public static final Supplier<FrameItem> HOT_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "hot_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().temperature(EnumTolerance.UP_1).lifespan(0.8f).build(), Config.CONFIG.hotFrame));
    public static final Supplier<FrameItem> DRY_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "dry_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().humidity(EnumTolerance.DOWN_1).lifespan(0.8f).build(), Config.CONFIG.dryFrame));
    public static final Supplier<FrameItem> WET_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "wet_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().humidity(EnumTolerance.UP_1).lifespan(0.8f).build(), Config.CONFIG.wetFrame));
    public static final Supplier<FrameItem> DEADLY_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "deadly_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().lifespan(0.1f).build(), Config.CONFIG.deadlyFrame));
    public static final Supplier<FrameItem> RESTRICTIVE_FRAME = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "restrictive_frame"),
            new FrameItem(new Item.Properties().durability(80), new BeeHousingModifier.Builder().territory(0.5f).lifespan(0.75f).productivity(0.75f).build(), Config.CONFIG.restrictiveFrame));

    public static final Supplier<ArmorItem> APIARIST_HELMET = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "apiarist_helmet"),
            new ArmorItem(ArmorMaterials.APIARIST, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Supplier<ArmorItem> APIARIST_CHESTPLATE = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "apiarist_chestplate"),
            new ArmorItem(ArmorMaterials.APIARIST, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Supplier<ArmorItem> APIARIST_LEGGINGS = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "apiarist_leggings"),
            new ArmorItem(ArmorMaterials.APIARIST, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Supplier<ArmorItem> APIARIST_BOOTS = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "apiarist_boots"),
            new ArmorItem(ArmorMaterials.APIARIST, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Supplier<Item> BEE_NEST = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "bee_nest"), new BeeNestBlockItem(new Item.Properties()));
    public static final Supplier<Item> APIARY = registerSimpleBlockItem("apiary", BlocksRegistration.APIARY);
    public static final Supplier<Item> CENTRIFUGE = registerSimpleBlockItem("centrifuge", BlocksRegistration.CENTRIFUGE);
    public static final Supplier<Item> GENERATOR = registerSimpleBlockItem("generator", BlocksRegistration.GENERATOR);
    public static final Supplier<Item> WAX_BLOCK = registerSimpleBlockItem("wax_block", BlocksRegistration.WAX_BLOCK);
    public static final Supplier<Item> WAX_BLOCK_STAIRS = registerSimpleBlockItem("wax_block_stairs", BlocksRegistration.WAX_BLOCK_STAIRS);
    public static final Supplier<Item> WAX_BLOCK_SLAB = registerSimpleBlockItem("wax_block_slab", BlocksRegistration.WAX_BLOCK_SLAB);
    public static final Supplier<Item> WAX_BLOCK_WALL = registerSimpleBlockItem("wax_block_wall", BlocksRegistration.WAX_BLOCK_WALL);
    public static final Supplier<Item> SMOOTH_WAX = registerSimpleBlockItem("smooth_wax", BlocksRegistration.SMOOTH_WAX);
    public static final Supplier<Item> SMOOTH_WAX_STAIRS = registerSimpleBlockItem("smooth_wax_stairs", BlocksRegistration.SMOOTH_WAX_STAIRS);
    public static final Supplier<Item> SMOOTH_WAX_SLAB = registerSimpleBlockItem("smooth_wax_slab", BlocksRegistration.SMOOTH_WAX_SLAB);
    public static final Supplier<Item> SMOOTH_WAX_WALL = registerSimpleBlockItem("smooth_wax_wall", BlocksRegistration.SMOOTH_WAX_WALL);
    public static final Supplier<Item> WAX_BRICKS = registerSimpleBlockItem("wax_bricks", BlocksRegistration.WAX_BRICKS);
    public static final Supplier<Item> WAX_BRICK_STAIRS = registerSimpleBlockItem("wax_brick_stairs", BlocksRegistration.WAX_BRICK_STAIRS);
    public static final Supplier<Item> WAX_BRICK_SLAB = registerSimpleBlockItem("wax_brick_slab", BlocksRegistration.WAX_BRICK_SLAB);
    public static final Supplier<Item> WAX_BRICK_WALL = registerSimpleBlockItem("wax_brick_wall", BlocksRegistration.WAX_BRICK_WALL);
    public static final Supplier<Item> CHISELED_WAX = registerSimpleBlockItem("chiseled_wax", BlocksRegistration.CHISELED_WAX);
    public static final Supplier<Item> HONEYED_PLANKS = registerSimpleBlockItem("honeyed_planks", BlocksRegistration.HONEYED_PLANKS);
    public static final Supplier<Item> HONEYED_STAIRS = registerSimpleBlockItem("honeyed_stairs", BlocksRegistration.HONEYED_STAIRS);
    public static final Supplier<Item> HONEYED_SLAB = registerSimpleBlockItem("honeyed_slab", BlocksRegistration.HONEYED_SLAB);
    public static final Supplier<Item> HONEYED_FENCE = registerSimpleBlockItem("honeyed_fence", BlocksRegistration.HONEYED_FENCE);
    public static final Supplier<Item> HONEYED_FENCE_GATE = registerSimpleBlockItem("honeyed_fence_gate", BlocksRegistration.HONEYED_FENCE_GATE);
    public static final Supplier<Item> HONEYED_BUTTON = registerSimpleBlockItem("honeyed_button", BlocksRegistration.HONEYED_BUTTON);
    public static final Supplier<Item> HONEYED_PRESSURE_PLATE = registerSimpleBlockItem("honeyed_pressure_plate", BlocksRegistration.HONEYED_PRESSURE_PLATE);
    public static final Supplier<BlockItem> HONEYED_DOOR = () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "honeyed_door"), new DoubleHighBlockItem(BlocksRegistration.HONEYED_DOOR.get(), new Item.Properties()));
    public static final Supplier<Item> HONEYED_TRAPDOOR = registerSimpleBlockItem("honeyed_trapdoor", BlocksRegistration.HONEYED_TRAPDOOR);

    public static void register() {}

    private static Supplier<Item> registerSimpleItem(String name) {
        return () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, name), new Item(new Item.Properties()));
    }

    private static <T extends Block>Supplier<Item> registerSimpleBlockItem(String name, Supplier<T> block) {
        return () -> Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, name), new BlockItem(block.get(), new Item.Properties()));
    }
}
