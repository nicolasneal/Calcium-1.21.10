package net.nicolas.calcium.screen;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.nicolas.calcium.Calcium;
import org.jspecify.annotations.Nullable;

public class CustomBeaconScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private final PropertyDelegate propertyDelegate;

    public CustomBeaconScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new ArrayPropertyDelegate(3), ScreenHandlerContext.EMPTY);
    }

    public CustomBeaconScreenHandler(int syncId, PlayerInventory inventory, PropertyDelegate propertyDelegate, ScreenHandlerContext context) {
        super(Calcium.CUSTOM_BEACON_SCREEN_HANDLER, syncId);

        checkDataCount(propertyDelegate, 3);
        this.propertyDelegate = propertyDelegate;
        this.context = context;
        this.addProperties(propertyDelegate);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 106 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 164));
        }

    }

    @Override public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, net.minecraft.block.Blocks.BEACON);
    }

    public int getProperties() {
        return this.propertyDelegate.get(0);
    }

    public @Nullable RegistryEntry<StatusEffect> getPrimaryEffect() {
        return getStatusEffectForRawId(this.propertyDelegate.get(1));
    }

    public @Nullable RegistryEntry<StatusEffect> getSecondaryEffect() {
        return getStatusEffectForRawId(this.propertyDelegate.get(2));
    }

    private static @Nullable RegistryEntry<StatusEffect> getStatusEffectForRawId(int id) {
        return id == 0 ? null : Registries.STATUS_EFFECT.getIndexedEntries().get(id - 1);
    }

    @Override public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (slotIndex >= 0 && slotIndex < 27) {
                if (!this.insertItem(originalStack, 27, 36, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= 27 && slotIndex < 36) {
                if (!this.insertItem(originalStack, 0, 27, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);

        }

        return newStack;

    }

    public void setEffects(java.util.Optional<RegistryEntry<StatusEffect>> primary, java.util.Optional<RegistryEntry<StatusEffect>> secondary) {

        if (primary.isPresent()) {
            this.propertyDelegate.set(1, Registries.STATUS_EFFECT.getRawId(primary.get().value()));
        } else {
            this.propertyDelegate.set(1, 0);
        }

        if (secondary.isPresent()) {
            this.propertyDelegate.set(2, Registries.STATUS_EFFECT.getRawId(secondary.get().value()));
        } else {
            this.propertyDelegate.set(2, 0);
        }

        this.context.run(net.minecraft.world.World::markDirty);
        this.sendContentUpdates();

    }

}