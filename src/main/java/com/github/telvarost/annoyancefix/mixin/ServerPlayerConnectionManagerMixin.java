package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerHandler;
import net.minecraft.server.ServerPlayerConnectionManager;
import net.minecraft.server.ServerPlayerView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayerConnectionManager.class)
public abstract class ServerPlayerConnectionManagerMixin {


    @Shadow private PlayerHandler field_590;

    @Shadow private MinecraftServer server;

    @Shadow public List players;

    @Shadow protected abstract ServerPlayerView getPlayerView(int i);

    @Inject(
            method = "updateDimension",
            at = @At("HEAD"),
            cancellable = true
    )
    public void updateDimension(ServerPlayer arg, CallbackInfo ci) {
        if (!Config.config.boatLogoutLoginFixesEnabled) {
            return;
        }

        if (null != arg.vehicle) {
            arg.level.removeEntity(arg.vehicle);
        } else {
            arg.vehicle_setVehicleName("null");
        }
        this.field_590.savePlayer(arg);
        this.server.getLevel(arg.dimensionId).removeEntity(arg);
        this.players.remove(arg);
        this.getPlayerView(arg.dimensionId).addPlayer(arg);
        ci.cancel();
    }
}
