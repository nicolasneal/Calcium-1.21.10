package net.nicolas.calcium.mixin.hitboxes;

import net.minecraft.block.*;
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

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {

    @Unique private static final VoxelShape FACE_X = Block.createCuboidShape(0.0, 9.0, 2.0, 16.0, 16.0, 14.0);
    @Unique private static final VoxelShape WAIST_X = Block.createCuboidShape(4.0, 5.0, 5.0, 12.0, 9.0, 11.0);
    @Unique private static final VoxelShape LIP_X = Block.createCuboidShape(3.0, 4.0, 4.0, 13.0, 5.0, 12.0);
    @Unique private static final VoxelShape BASE_X = Block.createCuboidShape(1.0, 0.0, 2.0, 15.0, 4.0, 14.0);
    @Unique private static final VoxelShape SHAPE_X = VoxelShapes.union(FACE_X, WAIST_X, LIP_X, BASE_X);

    @Unique private static final VoxelShape FACE_Z = Block.createCuboidShape(2.0, 9.0, 0.0, 14.0, 16.0, 16.0);
    @Unique private static final VoxelShape WAIST_Z = Block.createCuboidShape(5.0, 5.0, 4.0, 11.0, 9.0, 12.0);
    @Unique private static final VoxelShape LIP_Z = Block.createCuboidShape(4.0, 4.0, 3.0, 12.0, 5.0, 13.0);
    @Unique private static final VoxelShape BASE_Z = Block.createCuboidShape(2.0, 0.0, 1.0, 14.0, 4.0, 15.0);
    @Unique private static final VoxelShape SHAPE_Z = VoxelShapes.union(FACE_Z, WAIST_Z, LIP_Z, BASE_Z);

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void injectCustomAnvilShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {

        Direction.Axis axis = state.get(AnvilBlock.FACING).getAxis();

        if (axis == Direction.Axis.X) {
            cir.setReturnValue(SHAPE_X);
        } else {
            cir.setReturnValue(SHAPE_Z);
        }

    }

}