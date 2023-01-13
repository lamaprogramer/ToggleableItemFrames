package net.iamaprogrammer.toggleableitemframes.event;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class UseEntityHandler implements UseEntityCallback {

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (entity instanceof ItemFrameEntity && player.getMainHandStack() == ItemStack.EMPTY && player.isSneaking() && hand.equals(Hand.MAIN_HAND) && !world.isClient()) {
            ((ItemFrameEntity) entity).setRotation(((ItemFrameEntity) entity).getRotation() - 1);

            player.sendMessage(Text.literal(String.valueOf(((ItemFrameEntity) entity).getRotation())));
            entity.setInvisible(!entity.isInvisible());

        }

        return ActionResult.PASS;
    }
}
