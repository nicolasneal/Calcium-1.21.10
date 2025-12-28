package net.nicolas.calcium.mixin.screens;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin extends HandledScreen<AnvilScreenHandler> {

    public AnvilScreenMixin(AnvilScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @ModifyArg(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;<init>(Lnet/minecraft/client/font/TextRenderer;IIIILnet/minecraft/text/Text;)V"), index = 1)
    private int calcium$modifyTextFieldWidgetX(int x) {
        return this.x + 38;
    }

    @ModifyArg(method = "setup", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;<init>(Lnet/minecraft/client/font/TextRenderer;IIIILnet/minecraft/text/Text;)V"), index = 2)
    private int calcium$modifyTextFieldWidgetY(int y) {
        return this.y + 21;
    }

    @ModifyArg(method = "drawInvalidRecipeArrow", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"), index = 2)
    private int calcium$modifyErrorTextureX(int x) {
        return this.x + 94;
    }

    @Redirect(method = "drawForeground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"))
    private void calcium$cancelOriginalBox(DrawContext instance, int x1, int y1, int x2, int y2, int color) {
    }

    @Redirect(method = "drawForeground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"))
    private void calcium$drawLeftAlignedCost(DrawContext context, TextRenderer textRenderer, Text text, int x, int y, int color) {
        context.fill(7, 68, 11 + textRenderer.getWidth(text), 80, 1325400064);
        context.drawTextWithShadow(textRenderer, text, 9, 70, color);
    }

}