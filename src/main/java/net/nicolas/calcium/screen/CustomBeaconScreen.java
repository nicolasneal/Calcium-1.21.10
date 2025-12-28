package net.nicolas.calcium.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.input.AbstractInput;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.c2s.play.UpdateBeaconC2SPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class CustomBeaconScreen extends HandledScreen<CustomBeaconScreenHandler> {

    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/container/beacon.png");

    static final Identifier BUTTON_TEXTURE = Identifier.ofVanilla("container/beacon/button");
    static final Identifier BUTTON_HIGHLIGHTED_TEXTURE = Identifier.ofVanilla("container/beacon/button_highlighted");
    static final Identifier BUTTON_SELECTED_TEXTURE = Identifier.ofVanilla("container/beacon/button_selected");
    static final Identifier BUTTON_DISABLED_TEXTURE = Identifier.ofVanilla("container/beacon/button_disabled");

    private final List<BeaconButtonWidget> buttons = new ArrayList<>();

    public CustomBeaconScreen(CustomBeaconScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 188;
    }

    @Override
    protected void init() {
        super.init();
        this.buttons.clear();

        int index = 0;

        for (int i = 0; i <= 2; ++i) {
            int count = BeaconBlockEntity.EFFECTS_BY_LEVEL.get(i).size();
            int width = count * 22 + (count - 1) * 2;
            for (int j = 0; j < count; ++j) {
                RegistryEntry<StatusEffect> effect = BeaconBlockEntity.EFFECTS_BY_LEVEL.get(i).get(j);
                EffectButtonWidget btn = new EffectButtonWidget(0, 0, effect, true, i);
                setWidgetPosition(btn, index);
                this.addDrawableChild(btn);
                this.buttons.add(btn);
                index++;
            }
        }

        int count = BeaconBlockEntity.EFFECTS_BY_LEVEL.get(3).size() + 1;

        for (int j = 0; j < count - 1; ++j) {
            RegistryEntry<StatusEffect> effect = BeaconBlockEntity.EFFECTS_BY_LEVEL.get(3).get(j);
            EffectButtonWidget btn = new EffectButtonWidget(0, 0, effect, false, 3);
            setWidgetPosition(btn, index);
            this.addDrawableChild(btn);
            this.buttons.add(btn);
            index++;
        }

        RegistryEntry<StatusEffect> dummy = BeaconBlockEntity.EFFECTS_BY_LEVEL.get(0).getFirst();
        UpgradeButtonWidget upgradeBtn = new UpgradeButtonWidget(0, 0, dummy);
        upgradeBtn.visible = false;
        setWidgetPosition(upgradeBtn, index);
        this.addDrawableChild(upgradeBtn);
        this.buttons.add(upgradeBtn);
    }

    private void setWidgetPosition(PressableWidget widget, int i) {
        if (i == 0) {
            widget.setX(this.x + 41);
            widget.setY(this.y + 16);
        } else if (i == 1) {
            widget.setX(this.x + 67);
            widget.setY(this.y + 16);
        } else if (i == 2) {
            widget.setX(this.x + 41);
            widget.setY(this.y + 42);
        } else if (i == 3) {
            widget.setX(this.x + 67);
            widget.setY(this.y + 42);
        } else if (i == 4) {
            widget.setX(this.x + 54);
            widget.setY(this.y + 68);
        } else if (i == 5) {
            widget.setX(this.x + 107);
            widget.setY(this.y + 42);
        } else if (i == 6) {
            widget.setX(this.x + 133);
            widget.setY(this.y + 42);
        }
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        int properties = this.handler.getProperties();
        this.buttons.forEach(button -> button.tick(properties));
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Environment(EnvType.CLIENT)
    interface BeaconButtonWidget {
        void tick(int level);
        void renderButton(DrawContext context, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    abstract static class BaseButtonWidget extends PressableWidget implements BeaconButtonWidget {
        protected boolean disabled;

        protected BaseButtonWidget(int x, int y) {
            super(x, y, 22, 22, ScreenTexts.EMPTY);
        }

        protected BaseButtonWidget(int x, int y, Text message) {
            super(x, y, 22, 22, message);
        }

        @Override public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
            this.drawIcon(context, mouseX, mouseY, delta);
        }

        public void drawIcon(DrawContext context, int mouseX, int mouseY, float delta) {
            Identifier identifier;
            if (!this.active) {
                identifier = BUTTON_DISABLED_TEXTURE;
            }
            else if (this.disabled) {
                identifier = BUTTON_SELECTED_TEXTURE;
            }
            else if (this.hovered) {
                identifier = BUTTON_HIGHLIGHTED_TEXTURE;
            }
            else {
                identifier = BUTTON_TEXTURE;
            }
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, identifier, this.getX(), this.getY(), this.width, this.height);
            this.renderExtra(context);
        }

        public abstract void onPress();
        protected abstract void renderExtra(DrawContext context);

        @Override public void appendClickableNarrations(NarrationMessageBuilder builder) {
            this.appendDefaultNarrations(builder);
        }

    }

    @Environment(EnvType.CLIENT) class EffectButtonWidget extends BaseButtonWidget {
        private final boolean primary;
        protected final int level;
        private RegistryEntry<StatusEffect> effect;
        private Identifier sprite;

        public EffectButtonWidget(int x, int y, RegistryEntry<StatusEffect> effect, boolean primary, int level) {
            super(x, y);
            this.primary = primary;
            this.level = level;
            this.init(effect);
        }

        protected void init(RegistryEntry<StatusEffect> effect) {
            this.effect = effect;
            this.sprite = InGameHud.getEffectTexture(effect);
            this.setTooltip(Tooltip.of(Text.translatable(effect.value().getTranslationKey())));
        }

        @Override public void onPress() {
            if (!this.disabled) {
                if (this.primary) {
                    Objects.requireNonNull(CustomBeaconScreen.this.client.getNetworkHandler()).sendPacket(
                            new UpdateBeaconC2SPacket(Optional.of(this.effect), Optional.empty())
                    );
                } else {
                    Objects.requireNonNull(CustomBeaconScreen.this.client.getNetworkHandler()).sendPacket(
                            new UpdateBeaconC2SPacket(Optional.ofNullable(CustomBeaconScreen.this.handler.getPrimaryEffect()), Optional.of(this.effect))
                    );
                }
            }
        }

        @Override public void onPress(AbstractInput input) {
            this.onPress();
        }

        @Override protected void renderExtra(DrawContext context) {
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, this.sprite, this.getX() + 2, this.getY() + 2, 18, 18);
        }

        @Override public void tick(int level) {
            this.active = this.level < level;
            this.disabled = this.effect.equals(this.primary ? CustomBeaconScreen.this.handler.getPrimaryEffect() : CustomBeaconScreen.this.handler.getSecondaryEffect());
        }

    }

    @Environment(EnvType.CLIENT) class UpgradeButtonWidget extends EffectButtonWidget {
        public UpgradeButtonWidget(int x, int y, RegistryEntry<StatusEffect> effect) {
            super(x, y, effect, false, 3);
        }

        @Override public void tick(int level) {
            RegistryEntry<StatusEffect> primary = CustomBeaconScreen.this.handler.getPrimaryEffect();
            if (primary != null) {
                this.visible = true;
                this.init(primary);
                super.tick(level);
            } else {
                this.visible = false;
            }
        }

        @Override protected void init(RegistryEntry<StatusEffect> effect) {
            super.init(effect);
            this.setTooltip(Tooltip.of(Text.translatable(effect.value().getTranslationKey()).append(" II")));
        }

    }

}