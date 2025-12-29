package net.nicolas.calcium.mixin.screens;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.gui.screen.ingame.HangingSignEditScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin extends Screen {

    protected AbstractSignEditScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/AbstractSignEditScreen;renderSign(Lnet/minecraft/client/gui/DrawContext;)V"))
    private void calcium$drawBackgroundTexture(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {

        int bgWidth;
        int bgHeight;
        Identifier backgroundTexture;

        if ((Object) this instanceof HangingSignEditScreen) {
            bgWidth = 110;
            bgHeight = 86;
            backgroundTexture = Identifier.ofVanilla("textures/gui/container/hanging_sign.png");
        }
        else {
            bgWidth = 122;
            bgHeight = 74;
            backgroundTexture = Identifier.ofVanilla("textures/gui/container/sign.png");
        }

        int x = (this.width - bgWidth) / 2;
        int y = (this.height - bgHeight) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, backgroundTexture, x, y, 0.0F, 0.0F, bgWidth, bgHeight, 128, 128);

    }

    @Redirect(method = "renderSign", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/AbstractSignEditScreen;getYOffset()F"))
    private float calcium$modifySignYPosition(AbstractSignEditScreen instance) {
        if (instance instanceof HangingSignEditScreen) {
            return 235.0F;
        }
        else {
            return 242.0F;
        }
    }

    @Inject(method = "renderSign", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/AbstractSignEditScreen;renderSignText(Lnet/minecraft/client/gui/DrawContext;)V"))
    private void calcium$offsetSignText(DrawContext context, CallbackInfo ci) {
        if ((Object) this instanceof HangingSignEditScreen) {
            context.getMatrices().translate(0.0F, 5.0F);
        }
    }

    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/AbstractSignEditScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;"))
    private Element calcium$preventDoneButton(AbstractSignEditScreen instance, Element element) {
        return null;
    }

}