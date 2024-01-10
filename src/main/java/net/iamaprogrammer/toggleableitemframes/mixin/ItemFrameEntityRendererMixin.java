package net.iamaprogrammer.toggleableitemframes.mixin;


import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemFrameEntityRenderer.class)
public class ItemFrameEntityRendererMixin {
    @Unique
    private final MinecraftClient client = MinecraftClient.getInstance();
    @Unique
    private ClientPlayerEntity player;
    @Redirect(method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;getHeldItemStack()Lnet/minecraft/item/ItemStack;"))
    private ItemStack injected(ItemFrameEntity frame) {
        if (this.player != null) {
            BlockPos playerPos = this.player.getBlockPos();

            if (frame != null && !frame.getBlockPos().isWithinDistance(playerPos, ToggleableItemFramesClient.CONFIG.getRenderItemModelDistance())) {
                return ItemStack.EMPTY;
            }
        } else {
            this.player = this.client.player;
        }
        return frame.getHeldItemStack();
    }
}
