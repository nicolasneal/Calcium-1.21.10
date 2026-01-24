package net.nicolas.calcium.mixin.hitboxes;

import net.minecraft.block.BlockState;
import net.minecraft.block.RootsBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RootsBlock.class)
public class RootsBlockMixin {

    @Shadow @Final private static VoxelShape SHAPE;

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void calcium$addOffsetToShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(SHAPE.offset(state.getModelOffset(pos)));
    }

}