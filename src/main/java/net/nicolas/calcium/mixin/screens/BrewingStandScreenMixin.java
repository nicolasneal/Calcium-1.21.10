package net.nicolas.calcium.mixin.screens;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BrewingStandScreen.class)
public abstract class BrewingStandScreenMixin extends HandledScreen<BrewingStandScreenHandler> {

    public BrewingStandScreenMixin(BrewingStandScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIIIIIII)V"))
    private void calcium$redirectBrewingTextures(DrawContext instance, RenderPipeline pipeline, Identifier texture, int textureWidth, int textureHeight, int u, int v, int x, int y, int width, int height) {
        String path = texture.getPath();
        if (path.contains("fuel_length")) {
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, this.x + 61, this.y + 42, width, height);
        }
        else if (path.contains("brew_progress")) {
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, this.x + 98, this.y + 14, width, height);
        }
        else if (path.contains("bubbles")) {
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, this.x + 63, this.y + 12 + (textureHeight - height), width, height);
        }
        else {
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, x, y, width, height);
        }
    }

}