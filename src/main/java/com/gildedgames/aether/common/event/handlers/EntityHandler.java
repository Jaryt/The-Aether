package com.gildedgames.aether.common.event.handlers;

import com.gildedgames.aether.common.registry.AetherAdvancements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityHandler
{
    @SubscribeEvent
    public static void onMountEntity(EntityMountEvent event) {
        Entity rider = event.getEntityMounting();
        Entity mount = event.getEntityBeingMounted();
        if (event.getEntityBeingMounted() != null && rider instanceof ServerPlayerEntity) {
            AetherAdvancements.MOUNT_ENTITY.trigger((ServerPlayerEntity) rider, mount);
        }
    }
}
