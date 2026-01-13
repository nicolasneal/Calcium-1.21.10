package net.nicolas.calcium.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @Invoker("isWaterNearby") private static boolean invokeIsWaterNearby(WorldView world, BlockPos pos) {
        throw new AssertionError();
    }

    @Invoker("hasCrop") private static boolean invokeHasCrop(BlockView world, BlockPos pos) {
        throw new AssertionError();
    }

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {

        int i = state.get(FarmlandBlock.MOISTURE);

        if (!invokeIsWaterNearby(world, pos) && !world.hasRain(pos.up())) {
            if (i > 0) {
                world.setBlockState(pos, state.with(FarmlandBlock.MOISTURE, i - 1), 2);
            } else if (!invokeHasCrop(world, pos)) {
                FarmlandBlock.setToDirt(null, state, world, pos);
            }
        } else if (i < 7) {
            world.setBlockState(pos, state.with(FarmlandBlock.MOISTURE, i + 1), 2);
        }

        ci.cancel();

    }

}