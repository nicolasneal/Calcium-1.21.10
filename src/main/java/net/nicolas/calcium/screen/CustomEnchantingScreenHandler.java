package net.nicolas.calcium.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.Property;
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
    public final Property levelCost = Property.create();

    public CustomEnchantingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(11));
    }

    public CustomEnchantingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {

        super(Calcium.CUSTOM_ENCHANTING_SCREEN_HANDLER, syncId);
        checkSize(inventory, 11);
        this.inventory = inventory;
        this.player = playerInventory.player;
        inventory.onOpen(playerInventory.player);
        this.addProperty(this.levelCost);

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

        this.addSlot(new CustomSlot.Builder(inventory, 0, 12, 35).itemMode(SlotConfig.ItemMode.FIXED).fixedItem(Items.LAPIS_LAZULI).stackMode(SlotConfig.StackMode.STACK).tooltip(Text.translatable("tooltip.calcium.enchanting_table_fuel")).build());

        this.addSlot(new CustomSlot.Builder(inventory, 1, 71, 44).itemMode(SlotConfig.ItemMode.TAG).allowedTag(ModTags.ENCHANTABLE).stackMode(SlotConfig.StackMode.SINGLE).tooltip(Text.translatable("tooltip.calcium.enchanting_table_item")).build());

        int[][] slotPositions = {{35, 53}, {35, 35}, {35, 17}, {53, 17}, {71, 17}, {89, 17}, {107, 17}, {107, 35}, {107, 53}};
        for (int i = 0; i < 9; i++) {
            this.addSlot(new CustomSlot.Builder(inventory, i + 2, slotPositions[i][0], slotPositions[i][1]).itemMode(SlotConfig.ItemMode.ALL).stackMode(SlotConfig.StackMode.SINGLE).build());
        }

        this.addSlot(new Slot(this.outputInventory, 0, 144, 35) {

            @Override public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override public boolean canTakeItems(PlayerEntity player) {

                if (player.getAbilities().creativeMode) return true;

                int ingredientCount = 0;
                for (int i = 2; i < 11; i++) {
                    if (!CustomEnchantingScreenHandler.this.inventory.getStack(i).isEmpty()) {
                        ingredientCount++;
                    }
                }

                int xpCost = CustomEnchantingScreenHandler.this.getXpCost(ingredientCount);
                int lapisCost = ingredientCount;
                ItemStack lapis = CustomEnchantingScreenHandler.this.inventory.getStack(0);
                return player.experienceLevel >= xpCost && !lapis.isEmpty() && lapis.getCount() >= lapisCost;

            }

            @Override public void onTakeItem(PlayerEntity player, ItemStack stack) {

                player.getEntityWorld().playSound(null, player.getBlockPos(), net.minecraft.sound.SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, net.minecraft.sound.SoundCategory.BLOCKS, 1.0F, player.getEntityWorld().random.nextFloat() * 0.1F + 0.9F);

                int ingredientCount = 0;
                for (int i = 2; i < 11; i++) {
                    if (!CustomEnchantingScreenHandler.this.inventory.getStack(i).isEmpty()) {
                        ingredientCount++;
                    }
                }

                int xpCost = CustomEnchantingScreenHandler.this.getXpCost(ingredientCount);
                int lapisCost = ingredientCount;

                if (!player.getAbilities().creativeMode) {
                    player.addExperienceLevels(-xpCost);
                }

                CustomEnchantingScreenHandler.this.inventory.removeStack(0, lapisCost);
                CustomEnchantingScreenHandler.this.inventory.setStack(1, ItemStack.EMPTY);

                for (int i = 2; i < 11; ++i) {
                    ItemStack ingredientStack = CustomEnchantingScreenHandler.this.inventory.getStack(i);
                    if (!ingredientStack.isEmpty()) {
                        ItemStack remainder = ingredientStack.getRecipeRemainder();

                        ingredientStack.decrement(1);

                        if (!remainder.isEmpty()) {
                            if (ingredientStack.isEmpty()) {
                                CustomEnchantingScreenHandler.this.inventory.setStack(i, remainder);
                            } else {
                                if (!player.getInventory().insertStack(remainder)) {
                                    player.dropItem(remainder, false);
                                }
                            }
                        }
                    }
                }

            }

        });

    }

    private int getXpCost(int ingredientCount) {
        if (ingredientCount <= 3) return 1;
        if (ingredientCount <= 6) return 2;
        return 3;
    }

    @Override public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.inventory) {
            this.updateResult(this.player.getEntityWorld());
        }
    }

    private void updateResult(World world) {

        if (!world.isClient() && world instanceof net.minecraft.server.world.ServerWorld serverWorld) {

            ItemStack tablet = this.inventory.getStack(1);
            List<ItemStack> ingredients = new ArrayList<>();
            for (int i = 2; i < 11; i++) {
                ItemStack stack = this.inventory.getStack(i);
                if (!stack.isEmpty()) {
                    ingredients.add(stack);
                }
            }

            EnchantingRecipeInput input = new EnchantingRecipeInput(tablet, ingredients);
            assert serverWorld.getServer() != null;
            Optional<RecipeEntry<EnchantingRecipe>> match = serverWorld.getServer()
                .getRecipeManager()
                .getFirstMatch(ModRecipes.ENCHANTING_TYPE, input, world, (RegistryKey<Recipe<?>>) null);

            if (match.isPresent()) {
                int ingredientCount = ingredients.size();
                int xpCost = this.getXpCost(ingredientCount);

                this.levelCost.set(xpCost);

                ItemStack lapis = this.inventory.getStack(0);
                boolean enoughLevels = this.player.getAbilities().creativeMode || this.player.experienceLevel >= xpCost;
                boolean enoughLapis = !lapis.isEmpty() && lapis.isOf(Items.LAPIS_LAZULI) && lapis.getCount() >= ingredientCount;

                if (enoughLevels && enoughLapis) {
                    ItemStack result = match.get().value().craft(input, world.getRegistryManager());
                    this.outputInventory.setStack(0, result);
                } else {
                    this.outputInventory.setStack(0, ItemStack.EMPTY);
                }

            }
            else {
                this.outputInventory.setStack(0, ItemStack.EMPTY);
                this.levelCost.set(0);
            }

        }

    }

    @Override public ItemStack quickMove(PlayerEntity player, int invSlot) {

        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);

        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();

            if (invSlot == 47) {
                if (!this.insertItem(originalStack, 0, 36, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(originalStack, newStack);
            }
            else if (invSlot < 36) {
                if (originalStack.isOf(Items.LAPIS_LAZULI)) {
                    if (!this.insertItem(originalStack, 36, 37, false)) {
                        if (!this.insertItem(originalStack, 37, 47, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                } else {
                    if (!this.insertItem(originalStack, 37, 47, false)) {
                        return ItemStack.EMPTY;
                    }
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

    public int getLevelCost() {
        return this.levelCost.get();
    }

}