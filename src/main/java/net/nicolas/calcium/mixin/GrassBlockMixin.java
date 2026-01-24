package net.nicolas.calcium.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.GrassBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GrassBlock.class)
public class GrassBlockMixin {

    @Redirect(
        method = "grow",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/Fertilizable;grow(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
        )
    )
    private void calcium$preventTallGrass(Fertilizable instance, ServerWorld world, Random random, BlockPos pos, BlockState state) {}

}