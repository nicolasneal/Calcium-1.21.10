package net.nicolas.calcium.mixin.screens;

import net.minecraft.client.gui.screen.ingame.MountScreen;
import net.minecraft.client.gui.screen.ingame.NautilusScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MountScreen.class)
public class MountScreenMixin {

    @ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIIIIIII)V"), index = 7)
    private int calcium$modifyChestTextureY(int y) {
        return y - 1;
    }

    @ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MountScreen;drawSlot(Lnet/minecraft/client/gui/DrawContext;II)V", ordinal = 0), index = 2)
    private int calcium$modifySaddleTextureY(int y) {
        return y - 1;
    }

    @ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MountScreen;drawSlot(Lnet/minecraft/client/gui/DrawContext;II)V", ordinal = 1), index = 2)
    private int calcium$modifyArmorTextureY(int y) {
        return y - 1;
    }

    // Moves Nautilus Entity Overlay

    @ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"), index = 2)
    private int calcium$modifyNautilusTopY(int y) {
        return ((Object) this instanceof NautilusScreen) ? y - 4 : y;
    }

    @ModifyArg(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/InventoryScreen;drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V"), index = 4)
    private int calcium$modifyNautilusBottomY(int y) {
        return ((Object) this instanceof NautilusScreen) ? y - 4 : y;
    }

}