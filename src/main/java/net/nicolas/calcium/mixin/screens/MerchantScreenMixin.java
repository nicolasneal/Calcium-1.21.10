package net.nicolas.calcium.mixin.screens;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MerchantScreen.class)
public abstract class MerchantScreenMixin extends HandledScreen<MerchantScreenHandler> {

    public MerchantScreenMixin(MerchantScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void calcium$modifyScreenSize(MerchantScreenHandler handler, PlayerInventory inventory, Text title, CallbackInfo ci) {
        this.backgroundWidth = 277;
    }

    @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"))
    private void calcium$redirectErrorTexture(DrawContext instance, RenderPipeline pipeline, Identifier texture, int x, int y, int width, int height) {
        if (texture.getPath().contains("out_of_stock")) {
            instance.drawGuiTexture(pipeline, texture, this.x + 182, this.y + 41, width, height);
        } else {
            instance.drawGuiTexture(pipeline, texture, x, y, width, height);
        }
    }

    @Redirect(method = "drawLevelInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"))
    private void calcium$redirectExpBarBackground(DrawContext instance, RenderPipeline pipeline, Identifier texture, int x, int y, int width, int height) {
        if (texture.getPath().contains("experience_bar")) {
            instance.drawGuiTexture(pipeline, texture, this.x + 138, this.y + 15, width, height);
        }
        else {
            instance.drawGuiTexture(pipeline, texture, x, y, width, height);
        }
    }

    @Redirect(method = "drawLevelInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIIIIIII)V"))
    private void calcium$redirectExpBarProgress(DrawContext instance, RenderPipeline pipeline, Identifier texture, int textureWidth, int textureHeight, int u, int v, int x, int y, int width, int height) {
        if (texture.getPath().contains("experience_bar")) {
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, x, this.y + 15, width, height);
        }
        else {
            instance.drawGuiTexture(pipeline, texture, textureWidth, textureHeight, u, v, x, y, width, height);
        }
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen$WidgetButtonPage;<init>(Lnet/minecraft/client/gui/screen/ingame/MerchantScreen;IIILnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V"), index = 1)
    private int calcium$modifyButtonX(int x) {
        return x + 3;
    }

    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen$WidgetButtonPage;<init>(Lnet/minecraft/client/gui/screen/ingame/MerchantScreen;IIILnet/minecraft/client/gui/widget/ButtonWidget$PressAction;)V"), index = 2)
    private int calcium$modifyButtonY(int y) {
        return y - 10;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen;renderFirstBuyItem(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;II)V"), index = 3)
    private int calcium$modifyFirstBuyItemX(int x) {
        return x + 3;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen;renderFirstBuyItem(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;II)V"), index = 4)
    private int calcium$modifyFirstBuyItemY(int y) {
        return y - 10;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen;renderArrow(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/village/TradeOffer;II)V"), index = 2)
    private int calcium$modifyArrowX(int x) {
        return x - 13;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen;renderArrow(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/village/TradeOffer;II)V"), index = 3)
    private int calcium$modifyArrowY(int y) {
        return y - 10;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;II)V"), index = 1)
    private int calcium$modifyDrawItemX(int x) {
        return x + 3;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;II)V"), index = 2)
    private int calcium$modifyDrawItemY(int y) {
        return y - 10;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawStackOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;II)V"), index = 2)
    private int calcium$modifyDrawOverlayX(int x) {
        return x + 3;
    }

    @ModifyArg(method = "renderMain", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawStackOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;II)V"), index = 3)
    private int calcium$modifyDrawOverlayY(int y) {
        return y - 12;
    }

    @ModifyConstant(method = {"renderScrollbar", "mouseClicked"}, constant = @Constant(intValue = 94))
    private int calcium$modifyScrollbarX(int originalValue) {
        return 97;
    }

    @ModifyConstant(method = {"renderScrollbar", "mouseClicked", "mouseDragged"}, constant = @Constant(intValue = 18))
    private int calcium$modifyScrollbarY(int originalValue) {
        return 8;
    }

}