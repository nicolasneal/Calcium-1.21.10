package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.MerchantScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MerchantScreenHandler.class)
public class MerchantScreenHandlerMixin {

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/village/Merchant;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 0), index = 2)
    private int calcium$modifyInput1X(int x) {
        return 142;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/village/Merchant;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 0), index = 3)
    private int calcium$modifyInput1Y(int y) {
        return 44;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/village/Merchant;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 1), index = 2)
    private int calcium$modifyInput2X(int x) {
        return 164;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/village/Merchant;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V", ordinal = 1), index = 3)
    private int calcium$modifyInput2Y(int y) {
        return 44;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/village/Merchant;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/TradeOutputSlot;<init>(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/village/Merchant;Lnet/minecraft/village/MerchantInventory;III)V"), index = 4)
    private int calcium$modifyOutputX(int x) {
        return 222;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/village/Merchant;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/TradeOutputSlot;<init>(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/village/Merchant;Lnet/minecraft/village/MerchantInventory;III)V"), index = 5)
    private int calcium$modifyOutputY(int y) {
        return 44;
    }

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/village/Merchant;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/MerchantScreenHandler;addPlayerSlots(Lnet/minecraft/inventory/Inventory;II)V"), index = 1)
    private int calcium$modifyPlayerSlotsX(int x) {
        return 112;
    }

}