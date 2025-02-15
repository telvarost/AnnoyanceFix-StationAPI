package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.world.PlayerSaveHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerManager.class)
public abstract class ServerPlayerConnectionManagerMixin {

    @WrapOperation(
            method = "disconnect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/PlayerSaveHandler;savePlayerData(Lnet/minecraft/entity/player/PlayerEntity;)V"
            )
    )
    public void updateDimension(PlayerSaveHandler instance, PlayerEntity playerEntity, Operation<Void> original) {
        original.call(instance, playerEntity);

        if (Config.config.boatLogoutLoginFixesEnabled) {
            /** - Remove vehicle on logout */
            if (null != playerEntity.vehicle) {
                playerEntity.world.remove(playerEntity.vehicle);
            }
        }
    }
}
