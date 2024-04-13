package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Level.class)
public class LevelMixin {

    @Shadow public boolean isServerSide;

    @Shadow public List entities;

    @Inject(
            method = "spawnEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;method_219(Lnet/minecraft/entity/EntityBase;)V"
            )
    )
    public void spawnEntity(EntityBase arg, CallbackInfoReturnable<Boolean> cir) {
        if (  (Config.config.boatLogoutLoginFixesEnabled)
           && (ModHelper.ModHelperFields.isVehicleSaved)
           && (!this.isServerSide)
        ) {
            /** - Find saved vehicle if on single player */
            for (int entityIndex = 0; entityIndex < this.entities.size(); entityIndex++) {
                EntityBase entityToCheck = (EntityBase) this.entities.get(entityIndex);

                if (  (entityToCheck.getClass().equals(ModHelper.ModHelperFields.savedVehicleClass))
                   && (1 > Math.abs(entityToCheck.x - ModHelper.ModHelperFields.savedVehicleX))
                   && (1 > Math.abs(entityToCheck.y - ModHelper.ModHelperFields.savedVehicleY))
                   && (1 > Math.abs(entityToCheck.z - ModHelper.ModHelperFields.savedVehicleZ))
                ) {
                    PlayerBase player = PlayerHelper.getPlayerFromGame();
                    if (null != player) {
                        player.startRiding(entityToCheck);
                    }
                    ModHelper.ModHelperFields.isVehicleSaved = false;
                }
            }
        }
    }
}
