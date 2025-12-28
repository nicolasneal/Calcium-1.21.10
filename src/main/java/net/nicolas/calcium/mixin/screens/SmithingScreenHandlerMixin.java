package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.SmithingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SmithingScreenHandler.class)
public class SmithingScreenHandlerMixin {

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 0), index = 1)
    private static int calcium$modifyTemplateSlotX(int x) {
        return 15;
    }

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 0), index = 2)
    private static int calcium$modifyTemplateSlotY(int y) {
        return 25;
    }

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 1), index = 1)
    private static int calcium$modifyEquipmentSlotX(int x) {
        return 35;
    }

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 1), index = 2)
    private static int calcium$modifyEquipmentSlotY(int y) {
        return 25;
    }

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 2), index = 1)
    private static int calcium$modifyMaterialSlotX(int x) {
        return 25;
    }

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 2), index = 2)
    private static int calcium$modifyMaterialSlotY(int y) {
        return 45;
    }

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;output(III)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;"), index = 1)
    private static int calcium$modifyOutputSlotX(int x) {
        return 93;
    }

    @ModifyArg(method = "createForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;output(III)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;"), index = 2)
    private static int calcium$modifyOutputSlotY(int y) {
        return 35;
    }

}