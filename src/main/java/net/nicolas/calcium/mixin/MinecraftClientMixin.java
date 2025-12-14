package net.nicolas.calcium.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.InactivityFpsLimiter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow @Final public GameOptions options;
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/InactivityFpsLimiter;update()I"))

    private int unlockTitleFps(InactivityFpsLimiter instance) {
        return this.options.getMaxFps().getValue();
    }

}