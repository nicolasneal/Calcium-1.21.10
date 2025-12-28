package net.nicolas.calcium.mixin.screens;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CartographyTableScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.map.MapState;
import net.minecraft.screen.CartographyTableScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CartographyTableScreen.class)
public abstract class CartographyTableScreenMixin extends HandledScreen<CartographyTableScreenHandler> {

    public CartographyTableScreenMixin(CartographyTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"))
    private void calcium$redirectErrorTexture(DrawContext instance, RenderPipeline pipeline, Identifier texture, int x, int y, int width, int height) {
        instance.drawGuiTexture(pipeline, texture, this.x + 31, this.y + 32, width, height);
    }

    @Shadow private void drawMap(DrawContext context, MapIdComponent mapId, MapState mapState, int x, int y, float scale) {}

    @Redirect(method = "drawMap(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/component/type/MapIdComponent;Lnet/minecraft/item/map/MapState;ZZZZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"))
    private void calcium$redirectMapOverlays(DrawContext instance, RenderPipeline pipeline, Identifier texture, int x, int y, int width, int height) {
        String path = texture.getPath();

        if (path.contains("scaled_map")) {
            instance.drawGuiTexture(pipeline, texture, this.x + 64, this.y + 10, width, height);
        } else if (path.contains("duplicated_map")) {
            if (x == this.x + 83) {
                instance.drawGuiTexture(pipeline, texture, this.x + 80, this.y + 10, width, height);
            }
            else {
                instance.drawGuiTexture(pipeline, texture, this.x + 64, this.y + 26, width, height);
            }
        } else if (path.contains("map")) { // Standard map background
            instance.drawGuiTexture(pipeline, texture, this.x + 64, this.y + 10, width, height);
        } else if (path.contains("locked")) { // Lock icon
            instance.drawGuiTexture(pipeline, texture, this.x + 115, this.y + 57, width, height);
        } else {
            instance.drawGuiTexture(pipeline, texture, x, y, width, height);
        }
    }

    @Redirect(method = "drawMap(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/component/type/MapIdComponent;Lnet/minecraft/item/map/MapState;ZZZZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/CartographyTableScreen;drawMap(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/component/type/MapIdComponent;Lnet/minecraft/item/map/MapState;IIF)V"))
    private void calcium$redirectMapContent(CartographyTableScreen instance, DrawContext context, MapIdComponent mapId, MapState mapState, int x, int y, float scale) {
        if (scale == 0.226F) {
            this.drawMap(context, mapId, mapState, this.x + 82, this.y + 28, scale);
        }
        else if (scale == 0.34F) {
            if (y == this.y + 16) {
                this.drawMap(context, mapId, mapState, this.x + 83, this.y + 13, scale);
            }
            else {
                this.drawMap(context, mapId, mapState, this.x + 67, this.y + 29, scale);
            }
        }
        else {
            this.drawMap(context, mapId, mapState, this.x + 68, this.y + 14, scale);
        }
    }

}