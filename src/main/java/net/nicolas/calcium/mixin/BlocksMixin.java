package net.nicolas.calcium.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.nicolas.calcium.block.custom.CustomMelon;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Unique
    private static boolean isRegisteringMelon = false;

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/Blocks;MELON:Lnet/minecraft/block/Block;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.BEFORE
            )
    )
    private static void beforeRegisterMelon(CallbackInfo ci) {
        isRegisteringMelon = true;
    }

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/Blocks;MELON:Lnet/minecraft/block/Block;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void afterRegisterMelon(CallbackInfo ci) {
        isRegisteringMelon = false;
    }

    @Unique
    private static Block onNewBlock(AbstractBlock.Settings settings) {
        if (isRegisteringMelon) {
            return new CustomMelon(settings);
        }
        return new Block(settings);
    }
    
}