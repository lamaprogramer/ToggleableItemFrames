package net.iamaprogrammer.toggleableitemframes.mixin;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


// Mixin for Custom NBT
@Mixin(ItemFrameEntity.class)
public abstract class ModifyItemFrameNbt extends AbstractDecorationEntity {
    @Shadow public abstract SoundEvent getRotateItemSound();
    protected ModifyItemFrameNbt(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "interact", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isClient:Z", opcode = Opcodes.GETFIELD, shift = At.Shift.AFTER), cancellable = true)
    private void toggleableitemframes_ToggleItemFrame(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!this.getWorld().isClient() && !player.isSpectator() && player.getMainHandStack().isEmpty() && player.isSneaking() && hand.equals(Hand.MAIN_HAND)) {
            this.setInvisible(!this.isInvisible());
            this.playSound(this.getRotateItemSound(), 1.0f, 1.0f);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
