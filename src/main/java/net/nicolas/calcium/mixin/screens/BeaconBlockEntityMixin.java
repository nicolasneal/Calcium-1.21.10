package net.nicolas.calcium.mixin.screens;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.nicolas.calcium.screen.CustomBeaconScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin extends BlockEntity {

    @Shadow private PropertyDelegate propertyDelegate;

    public BeaconBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "createMenu", at = @At("HEAD"), cancellable = true)
    private void replaceMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player, CallbackInfoReturnable<ScreenHandler> cir) {
        cir.setReturnValue(new CustomBeaconScreenHandler(syncId, playerInventory, this.propertyDelegate, ScreenHandlerContext.create(this.world, this.pos)));
    }

}