package net.nicolas.calcium.mixin.screens;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.ingame.RecipeBookScreen;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractFurnaceScreen.class)
public abstract class AbstractFurnaceScreenMixin extends RecipeBookScreen<AbstractFurnaceScreenHandler> {

    public AbstractFurnaceScreenMixin(AbstractFurnaceScreenHandler handler, net.minecraft.client.gui.screen.recipebook.RecipeBookWidget recipeBook, net.minecraft.entity.player.PlayerInventory inventory, net.minecraft.text.Text title) {
        super(handler, recipeBook, inventory, title);
    }

    @Redirect(method = "drawBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIIIIIII)V"))
    private void calcium$redirectFurnaceOverlays(DrawContext instance, RenderPipeline pipeline, Identifier texture, int textureWidth, int textureHeight, int u, int v, int x, int y, int width, int height) {

        if (textureWidth == 14 && textureHeight == 14) {
            int newX = 48;
            int baseNewY = 36;
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, this.x + newX, this.y + baseNewY + (14 - height), width, height);
        }

        else if (textureWidth == 24 && textureHeight == 16) {
            int newX = 72;
            int newY = 34;
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, this.x + newX, this.y + newY, width, height);
        }

    }

}