package com.accbdd.complicated_bees.client;

import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class BakedModelWrapper<T extends BakedModel> implements BakedModel {
	private final BakedModel original;

	public BakedModelWrapper(BakedModel original) {
		this.original = original;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random) {
		return original.getQuads(state, direction, random);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return original.useAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return original.isGui3d();
	}

	@Override
	public boolean usesBlockLight() {
		return original.usesBlockLight();
	}

	@Override
	public boolean isCustomRenderer() {
		return original.isCustomRenderer();
	}

	@Override
	public TextureAtlasSprite getParticleIcon() {
		return original.getParticleIcon();
	}

	@Override
	public ItemTransforms getTransforms() {
		return original.getTransforms();
	}

	@Override
	public ItemOverrides getOverrides() {
		return original.getOverrides();
	}

	@Override
	public boolean isVanillaAdapter() {
		return original.isVanillaAdapter();
	}

	@Override
	public void emitBlockQuads(BlockAndTintGetter blockView, BlockState state, BlockPos pos, Supplier<RandomSource> randomSupplier, RenderContext context) {
		original.emitBlockQuads(blockView, state, pos, randomSupplier, context);
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<RandomSource> randomSupplier, RenderContext context) {
		original.emitItemQuads(stack, randomSupplier, context);
	}
}
