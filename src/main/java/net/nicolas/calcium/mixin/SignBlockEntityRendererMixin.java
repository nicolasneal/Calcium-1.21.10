package net.nicolas.calcium.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignBlockEntityRenderer.class)
public class SignBlockEntityRendererMixin {

    @Unique
    private BlockState calcium$capturedState;

    @Inject(method = "applyTransforms", at = @At("HEAD"))
    private void calcium$captureBlockState(MatrixStack matrices, float rot, BlockState state, CallbackInfo ci) {
        this.calcium$capturedState = state;
    }

    @Inject(method = "getTextOffset", at = @At("HEAD"), cancellable = true)
    private void modifyTextOffset(CallbackInfoReturnable<Vec3d> cir) {
        if (this.calcium$capturedState != null) {
            boolean isStandingSign = this.calcium$capturedState.getBlock() instanceof SignBlock;
            if (!isStandingSign) {
                cir.setReturnValue(new Vec3d(0.0, 0.3255, 0.065));
            }
            else {
                cir.setReturnValue(new Vec3d(0.0, 0.2985, 0.065));
            }
        }
    }

}