package net.nicolas.calcium.mixin;

import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignBlockEntityRenderer.class)
public class SignTextMixin {

    @Inject(method = "getTextOffset", at = @At("HEAD"), cancellable = true)
    private void modifyTextOffset(CallbackInfoReturnable<Vec3d> cir) {
        cir.setReturnValue(new Vec3d(0.0, 0.3255, 0.065));
    }

}