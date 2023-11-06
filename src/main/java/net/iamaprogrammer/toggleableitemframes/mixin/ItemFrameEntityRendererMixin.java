package net.iamaprogrammer.toggleableitemframes.mixin;

import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemFrameEntityRenderer.class)
public abstract class ItemFrameEntityRendererMixin<T extends ItemFrameEntity> extends EntityRenderer<T> {

    protected ItemFrameEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @ModifyVariable(method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("STORE"), ordinal = 0)
    private boolean toggleableitemframes_CheckIfHoldingFrame(boolean value) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if (player != null) {
            if (player.isHolding(Items.ITEM_FRAME) || player.isHolding(Items.GLOW_ITEM_FRAME)) {
                return false;
            }
        }
        return value || ToggleableItemFramesClient.ALWAYS_INVISIBLE;
    }
}
