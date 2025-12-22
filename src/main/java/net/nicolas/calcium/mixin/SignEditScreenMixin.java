package net.nicolas.calcium.mixin;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignEditScreen.class)
public abstract class SignEditScreenMixin extends AbstractSignEditScreen {

    @Unique
    private Identifier guiTexture;

    public SignEditScreenMixin(SignBlockEntity blockEntity, boolean front, boolean filtered) {
        super(blockEntity, front, filtered);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void initTexture(CallbackInfo ci) {
        this.guiTexture = Identifier.ofVanilla("textures/gui/signs/" + this.signType.name() + ".png");
    }

    @Inject(method = "renderSignBackground", at = @At("HEAD"), cancellable = true)
    private void renderSignBackgroundReplacement(DrawContext context, CallbackInfo ci) {
        if (this.guiTexture == null) return;

        context.getMatrices().pushMatrix();
        context.getMatrices().translate(-48.0f, -26.0f);
        context.getMatrices().scale(6.0f, 6.0f);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, this.guiTexture, 0, 0, 0.0f, 0.0f, 16, 8, 16, 8);
        context.getMatrices().popMatrix();

        ci.cancel();

    }

}