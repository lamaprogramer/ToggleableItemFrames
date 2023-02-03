package net.iamaprogrammer.toggleableitemframes.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFrameEntityRenderer.class)
public abstract class ModifyItemFrameRenderer<T extends ItemFrameEntity> extends EntityRenderer<T> {

    protected ModifyItemFrameRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(method = "render(Lnet/minecraft/entity/decoration/ItemFrameEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/ItemFrameEntity;isInvisible()Z"))
    private void injected(T itemFrameEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if (!MinecraftClient.getInstance().isInSingleplayer() && MinecraftClient.getInstance().player != null) {
            if (itemFrameEntity.world.isClient() && !(MinecraftClient.getInstance().player.isHolding(Items.ITEM_FRAME) || MinecraftClient.getInstance().player.isHolding(Items.GLOW_ITEM_FRAME))) {
                itemFrameEntity.setInvisible(true);
            }
        }
    }

}
