package net.nicolas.calcium.mixin;

import net.minecraft.client.render.block.entity.CampfireBlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CampfireBlockEntityRenderer.class)
public class CampfireBlockEntityRendererMixin {

    @ModifyArg(method = "render*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V", ordinal = 0), index = 1)
    private float modifyHeight(float originalY) {
        return 0.465F;
    }
}