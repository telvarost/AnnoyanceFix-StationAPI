package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.server.network.ServerPacketHandler;
import net.minecraft.util.io.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.SERVER)
@Mixin(ServerPacketHandler.class)
public abstract class ServerPacketHandlerMixin {

    @Redirect(
            method = "complete",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ServerPlayer;method_317()V"
            )
    )
    public void annoyanceFix_completeLoadVehicle(ServerPlayer instance) {
        if (!Config.config.boatLogoutLoginFixesEnabled) {
            return;
        }

        instance.method_317();

        /** - Spawn saved vehicle if on multiplayer */
        String vehicleName = instance.vehicle_getVehicleName();
        if (!vehicleName.equals("null")) {
            CompoundTag vehicleTag = instance.vehicle_getVehicleTag();
            if (vehicleTag != null) {
                EntityBase vehicle = EntityRegistry.create(vehicleName, instance.level);
                if (null != vehicle) {
                    try {
                        vehicle.fromTag(vehicleTag);
                    } catch(Exception ex) {
                        vehicle.setPositionAndAngles(instance.x, instance.y, instance.z, instance.yaw, instance.pitch);
                        System.out.println("Failed to read vehicle data");
                    }
                    instance.level.spawnEntity(vehicle);
                    instance.startRiding(vehicle);
                }
            }
        }
    }

}
