package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(World.class)
public class LevelMixin {

    @Shadow public boolean isRemote;

    @Shadow public List entities;

    @Inject(
            method = "spawnEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;notifyEntityAdded(Lnet/minecraft/entity/Entity;)V"
            )
    )
    public void spawnEntity(Entity arg, CallbackInfoReturnable<Boolean> cir) {
        if (  (Config.config.boatLogoutLoginFixesEnabled)
           && (ModHelper.ModHelperFields.isVehicleSaved)
           && (!this.isRemote)
        ) {
            /** - Find saved vehicle if on single player */
            for (int entityIndex = 0; entityIndex < this.entities.size(); entityIndex++) {
                Entity entityToCheck = (Entity) this.entities.get(entityIndex);

                if (  (entityToCheck.getClass().equals(ModHelper.ModHelperFields.savedVehicleClass))
                   && (1 > Math.abs(entityToCheck.x - ModHelper.ModHelperFields.savedVehicleX))
                   && (1 > Math.abs(entityToCheck.y - ModHelper.ModHelperFields.savedVehicleY))
                   && (1 > Math.abs(entityToCheck.z - ModHelper.ModHelperFields.savedVehicleZ))
                ) {
                    PlayerEntity player = PlayerHelper.getPlayerFromGame();
                    if (null != player) {
                        player.setVehicle(entityToCheck);
                    }
                    ModHelper.ModHelperFields.isVehicleSaved = false;
                }
            }
        }
    }
}
