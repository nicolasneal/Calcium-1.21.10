package net.nicolas.calcium.mixin.screens;

import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ForgingScreen<SmithingScreenHandler> {

    public SmithingScreenMixin(SmithingScreenHandler handler, PlayerInventory inventory, Text title, Identifier texture) {
        super(handler, inventory, title, texture);
    }

    @ModifyArg(method = "drawInvalidRecipeArrow", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"), index = 2)
    private int calcium$modifyErrorTextureX(int x) {
        return this.x + 56;
    }

    @ModifyArg(method = "drawInvalidRecipeArrow", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"), index = 3)
    private int calcium$modifyErrorTextureY(int y) {
        return this.y + 33;
    }

    @ModifyArg(method = "renderSlotTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/SmithingScreen;isPointWithinBounds(IIIIDD)Z"), index = 0)
    private int calcium$modifyErrorTooltipX(int x) {
        return 15;
    }

    @ModifyArg(method = "renderSlotTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/SmithingScreen;isPointWithinBounds(IIIIDD)Z"), index = 1)
    private int calcium$modifyErrorTooltipY(int y) {
        return 25;
    }

    @ModifyArgs(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;addEntity(Lnet/minecraft/client/render/entity/state/EntityRenderState;FLorg/joml/Vector3f;Lorg/joml/Quaternionf;Lorg/joml/Quaternionf;IIII)V"))
    private void calcium$modifyArmorStandPosition(Args args) {
        args.set(5, this.x + 125);
        args.set(6, this.y + 9);
        args.set(7, this.x + 165);
        args.set(8, this.y + 69);
    }

}