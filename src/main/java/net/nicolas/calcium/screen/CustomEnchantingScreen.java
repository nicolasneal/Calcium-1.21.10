package net.nicolas.calcium.screen;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CustomEnchantingScreen extends HandledScreen<CustomEnchantingScreenHandler> {

    private static final Identifier TEXTURE = Identifier.of("calcium", "textures/gui/container/enchanting_table.png");
    private static final Identifier ERROR = Identifier.of("calcium", "textures/gui/sprites/container/enchanting_table/error.png");
    private static final Identifier[] LEVEL_SPRITES = new Identifier[]{
            Identifier.of("calcium", "textures/gui/sprites/container/enchanting_table/level_1.png"),
            Identifier.of("calcium", "textures/gui/sprites/container/enchanting_table/level_2.png"),
            Identifier.of("calcium", "textures/gui/sprites/container/enchanting_table/level_3.png")
    };

    public CustomEnchantingScreen(CustomEnchantingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);

        Slot slot = this.getSlotUnderMouse();
        if (slot instanceof CustomSlot customSlot) {
            Text tooltip = customSlot.getTooltip();
            if (tooltip != null) {
                context.drawTooltip(this.textRenderer, tooltip, mouseX, mouseY);
            }
        }

    }

    private Slot getSlotUnderMouse() {

        double mouseX = this.client.mouse.getX() * this.client.getWindow().getScaledWidth() / this.client.getWindow().getWidth();
        double mouseY = this.client.mouse.getY() * this.client.getWindow().getScaledHeight() / this.client.getWindow().getHeight();
        int guiLeft = this.x;
        int guiTop = this.y;
        int relativeX = (int) mouseX - guiLeft;
        int relativeY = (int) mouseY - guiTop;

        for (Slot slot : this.handler.slots) {
            int centerX = slot.x + 8;
            int centerY = slot.y + 8;
            if (relativeX >= centerX - 18 / 2 && relativeX < centerX + 18 / 2 &&
                    relativeY >= centerY - 18 / 2 && relativeY < centerY + 18 / 2) {
                return slot;
            }
        }

        return null;

    }

    @Override protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight, 256, 256);

        boolean mainInputOccupied = this.handler.slots.get(37).hasStack();

        int ingredientCount = 0;
        for (int i = 38; i <= 46; i++) {
            if (this.handler.slots.get(i).hasStack()) {
                ingredientCount++;
            }
        }

        if (mainInputOccupied && ingredientCount > 0) {
            int spriteIndex = (ingredientCount - 1) / 3;
            Identifier sprite = LEVEL_SPRITES[spriteIndex];
            context.drawTexture(RenderPipelines.GUI_TEXTURED, sprite, x + 73, y + 65, 0, 0, 12, 10, 12, 10);
        }

        int cost = this.handler.getLevelCost();
        if (cost > 0) {

            assert this.client.player != null;
            boolean enoughLevels = this.client.player.getAbilities().creativeMode || this.client.player.experienceLevel >= cost;
            ItemStack lapisStack = this.handler.getSlot(36).getStack();
            boolean enoughLapis = !lapisStack.isEmpty() && lapisStack.isOf(Items.LAPIS_LAZULI) && lapisStack.getCount() >= ingredientCount;

            if (!enoughLevels || !enoughLapis) {
                context.drawTexture(RenderPipelines.GUI_TEXTURED, ERROR, x + 126, y + 37, 0, 0, 11, 12, 11, 12);
            }

        }

    }

    @Override protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

}