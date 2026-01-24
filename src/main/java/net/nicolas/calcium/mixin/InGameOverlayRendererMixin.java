package net.nicolas.calcium.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.nicolas.calcium.fluid.ModFluids;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin {

    @Shadow @Final private MinecraftClient client;
    @Shadow @Final private VertexConsumerProvider vertexConsumers;
    @Unique private static final Identifier ECTOPLASM_TEXTURE = Identifier.of("calcium", "textures/block/ectoplasm_overlay.png");

    @Inject(method = "renderOverlays", at = @At("HEAD"))
    private void calcium$renderEctoplasmOverlay(boolean sleeping, float tickProgress, OrderedRenderCommandQueue queue, CallbackInfo ci) {

        if (this.client.player == null || sleeping) {
            return;
        }

        BlockPos pos = this.client.gameRenderer.getCamera().getBlockPos();
        FluidState fluidState = this.client.player.getEntityWorld().getFluidState(pos);

        if (fluidState.getFluid() == ModFluids.ECTOPLASM_STILL || fluidState.getFluid() == ModFluids.ECTOPLASM_FLOWING) {
            MatrixStack matrices = new MatrixStack();
            calcium$renderOverlay(this.client, matrices, this.vertexConsumers, pos);
        }

    }

    @Unique private void calcium$renderOverlay(MinecraftClient client, MatrixStack matrices, VertexConsumerProvider vertexConsumers, BlockPos pos) {

        assert client.player != null;
        float brightness = LightmapTextureManager.getBrightness(client.player.getEntityWorld().getDimension(), client.player.getEntityWorld().getLightLevel(pos));
        int color = ColorHelper.fromFloats(1.0F, brightness, brightness, brightness);

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayers.blockScreenEffect(ECTOPLASM_TEXTURE));
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();

        vertexConsumer.vertex(matrix4f, -1.0F, -1.0F, -0.5F).texture(4.0F, 4.0F).color(color);
        vertexConsumer.vertex(matrix4f, 1.0F, -1.0F, -0.5F).texture(0.0F, 4.0F).color(color);
        vertexConsumer.vertex(matrix4f, 1.0F, 1.0F, -0.5F).texture(0.0F, 0.0F).color(color);
        vertexConsumer.vertex(matrix4f, -1.0F, 1.0F, -0.5F).texture(4.0F, 0.0F).color(color);

    }

}