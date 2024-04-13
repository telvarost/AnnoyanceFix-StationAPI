package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import com.github.telvarost.annoyancefix.interfaces.VehicleInterface;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerBase.class)
public abstract class PlayerBaseMixin extends Living implements VehicleInterface {

    @Unique
    private static String NULL_AS_STRING = "null";

    @Unique
    public String _vehicleName = NULL_AS_STRING;

    @Unique
    public CompoundTag _vehicleTag = new CompoundTag();

    public PlayerBaseMixin(Level arg) {
        super(arg);
    }

    @Override
    public String vehicle_getVehicleName() {
        return _vehicleName;
    }

    @Override
    public void vehicle_setVehicleName(String vehicleName) {
        _vehicleName = vehicleName;
    }

    @Override
    public CompoundTag vehicle_getVehicleTag() {
        return _vehicleTag;
    }

    @Override
    public void vehicle_setVehicleTag(CompoundTag vehicleTag) {
        _vehicleTag = vehicleTag;
    }

    @Redirect(
            method = "interactWith",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityBase;interact(Lnet/minecraft/entity/player/PlayerBase;)Z"
            )
    )
    public boolean interactWith(EntityBase instance, PlayerBase playerBase) {
        boolean canInteract = instance.interact(playerBase);

        if (Config.config.boatLogoutLoginFixesEnabled && canInteract) {
            /** - Set vehicle data */
            _vehicleName = (instance.passenger != null) ?  EntityRegistry.getStringId(instance) : NULL_AS_STRING;
            if (!_vehicleName.equals(NULL_AS_STRING)) {
                instance.toTag(_vehicleTag);
            }
        }

        return  canInteract;
    }



    @Inject(method = "writeCustomDataToTag", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        if (!Config.config.boatLogoutLoginFixesEnabled) {
            return;
        }

        /** - Save vehicle data */
        _vehicleName = (this.vehicle != null) ?  EntityRegistry.getStringId(this.vehicle) : NULL_AS_STRING;
        tag.put("VehicleName", _vehicleName);
        if (!_vehicleName.equals(NULL_AS_STRING)) {
            this.vehicle.toTag(_vehicleTag);
            tag.put("VehicleTag", _vehicleTag);
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if (!Config.config.boatLogoutLoginFixesEnabled) {
            return;
        }

        /** - Get vehicle data */
        _vehicleName = tag.getString("VehicleName");
        if (!_vehicleName.equals(NULL_AS_STRING)) {
            _vehicleTag = tag.getCompoundTag("VehicleTag");
        }

        /** - Find saved vehicle if on single player */
        if (level.isServerSide)   return;
        PlayerBase singlePlayer = PlayerHelper.getPlayerFromGame();
        if (null == singlePlayer) return;
        if (null == _vehicleTag)  return;
        if (_vehicleName.equals(NULL_AS_STRING)) return;
        EntityBase vehicle = EntityRegistry.create(_vehicleName, level);
        if (null != vehicle) {
            ModHelper.ModHelperFields.isVehicleSaved = true;
            EntityBase savedVehicle = null;

            try {
                vehicle.fromTag(_vehicleTag);
            } catch(Exception ex) {
                vehicle.setPositionAndAngles(x, y, z, yaw, pitch);
                System.out.println("Failed to read vehicle data");
            }

            ModHelper.ModHelperFields.savedVehicleClass = vehicle.getClass();
            ModHelper.ModHelperFields.savedVehicleX = vehicle.x;
            ModHelper.ModHelperFields.savedVehicleY = vehicle.y;
            ModHelper.ModHelperFields.savedVehicleZ = vehicle.z;

            /** - Search for vehicle */
            for (int entityIndex = 0; entityIndex < level.entities.size(); entityIndex++) {
                EntityBase entityToCheck = (EntityBase)level.entities.get(entityIndex);

                if (  (entityToCheck.getClass().equals(ModHelper.ModHelperFields.savedVehicleClass))
                   && (1 > Math.abs(entityToCheck.x - vehicle.x))
                   && (1 > Math.abs(entityToCheck.y - vehicle.y))
                   && (1 > Math.abs(entityToCheck.z - vehicle.z))
                ) {
                    savedVehicle = entityToCheck;
                }
            }

            if (null != savedVehicle) {
                startRiding(savedVehicle);
                ModHelper.ModHelperFields.isVehicleSaved = false;
            }
        }
    }
}
