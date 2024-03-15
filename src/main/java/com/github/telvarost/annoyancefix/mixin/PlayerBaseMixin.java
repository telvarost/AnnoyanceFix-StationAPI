package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.ModHelper;
import com.github.telvarost.annoyancefix.interfaces.VehicleInterface;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerContainer;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.util.Util;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerBase.class)
public class PlayerBaseMixin extends Living implements VehicleInterface {

    @Unique
    public String _vehicleName = null;
    @Unique
    public boolean _playerInVehicle = false;
    @Unique
    private double _vehicleX = 0;
    @Unique
    private double _vehicleY = 70;
    @Unique
    private double _vehicleZ = 0;

    public PlayerBaseMixin(Level arg) {
        super(arg);
    }


    @Override
    public boolean vehicle_isRiding() {
        return ModHelper.toBool(dataTracker.getByte(ModHelper.IS_RIDING_VEHICLE_ID));
    }

    @Override
    public void vehicle_setIsRiding(boolean isRiding) {
        this.dataTracker.setInt(ModHelper.IS_RIDING_VEHICLE_ID, ModHelper.toByte(isRiding));
    }

    @Override
    public String vehicle_getVehicleName() {
        return dataTracker.getString(ModHelper.VEHICLE_NAME_ID);
    }

    @Override
    public void vehicle_setVehicleName(String vehicleName) {
        this.dataTracker.setInt(ModHelper.VEHICLE_NAME_ID, vehicleName);
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

        if (canInteract) {
            _playerInVehicle = (instance.passenger != null);
            this.vehicle_setIsRiding(_playerInVehicle);

            if (instance.passenger != null) {
                this.vehicle_setVehicleName(instance.getClass().toString());
                ModHelper.ModHelperFields.vehicleName = instance.getClass().toString();
                _vehicleX = Math.floor(instance.x);
                _vehicleY = Math.floor(instance.y);
                _vehicleZ = Math.floor(instance.z);
            } else {
                ModHelper.ModHelperFields.vehicleName = "";
            }
        }

        return  canInteract;
    }



    @Inject(method = "writeCustomDataToTag", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        if (!true) {
            return;
        }

        tag.put("PlayerInVehicle", _playerInVehicle);

        if (_playerInVehicle) {
            tag.put("VehicleName", ModHelper.ModHelperFields.vehicleName);
            tag.put("VehicleX", _vehicleX);
            tag.put("VehicleY", _vehicleY);
            tag.put("VehicleZ", _vehicleZ);
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if (!true) {
            return;
        }

        _playerInVehicle = tag.getBoolean("PlayerInVehicle");
        vehicle_isRiding(_playerInVehicle);

        if (_playerInVehicle) {
            //this.vehicle_setVehicleName(instance.getClass().toString());
            ModHelper.ModHelperFields.vehicleName = tag.getString("VehicleName");
            _vehicleX = tag.getDouble("VehicleX");
            _vehicleY = tag.getDouble("VehicleY");
            _vehicleZ = tag.getDouble("VehicleZ");
        }
    }
}
