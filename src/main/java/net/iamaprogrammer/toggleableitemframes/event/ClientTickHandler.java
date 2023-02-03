package net.iamaprogrammer.toggleableitemframes.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.iamaprogrammer.toggleableitemframes.util.IModifyItemFrameNbt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;

import java.util.ArrayList;

public class ClientTickHandler implements ClientTickEvents.StartTick {

    ClientWorld world;
    ClientPlayerEntity player;
    ArrayList<ItemFrameEntity> entities;
    IModifyItemFrameNbt invisibleFrame;
    ArrayList<ItemFrameEntity> invisibleFrames = new ArrayList<>();
    @Override
    public void onStartTick(MinecraftClient client) {
        world = client.world;
        player = client.player;

        if(player != null && world != null) {
            entities = (ArrayList<ItemFrameEntity>) world.getEntitiesByClass(ItemFrameEntity.class, new Box(player.getX() - 10, player.getY() - 10, player.getZ() - 10, player.getX() + 10, player.getY() + 10, player.getZ() + 10), EntityPredicates.VALID_ENTITY);
            for (ItemFrameEntity frame : entities) {
                invisibleFrame = (IModifyItemFrameNbt) frame;
                if (invisibleFrame.getCurrentlyInvisible() || !MinecraftClient.getInstance().isInSingleplayer()) {
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
        }
    }
}
