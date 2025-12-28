package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.HorseScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HorseScreenHandler.class)
public class HorseScreenHandlerMixin {

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/HorseScreenHandler$1;<init>(Lnet/minecraft/screen/HorseScreenHandler;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;IIILnet/minecraft/util/Identifier;Lnet/minecraft/entity/passive/AbstractHorseEntity;)V"), index = 6)
    private int calcium$modifySaddleSlotY(int y) {
        return 17;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/HorseScreenHandler$2;<init>(Lnet/minecraft/screen/HorseScreenHandler;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;IIILnet/minecraft/util/Identifier;Lnet/minecraft/entity/passive/AbstractHorseEntity;Z)V"), index = 6)
    private int calcium$modifyArmorSlotY(int y) {
        return 35;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/Slot;<init>(Lnet/minecraft/inventory/Inventory;III)V"), index = 3)
    private int calcium$modifyChestSlotY(int y) {
        return y - 1;
    }

}