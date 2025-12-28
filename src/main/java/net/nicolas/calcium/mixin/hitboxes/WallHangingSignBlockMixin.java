package net.nicolas.calcium.mixin.hitboxes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallHangingSignBlock.class)
public class WallHangingSignBlockMixin {

    @Unique private static final VoxelShape BOARD_X = Block.createCuboidShape(7.0, 0.0, 1.0, 9.0, 10.0, 15.0);
    @Unique private static final VoxelShape BAR_X = Block.createCuboidShape(7.0, 14.0, 0.0, 9.0, 16.0, 16.0);
    @Unique private static final VoxelShape SHAPE_X = VoxelShapes.union(BOARD_X, BAR_X);

    @Unique private static final VoxelShape BOARD_Z = Block.createCuboidShape(1.0, 0.0, 7.0, 15.0, 10.0, 9.0);
    @Unique private static final VoxelShape BAR_Z = Block.createCuboidShape(0.0, 14.0, 7.0, 16.0, 16.0, 9.0);
    @Unique private static final VoxelShape SHAPE_Z = VoxelShapes.union(BOARD_Z, BAR_Z);

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void injectCustomShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {

        Direction.Axis axis = state.get(WallHangingSignBlock.FACING).getAxis();
        if (axis == Direction.Axis.X) {
            cir.setReturnValue(SHAPE_X);
        } else {
            cir.setReturnValue(SHAPE_Z);
        }

    }

}