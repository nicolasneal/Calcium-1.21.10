package net.nicolas.calcium.mixin;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin extends HandledScreen<AnvilScreenHandler> {

    @Shadow private TextFieldWidget nameField;

    public AnvilScreenMixin(AnvilScreenHandler handler, net.minecraft.entity.player.PlayerInventory inventory, net.minecraft.text.Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "setup", at = @At("TAIL"))
    private void moveTextFieldWidget(CallbackInfo ci) {
        this.nameField.setX(this.x + 38);
        this.nameField.setY(this.y + 25);
    }
}