package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.NautilusScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(NautilusScreenHandler.class)
public class NautilusScreenHandlerMixin {

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/NautilusScreenHandler$1;<init>(Lnet/minecraft/screen/NautilusScreenHandler;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;IIILnet/minecraft/util/Identifier;Lnet/minecraft/entity/passive/AbstractNautilusEntity;)V"), index = 6)
    private int calcium$modifySaddleSlotY(int y) {
        return 17;
    }

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/NautilusScreenHandler$2;<init>(Lnet/minecraft/screen/NautilusScreenHandler;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;IIILnet/minecraft/util/Identifier;Lnet/minecraft/entity/passive/AbstractNautilusEntity;)V"), index = 6)
    private int calcium$modifyArmorSlotY(int y) {
        return 35;
    }

}