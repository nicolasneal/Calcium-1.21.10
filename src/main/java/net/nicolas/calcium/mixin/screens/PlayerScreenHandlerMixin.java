package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.PlayerScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerScreenHandler.class)
public class PlayerScreenHandlerMixin {

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;addInputSlots(II)V"), index = 0)
    private int calcium$modifyInputSlotsX(int x) {
        return 117;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;addInputSlots(II)V"), index = 1)
    private int calcium$modifyInputSlotsY(int y) {
        return 11;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;addResultSlot(Lnet/minecraft/entity/player/PlayerEntity;II)Lnet/minecraft/screen/slot/Slot;"), index = 1)
    private int calcium$modifyResultSlotX(int x) {
        return 126;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/PlayerScreenHandler;addResultSlot(Lnet/minecraft/entity/player/PlayerEntity;II)Lnet/minecraft/screen/slot/Slot;"), index = 2)
    private int calcium$modifyResultSlotY(int y) {
        return 58;
    }

}