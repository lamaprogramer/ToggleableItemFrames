package net.iamaprogrammer.toggleableitemframes.mixin;

import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameEntityClientMixin extends AbstractDecorationEntity {
    @Unique
    private final MinecraftClient client = MinecraftClient.getInstance();
    @Unique
    private final ClientWorld world = this.client != null ? this.client.world : null;
    @Unique
    private final ClientPlayerEntity player = this.world != null ? this.client.player : null;

    protected ItemFrameEntityClientMixin(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

    protected ItemFrameEntityClientMixin(EntityType<? extends AbstractDecorationEntity> type, World world, BlockPos pos) {
        super(type, world, pos);
    }

    @Inject(method = "getHeldItemStack", at = @At("RETURN"), cancellable = true)
    private void toggleableitemframes_RenderItemModelDistance(CallbackInfoReturnable<ItemStack> cir) {
        if (this.client != null && this.world != null && this.player != null) {
            BlockPos playerPos = player.getBlockPos();
            BlockPos framePos = this.getBlockPos();

            if (!framePos.isWithinDistance(playerPos, ToggleableItemFramesClient.CONFIG.getRenderItemModelDistance())) {
                cir.setReturnValue(ItemStack.EMPTY);
            }
        }
        cir.setReturnValue(cir.getReturnValue());
    }
}
