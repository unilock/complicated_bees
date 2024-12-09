package com.accbdd.complicated_bees.block;

import com.accbdd.complicated_bees.block.entity.CentrifugeBlockEntity;
import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedSlottedStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public class CentrifugeBlock extends BaseEntityBlock {
    public static final String SCREEN_CENTRIFUGE = "gui.complicated_bees.centrifuge";

    public CentrifugeBlock() {
        super(Properties.of()
                .strength(3.5F)
                .noOcclusion()
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL));
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CentrifugeBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            MenuProvider containerProvider = state.getMenuProvider(level, pos);
            if (containerProvider != null) {
                player.openMenu(containerProvider);
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        if (level.isClientSide) {
            return null;
        } else {
            return (lvl, pos, st, blockEntity) -> {
                if (blockEntity instanceof CentrifugeBlockEntity be) {
                    be.tickServer();
                }
            };
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(BlockStateProperties.POWERED, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(BlockStateProperties.POWERED, BlockStateProperties.FACING);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof CentrifugeBlockEntity centrifuge && !pNewState.is(this)) {
            while (!centrifuge.outputBuffer.empty()) {
                Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), centrifuge.outputBuffer.pop());
            }
            CombinedSlottedStorage<ItemVariant, ItemStackHandler> handler = centrifuge.getItemHandler();
            for (int i = 0; i < handler.getSlots().size(); i++) {
                SingleSlotStorage<ItemVariant> slot = handler.getSlot(i);
                Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), slot.getResource().toStack((int) slot.getAmount()));
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }
}
