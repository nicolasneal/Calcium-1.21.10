package net.nicolas.calcium.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.nicolas.calcium.Calcium;
import net.nicolas.calcium.recipe.EnchantingRecipe;
import net.nicolas.calcium.recipe.EnchantingRecipeInput;
import net.nicolas.calcium.recipe.ModRecipes;
import net.nicolas.calcium.tag.ModTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomEnchantingScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final CraftingResultInventory outputInventory = new CraftingResultInventory();
    private final PlayerEntity player;

    public CustomEnchantingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(10));
    }

    public CustomEnchantingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {

        super(Calcium.CUSTOM_ENCHANTING_SCREEN_HANDLER, syncId);
        checkSize(inventory, 10);
        this.inventory = inventory;
        this.player = playerInventory.player;
        inventory.onOpen(playerInventory.player);

        if (inventory instanceof SimpleInventory simpleInv) {
            simpleInv.addListener(this::onContentChanged);
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new CustomSlot.Builder(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18).build());
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new CustomSlot.Builder(playerInventory, i, 8 + i * 18, 142).build());
        }

        this.addSlot(new CustomSlot.Builder(inventory, 0, 50, 44)
            .itemMode(SlotConfig.ItemMode.TAG)
            .allowedTag(ModTags.ENCHANTABLE)
            .stackMode(SlotConfig.StackMode.SINGLE)
            .tooltip(Text.translatable("tooltip.calcium.enchanting_table_item"))
            .build());

        int[][] slotPositions = {{14, 53}, {14, 35}, {14, 17}, {32, 17}, {50, 17}, {68, 17}, {86, 17}, {86, 35}, {86, 53}};
        for (int i = 1; i <= 9; i++) {
            this.addSlot(new CustomSlot.Builder(inventory, i, slotPositions[i - 1][0], slotPositions[i - 1][1]).itemMode(SlotConfig.ItemMode.ALL).stackMode(SlotConfig.StackMode.SINGLE).build());
        }

        this.addSlot(new Slot(this.outputInventory, 0, 142, 35) {

            @Override public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override public boolean canTakeItems(PlayerEntity player) {
                return true;
            }

            @Override public void onTakeItem(PlayerEntity player, ItemStack stack) {
                player.getEntityWorld().playSound(null, player.getBlockPos(), net.minecraft.sound.SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, player.getEntityWorld().random.nextFloat() * 0.1F + 0.9F);
                CustomEnchantingScreenHandler.this.inventory.setStack(0, ItemStack.EMPTY);
                for (int i = 1; i < 10; ++i) {
                    CustomEnchantingScreenHandler.this.inventory.removeStack(i, 1);
                }
            }

        });
    }

    @Override public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.inventory) {
            this.updateResult(this.player.getEntityWorld());
        }
    }

    private void updateResult(World world) {

        if (!world.isClient() && world instanceof net.minecraft.server.world.ServerWorld serverWorld) {
            ItemStack tablet = this.inventory.getStack(0);
            List<ItemStack> ingredients = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                ingredients.add(this.inventory.getStack(i));
            }
            EnchantingRecipeInput input = new EnchantingRecipeInput(tablet, ingredients);
            assert serverWorld.getServer() != null;
            Optional<RecipeEntry<EnchantingRecipe>> match = serverWorld.getServer()
                .getRecipeManager()
                .getFirstMatch(ModRecipes.ENCHANTING_TYPE, input, world, (RegistryKey<Recipe<?>>) null);
            if (match.isPresent()) {
                ItemStack result = match.get().value().craft(input, world.getRegistryManager());
                this.outputInventory.setStack(0, result);
            } else {
                this.outputInventory.setStack(0, ItemStack.EMPTY);
            }
        }

    }

    @Override public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot == 46) {
                if (!this.insertItem(originalStack, 0, 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, newStack);
            }
            else if (invSlot < 36) {
                if (!this.insertItem(originalStack, 36, 46, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(originalStack, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }
            else {
                slot.markDirty();
            }
            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, originalStack);
        }

        return newStack;

    }

    @Override public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.outputInventory.removeStack(0);
        this.dropInventory(player, this.inventory);
    }

}