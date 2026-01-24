package net.nicolas.calcium.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nicolas.calcium.screen.CustomEnchantingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {

    @Inject(method = "createScreenHandlerFactory", at = @At("HEAD"), cancellable = true)
    private void openCustomScreen(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<NamedScreenHandlerFactory> cir) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof EnchantingTableBlockEntity tableEntity) {
            Text displayName = tableEntity.getDisplayName();
            NamedScreenHandlerFactory factory = new SimpleNamedScreenHandlerFactory((syncId, inv, player) -> new CustomEnchantingScreenHandler(syncId, inv, new SimpleInventory(11)), displayName);
            cir.setReturnValue(factory);
        }

    }

}