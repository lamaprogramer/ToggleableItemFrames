package net.iamaprogrammer.toggleableitemframes.mixin;


import net.iamaprogrammer.toggleableitemframes.util.IModifyItemFrameNbt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFrameEntity.class)
public abstract class ModifyItemFrameNbt implements IModifyItemFrameNbt {
    private boolean isCurrentlyInvisible = false;

    @Override
    public boolean getCurrentlyInvisible() {
        return isCurrentlyInvisible;
    }
    @Override
    public void setCurrentlyInvisible(boolean bool) {
        isCurrentlyInvisible = bool;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void addCustomNbt(NbtCompound nbt, CallbackInfo info) {
        nbt.putBoolean("isCurrentlyInvisible", isCurrentlyInvisible);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void readCustomNbt(NbtCompound nbt, CallbackInfo info) {
        if(nbt.contains("isCurrentlyInvisible")) {
            isCurrentlyInvisible = nbt.getBoolean("isCurrentlyInvisible");
        }
    }
}
