package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.HopperScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HopperScreenHandler.class)
public class HopperScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyHopperSlotY(int y) {
        return 19;
    }

}