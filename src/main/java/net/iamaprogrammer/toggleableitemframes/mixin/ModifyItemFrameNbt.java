package net.iamaprogrammer.toggleableitemframes.mixin;


import net.iamaprogrammer.toggleableitemframes.util.IModifyItemFrameNbt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


// Mixin for Custom NBT
@Mixin(ItemFrameEntity.class)
public abstract class ModifyItemFrameNbt extends AbstractDecorationEntity implements IModifyItemFrameNbt {
    private boolean isCurrentlyInvisible;

    protected ModifyItemFrameNbt(EntityType<? extends AbstractDecorationEntity> entityType, World world) {
        super(entityType, world);
    }

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

//    @Inject(method = "writeCustomDataToNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putBoolean(Ljava/lang/String;Z)V"))
//    private void injected(CallbackInfo ci) {
//        if (this.world.getServer() == null) {
//            if (this.world.isClient()) {
//                this.setInvisible(true);
//            }
//        }
//    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void readCustomNbt(NbtCompound nbt, CallbackInfo info) {
        if(nbt.contains("isCurrentlyInvisible")) {
            isCurrentlyInvisible = nbt.getBoolean("isCurrentlyInvisible");
        }
    }
}
