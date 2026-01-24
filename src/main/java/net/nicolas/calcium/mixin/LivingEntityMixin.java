package net.nicolas.calcium.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.Vec3d;
import net.nicolas.calcium.fluid.ModFluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected boolean jumping;

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void calcium$ectoplasmPhysics(Vec3d movementInput, CallbackInfo ci) {

        LivingEntity entity = (LivingEntity) (Object) this;
        FluidState fluidState = entity.getEntityWorld().getFluidState(entity.getBlockPos());

        if (fluidState.isEmpty()) {
            fluidState = entity.getEntityWorld().getFluidState(entity.getBlockPos().up());
        }

        if (fluidState.getFluid() == ModFluids.ECTOPLASM_STILL || fluidState.getFluid() == ModFluids.ECTOPLASM_FLOWING) {

            entity.fallDistance = 0.0F;

            float speed = entity.isSprinting() ? 0.12F : 0.02F;
            float drag = 0.6F;
            double gravity = 0.015;

            double verticalPush = 0.0;
            if (this.jumping) {
                verticalPush = 0.03;
            } else if (movementInput.y < 0) {
                verticalPush = -0.03;
            }

            entity.updateVelocity(speed, movementInput);

            if (verticalPush != 0.0) {
                entity.setVelocity(entity.getVelocity().add(0.0, verticalPush, 0.0));
            }

            entity.move(net.minecraft.entity.MovementType.SELF, entity.getVelocity());
            entity.setVelocity(entity.getVelocity().multiply(drag));

            if (!entity.hasNoGravity()) {
                entity.setVelocity(entity.getVelocity().add(0.0, -gravity, 0.0));
            }

            entity.setSwimming(entity.isSprinting() && !entity.hasVehicle());

            ci.cancel();

        }
    }

    @Inject(method = "jump", at = @At("HEAD"), cancellable = true)
    private void calcium$preventJumpInEctoplasm(CallbackInfo ci) {

        LivingEntity entity = (LivingEntity) (Object) this;
        FluidState fluidState = entity.getEntityWorld().getFluidState(entity.getBlockPos());

        if (fluidState.isEmpty()) {
            fluidState = entity.getEntityWorld().getFluidState(entity.getBlockPos().up());
        }

        if (fluidState.getFluid() == ModFluids.ECTOPLASM_STILL || fluidState.getFluid() == ModFluids.ECTOPLASM_FLOWING) {
            ci.cancel();
        }

    }

}