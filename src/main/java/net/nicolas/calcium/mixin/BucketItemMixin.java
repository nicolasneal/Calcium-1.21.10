package net.nicolas.calcium.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nicolas.calcium.fluid.ModFluids;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {

    @Shadow @Final private Fluid fluid;

    @Inject(method = "placeFluid", at = @At("HEAD"), cancellable = true)
    private void calcium$evaporateEctoplasm(LivingEntity user, World world, BlockPos pos, BlockHitResult hitResult, CallbackInfoReturnable<Boolean> cir) {

        if (this.fluid == ModFluids.ECTOPLASM_STILL) {

            boolean isOverworld = world.getRegistryKey() == World.OVERWORLD;
            boolean isTheEnd = world.getRegistryKey() == World.END;

            if (isOverworld || isTheEnd) {
                PlayerEntity playerEntity = (user instanceof PlayerEntity) ? (PlayerEntity) user : null;
                world.playSound(playerEntity, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 0.8F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                for(int i = 0; i < 8; ++i) {
                    world.addParticleClient(ParticleTypes.SOUL, (double)pos.getX() + Math.random(), (double)pos.getY() + Math.random(), (double)pos.getZ() + Math.random(), 0.0, 0.0, 0.0);
                }
                cir.setReturnValue(true);
            }

        }

    }

}