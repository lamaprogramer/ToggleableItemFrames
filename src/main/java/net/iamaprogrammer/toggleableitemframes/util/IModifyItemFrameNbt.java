package net.iamaprogrammer.toggleableitemframes.util;

import net.minecraft.nbt.NbtCompound;

public interface IModifyItemFrameNbt {
    boolean getCurrentlyInvisible();
    void setCurrentlyInvisible(boolean bool);
}
