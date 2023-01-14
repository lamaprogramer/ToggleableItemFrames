package net.iamaprogrammer.toggleableitemframes.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.iamaprogrammer.toggleableitemframes.util.IModifyItemFrameNbt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtFloat;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

import java.util.ArrayList;
import java.util.List;

public class ServerTickHandler implements ServerTickEvents.StartTick {
    ParticleEffect e = ParticleTypes.FLAME;
    @Override
    public void onStartTick(MinecraftServer server) {
        ServerWorld world;
        ArrayList<ItemFrameEntity> entities;

        IModifyItemFrameNbt invisibleFrame;
        ArrayList<ItemFrameEntity> invisibleFrames = new ArrayList<>();

        //ServerPlayerEntity p = server.getPlayerManager().getPlayer(players[0]);
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

            world = player.getWorld();
            entities = (ArrayList<ItemFrameEntity>) world.getEntitiesByClass(ItemFrameEntity.class, new Box(player.getX() - 10, player.getY() - 10, player.getZ() - 10, player.getX() + 10, player.getY() + 10, player.getZ() + 10), EntityPredicates.VALID_ENTITY);
            //entities.addAll(world.getEntitiesByType(EntityType.GLOW_ITEM_FRAME, new Box(player.getX() - 10, player.getY() - 10, player.getZ() - 10, player.getX() + 10, player.getY() + 10, player.getZ() + 10), EntityPredicates.VALID_ENTITY));

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
