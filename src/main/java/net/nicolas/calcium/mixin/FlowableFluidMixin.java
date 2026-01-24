package net.nicolas.calcium.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.nicolas.calcium.block.ModBlocks;
import net.nicolas.calcium.fluid.ModFluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowableFluid.class)
public abstract class FlowableFluidMixin {

    @Inject(method = "flow", at = @At("HEAD"), cancellable = true)
    private void calcium$interactWithEctoplasm(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState newFluidState, CallbackInfo ci) {

        if (!((Object) this instanceof LavaFluid)) {
            return;
        }

        FluidState currentFluidState = state.getFluidState();

        if (currentFluidState.getFluid() == ModFluids.ECTOPLASM_STILL || currentFluidState.getFluid() == ModFluids.ECTOPLASM_FLOWING) {

            if (currentFluidState.isStill()) {
                world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState(), 3);
            } else {
                world.setBlockState(pos, ModBlocks.SOULSLATE.getDefaultState(), 3);
            }
            this.playExtinguishEvent(world, pos);

            ci.cancel();

        }
    }

    @Unique private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(1501, pos, 0);
    }

}