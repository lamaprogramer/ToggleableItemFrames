package net.iamaprogrammer.toggleableitemframes.mixin;

import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameEntityRendererMixin {
    @Unique
    private final MinecraftClient client = MinecraftClient.getInstance();
    @Redirect(method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    private boolean injected(ItemStack instance) {
        ClientPlayerEntity player = this.client.player;
        if (player != null && !instance.isEmpty()) {
            ItemFrameEntity frame = instance.getFrame();

            if (frame != null) {
                return !frame.getBlockPos().isWithinDistance(player.getBlockPos(), ToggleableItemFramesClient.CONFIG.getRenderItemModelDistance());
            }
        }
        return instance.isEmpty();
    }
}