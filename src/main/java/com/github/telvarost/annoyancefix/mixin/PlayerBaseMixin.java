package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.ModHelper;
import com.github.telvarost.annoyancefix.interfaces.VehicleInterface;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerBase.class)
public abstract class PlayerBaseMixin extends Living implements VehicleInterface {

    @Shadow public abstract void readCustomDataFromTag(CompoundTag arg);

    @Unique
    public boolean _playerInVehicle = false;

    @Unique
    public CompoundTag _vehicleTag = new CompoundTag();

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
    public CompoundTag vehicle_getVehicle() {
        return _vehicleTag;
    }

    @Override
    public void vehicle_setVehicle(CompoundTag vehicleTag) {
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

        if (canInteract) {
            _playerInVehicle = (instance.passenger != null);
            this.vehicle_setIsRiding(_playerInVehicle);

            if (instance.passenger != null) {
                //this.vehicle_setVehicleName(instance.getClass().toString());
                ModHelper.ModHelperFields.vehicleName = instance.getClass().toString();
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
        vehicle_setIsRiding(_playerInVehicle);


        if (_playerInVehicle) {
            this.vehicle.toTag(_vehicleTag);
            tag.put("Vehicle", _vehicleTag);
            vehicle_setVehicle(_vehicleTag);
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if (!true) {
            return;
        }

        _playerInVehicle = tag.getBoolean("PlayerInVehicle");
        this.vehicle_setIsRiding(_playerInVehicle);


        if (_playerInVehicle) {
            _vehicleTag = tag.getCompoundTag("Vehicle");
            vehicle_setVehicle(_vehicleTag);
        }
    }

    @Inject(method = "initDataTracker", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Living;initDataTracker()V",
            shift = At.Shift.AFTER
    ))
    private void creative_trackData(CallbackInfo info) {
        this.dataTracker.startTracking(ModHelper.IS_RIDING_VEHICLE_ID, (byte) 0);
        this.dataTracker.startTracking(ModHelper.VEHICLE_INFO_ID, new CompoundTag());
    }
}
