package net.iamaprogrammer.toggleableitemframes.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.iamaprogrammer.toggleableitemframes.util.IModifyItemFrameNbt;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

import java.util.ArrayList;

public class ServerTickHandler implements ServerTickEvents.StartTick {
    ServerWorld world;
    ArrayList<ItemFrameEntity> entities;
    IModifyItemFrameNbt invisibleFrame;
    ArrayList<ItemFrameEntity> invisibleFrames = new ArrayList<>();


    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            world = (ServerWorld) player.getWorld();
            entities = (ArrayList<ItemFrameEntity>) world.getEntitiesByClass(ItemFrameEntity.class, new Box(player.getX() - 10, player.getY() - 10, player.getZ() - 10, player.getX() + 10, player.getY() + 10, player.getZ() + 10), EntityPredicates.VALID_ENTITY);
            for (ItemFrameEntity frame : entities) {
                invisibleFrame = (IModifyItemFrameNbt) frame;
                if (invisibleFrame.getCurrentlyInvisible()) {
                    if ((player.isHolding(Items.ITEM_FRAME) || player.isHolding(Items.GLOW_ITEM_FRAME)) && Math.abs(player.getX() - frame.getX()) <= 9
                            && Math.abs(player.getY() - frame.getY()) <= 9
                            && Math.abs(player.getZ() - frame.getZ()) <= 9) {
                        frame.setInvisible(false);
                        invisibleFrames.add(frame);
                    } else {
                        frame.setInvisible(true);
                    }
                }
            }
            entities.clear();
            invisibleFrames.clear();
        }
    }
}
