package net.nicolas.calcium.mixin.screens;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.StonecutterScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StonecutterScreen.class)
public abstract class StonecutterScreenMixin extends HandledScreen<StonecutterScreenHandler> {

    public StonecutterScreenMixin(StonecutterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @ModifyConstant(method = {"drawBackground", "drawMouseoverTooltip", "mouseClicked"}, constant = @Constant(intValue = 52))
    private int calcium$modifyRecipeGridX(int constant) {
        return 48;
    }

    @ModifyConstant(method = {"drawBackground", "drawMouseoverTooltip", "mouseClicked"}, constant = @Constant(intValue = 14))
    private int calcium$modifyRecipeGridY(int constant) {
        return 15;
    }

    @ModifyConstant(method = {"drawBackground", "mouseClicked"}, constant = @Constant(intValue = 119))
    private int calcium$modifyScrollbarX(int constant) {
        return 116;
    }

    @Unique int ScrollBarY = 16;

    @ModifyConstant(method = "drawBackground", constant = @Constant(intValue = 15, ordinal = 0))
    private int calcium$modifyScrollbarVisualY(int constant) {
        return ScrollBarY;
    }

    @ModifyConstant(method = "mouseClicked", constant = @Constant(intValue = 9))
    private int calcium$modifyScrollbarClickY(int constant) {
        return ScrollBarY - 6;
    }

    @ModifyConstant(method = "mouseDragged", constant = @Constant(intValue = 14))
    private int calcium$modifyScrollbarDragY(int constant) {
        return ScrollBarY - 1;
    }

    @ModifyConstant(method = "drawBackground", constant = @Constant(floatValue = 41.0F))
    private float calcium$modifyScrollTravelFloat(float constant) {
        return 39.0F;
    }


    @ModifyConstant(method = "mouseClicked", constant = @Constant(intValue = 54))
    private int calcium$modifyClickAreaHeight(int constant) {
        return 52;
    }

    @ModifyConstant(method = "mouseDragged", constant = @Constant(intValue = 54))
    private int calcium$modifyDragAreaHeight(int constant) {
        return 52;
    }

}