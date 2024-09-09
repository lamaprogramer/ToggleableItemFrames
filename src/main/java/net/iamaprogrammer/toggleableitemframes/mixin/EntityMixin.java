package net.iamaprogrammer.toggleableitemframes.mixin;

import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public class EntityMixin {
    @Shadow private World world;
    @Unique
    private final Entity TARGET = (Entity)(Object)this;

    @Inject(method = "isInvisible", at = @At("RETURN"), cancellable = true)
    private void toggleableitemframes_ShowInvisibleFramesWhenHeld(CallbackInfoReturnable<Boolean> cir) {
        if (this.TARGET instanceof ItemFrameEntity && this.world.isClient() && ToggleableItemFramesClient.CONFIG.shouldShowInvisibleFramesWhenHeld()) {
            MinecraftClient client = MinecraftClient.getInstance();
            ClientPlayerEntity player = client.player;

            if (player != null) {
                if (player.isHolding(Items.ITEM_FRAME) || player.isHolding(Items.GLOW_ITEM_FRAME)) {
                    cir.setReturnValue(false);
                    return;
                }
            }

            cir.setReturnValue(cir.getReturnValue() || ToggleableItemFramesClient.ALWAYS_INVISIBLE);
        }
    }
}
