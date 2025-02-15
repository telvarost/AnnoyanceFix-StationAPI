package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.SERVER)
@Mixin(ServerLoginNetworkHandler.class)
public abstract class ServerPacketHandlerMixin {

    @WrapOperation(
            method = "accept",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ServerPlayerEntity;initScreenHandler()V"
            )
    )
    public void annoyanceFix_completeLoadVehicle(ServerPlayerEntity instance, Operation<Void> original) {
        original.call(instance);

        if (Config.config.boatLogoutLoginFixesEnabled) {
            /** - Spawn saved vehicle if on multiplayer */
            String vehicleName = instance.vehicle_getVehicleName();
            if (!vehicleName.equals("null")) {
                NbtCompound vehicleTag = instance.vehicle_getVehicleTag();
                if (vehicleTag != null) {
                    Entity vehicle = EntityRegistry.create(vehicleName, instance.world);
                    if (null != vehicle) {
                        try {
                            vehicle.read(vehicleTag);
                        } catch(Exception ex) {
                            vehicle.setPositionAndAnglesKeepPrevAngles(instance.x, instance.y, instance.z, instance.yaw, instance.pitch);
                            System.out.println("Failed to read vehicle data");
                        }
                        instance.world.spawnEntity(vehicle);
                        instance.setVehicle(vehicle);
                    }
                }
            }
        }
    }

}
