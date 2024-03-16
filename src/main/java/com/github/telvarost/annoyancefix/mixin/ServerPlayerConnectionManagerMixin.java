package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.server.PlayerHandler;
import net.minecraft.server.ServerPlayerConnectionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerConnectionManager.class)
public abstract class ServerPlayerConnectionManagerMixin {

    @Redirect(
            method = "updateDimension",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/PlayerHandler;savePlayer(Lnet/minecraft/entity/player/PlayerBase;)V"
            )
    )
    public void updateDimension(PlayerHandler instance, PlayerBase playerBase) {
        instance.savePlayer(playerBase);

        if (Config.config.boatLogoutLoginFixesEnabled) {
            /** - Remove vehicle on logout */
            if (null != playerBase.vehicle) {
                playerBase.level.removeEntity(playerBase.vehicle);
            }
        }
    }
}
