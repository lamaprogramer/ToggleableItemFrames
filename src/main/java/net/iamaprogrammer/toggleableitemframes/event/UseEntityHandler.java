package net.iamaprogrammer.toggleableitemframes.event;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor;
import net.fabricmc.loader.language.LanguageAdapter;
import net.iamaprogrammer.toggleableitemframes.util.IModifyItemFrameNbt;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class UseEntityHandler implements UseEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        if (entity instanceof ItemFrameEntity && hitResult != null && !player.isSpectator() && !world.isClient() && world.getServer() != null && player.getMainHandStack().isEmpty() && player.isSneaking() && hand.equals(Hand.MAIN_HAND)) {
            if (!world.getServer().isSingleplayer()) {
                IModifyItemFrameNbt frame = (IModifyItemFrameNbt) entity;
                frame.setCurrentlyInvisible(!frame.getCurrentlyInvisible());

                ((ItemFrameEntity) entity).setRotation(((ItemFrameEntity) entity).getRotation() - 1);
                if (((ItemFrameEntity) entity).getHeldItemStack() == ItemStack.EMPTY) {
                    entity.playSound(((ItemFrameEntity) entity).getRotateItemSound(), 1.0f, 1.0f);
                }
                entity.setInvisible(!entity.isInvisible());
                //player.sendMessage(Text.literal("server"));

            } else {
                IModifyItemFrameNbt frame = (IModifyItemFrameNbt) entity;
                frame.setCurrentlyInvisible(!frame.getCurrentlyInvisible());

                ((ItemFrameEntity) entity).setRotation(((ItemFrameEntity) entity).getRotation() - 1);
                if (((ItemFrameEntity) entity).getHeldItemStack() == ItemStack.EMPTY) {
                    entity.playSound(((ItemFrameEntity) entity).getRotateItemSound(), 1.0f, 1.0f);
                }
                entity.setInvisible(!entity.isInvisible());
                //player.sendMessage(Text.literal("server"));
            }
        }
        return ActionResult.PASS;
    }
}
