package net.nicolas.calcium.mixin.screens;

import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @ModifyArg(method = "getForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 0), index = 1)
    private static int calcium$modifyInput1X(int x) {
        return 25;
    }

    @ModifyArg(method = "getForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 0), index = 2)
    private static int calcium$modifyInput1Y(int y) {
        return 48;
    }

    @ModifyArg(method = "getForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 1), index = 1)
    private static int calcium$modifyInput2X(int x) {
        return 74;
    }

    @ModifyArg(method = "getForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;input(IIILjava/util/function/Predicate;)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;", ordinal = 1), index = 2)
    private static int calcium$modifyInput2Y(int y) {
        return 48;
    }

    @ModifyArg(method = "getForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;output(III)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;"), index = 1)
    private static int calcium$modifyOutputX(int x) {
        return 130;
    }

    @ModifyArg(method = "getForgingSlotsManager", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;output(III)Lnet/minecraft/screen/slot/ForgingSlotsManager$Builder;"), index = 2)
    private static int calcium$modifyOutputY(int y) {
        return 48;
    }

}