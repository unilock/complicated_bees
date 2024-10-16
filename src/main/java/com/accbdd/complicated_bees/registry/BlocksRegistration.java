package com.accbdd.complicated_bees.registry;

import com.accbdd.complicated_bees.block.ApiaryBlock;
import com.accbdd.complicated_bees.block.BeeNestBlock;
import com.accbdd.complicated_bees.block.CentrifugeBlock;
import com.accbdd.complicated_bees.block.GeneratorBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

import static com.accbdd.complicated_bees.ComplicatedBees.MODID;

public class BlocksRegistration {
    public static final BlockBehaviour.Properties WAX_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F);
    public static final BlockBehaviour.Properties HONEYPLANK_PROPERTIES = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_YELLOW)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD)
            .strength(2, 3);

    public static final Supplier<BeeNestBlock> BEE_NEST = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "bee_nest"), new BeeNestBlock());
    public static final Supplier<ApiaryBlock> APIARY = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "apiary"), new ApiaryBlock());
    public static final Supplier<CentrifugeBlock> CENTRIFUGE = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "centrifuge"), new CentrifugeBlock());
    public static final Supplier<GeneratorBlock> GENERATOR = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "generator"), new GeneratorBlock());

    public static final Supplier<Block> WAX_BLOCK = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_block"), new Block(WAX_PROPERTIES));
    public static final Supplier<StairBlock> WAX_BLOCK_STAIRS = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_block_stairs"), stair(WAX_BLOCK.get()));
    public static final Supplier<SlabBlock> WAX_BLOCK_SLAB = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_block_slab"), slab(WAX_BLOCK.get()));
    public static final Supplier<WallBlock> WAX_BLOCK_WALL = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_block_wall"), wall(WAX_BLOCK.get()));
    public static final Supplier<Block> SMOOTH_WAX = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "smooth_wax"), new Block(WAX_PROPERTIES));
    public static final Supplier<StairBlock> SMOOTH_WAX_STAIRS = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "smooth_wax_stairs"), stair(SMOOTH_WAX.get()));
    public static final Supplier<SlabBlock> SMOOTH_WAX_SLAB = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "smooth_wax_slab"), slab(SMOOTH_WAX.get()));
    public static final Supplier<WallBlock> SMOOTH_WAX_WALL = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "smooth_wax_wall"), wall(SMOOTH_WAX.get()));
    public static final Supplier<Block> WAX_BRICKS = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_bricks"), new Block(WAX_PROPERTIES));
    public static final Supplier<StairBlock> WAX_BRICK_STAIRS = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_brick_stairs"), stair(WAX_BRICKS.get()));
    public static final Supplier<SlabBlock> WAX_BRICK_SLAB = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_brick_slab"), slab(WAX_BRICKS.get()));
    public static final Supplier<WallBlock> WAX_BRICK_WALL = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "wax_brick_wall"), wall(WAX_BRICKS.get()));
    public static final Supplier<Block> CHISELED_WAX = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "chiseled_wax"), new Block(WAX_PROPERTIES));

    public static final Supplier<Block> HONEYED_PLANKS = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_planks"), new Block(HONEYPLANK_PROPERTIES));
    public static final Supplier<StairBlock> HONEYED_STAIRS = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_stairs"), stair(HONEYED_PLANKS.get()));
    public static final Supplier<SlabBlock> HONEYED_SLAB = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_slab"), slab(HONEYED_PLANKS.get()));
    public static final Supplier<FenceBlock> HONEYED_FENCE = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_fence"), fence(HONEYED_PLANKS.get()));
    public static final Supplier<FenceGateBlock> HONEYED_FENCE_GATE = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_fence_gate"), gate(HONEYED_PLANKS.get()));
    public static final Supplier<ButtonBlock> HONEYED_BUTTON = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_button"), button(BlockSetType.OAK, 30, HONEYED_PLANKS.get()));
    public static final Supplier<PressurePlateBlock> HONEYED_PRESSURE_PLATE = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_pressure_plate"), plate(BlockSetType.OAK, HONEYED_PLANKS.get()));
    public static final Supplier<DoorBlock> HONEYED_DOOR = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_door"), door(BlockSetType.OAK, HONEYED_PLANKS.get()));
    public static final Supplier<TrapDoorBlock> HONEYED_TRAPDOOR = () -> Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, "honeyed_trapdoor"), trapdoor(BlockSetType.OAK, HONEYED_PLANKS.get()));

    public static void register() {}

    private static StairBlock stair(Block base) {
        return new StairBlock(base.defaultBlockState(), BlockBehaviour.Properties.copy(base));
    }

    private static SlabBlock slab(Block base) {
        return new SlabBlock(BlockBehaviour.Properties.copy(base));
    }

    private static WallBlock wall(Block base) {
        return new WallBlock(BlockBehaviour.Properties.copy(base));
    }

    private static FenceBlock fence(Block base) {
        return new FenceBlock(BlockBehaviour.Properties.copy(base));
    }

    private static FenceGateBlock gate(Block base) {
        return new FenceGateBlock(BlockBehaviour.Properties.copy(base), WoodType.OAK);
    }

    private static ButtonBlock button(BlockSetType type, int ticksToPress, Block base) {
        return new ButtonBlock(BlockBehaviour.Properties.copy(base).noCollission(), type, ticksToPress, true);
    }

    private static PressurePlateBlock plate(BlockSetType type, Block base) {
        return new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(base), type);
    }

    private static DoorBlock door(BlockSetType type, Block base) {
        return new DoorBlock(BlockBehaviour.Properties.copy(base).noOcclusion(), type);
    }

    private static TrapDoorBlock trapdoor(BlockSetType type, Block base) {
        return new TrapDoorBlock(BlockBehaviour.Properties.copy(base).noOcclusion(), type);
    }
}
