package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.world.PlayerSaveHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public abstract class ServerPlayerConnectionManagerMixin {

    @Redirect(
            method = "disconnect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/PlayerSaveHandler;savePlayerData(Lnet/minecraft/entity/player/PlayerEntity;)V"
            )
    )
    public void updateDimension(PlayerSaveHandler instance, PlayerEntity playerBase) {
        instance.savePlayerData(playerBase);

        if (Config.config.boatLogoutLoginFixesEnabled) {
            /** - Remove vehicle on logout */
            if (null != playerBase.vehicle) {
                playerBase.world.remove(playerBase.vehicle);
            }
        }
    }
}
