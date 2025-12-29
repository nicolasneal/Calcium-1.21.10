package net.nicolas.calcium.mixin.screens;

import net.minecraft.client.gui.screen.ingame.CrafterScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CrafterScreen.class)
public class CrafterScreenMixin {

    @ModifyConstant(method = "drawArrowTexture", constant = @Constant(intValue = 9))
    private int calcium$modifyArrowX(int constant) {
        return 6;
    }

    @ModifyConstant(method = "drawArrowTexture", constant = @Constant(intValue = 48))
    private int calcium$modifyArrowY(int constant) {
        return 49;
    }

}