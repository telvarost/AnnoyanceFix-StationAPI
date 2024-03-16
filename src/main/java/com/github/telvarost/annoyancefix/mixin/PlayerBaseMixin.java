package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.ModHelper;
import com.github.telvarost.annoyancefix.interfaces.VehicleInterface;
import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
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

//    @Unique
//    public boolean _playerInVehicle = false;

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
        return _vehicleName; //ModHelper.toBool(dataTracker.getByte(ModHelper.IS_RIDING_VEHICLE_ID));
    }

    @Override
    public void vehicle_setVehicleName(String vehicleName) {
        _vehicleName = vehicleName;//this.dataTracker.setInt(ModHelper.IS_RIDING_VEHICLE_ID, ModHelper.toByte(isRiding));
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

        if (canInteract) {
            _vehicleName = (instance.passenger != null) ?  EntityRegistry.getStringId(instance) : NULL_AS_STRING;
            //this.vehicle_setIsRiding(_playerInVehicle);

            if (!_vehicleName.equals(NULL_AS_STRING)) {
                //_vehicleName = EntityRegistry.getStringId(instance);
                instance.toTag(_vehicleTag);
                //vehicle_setVehicle(_vehicleTag);
            }
//            else {
//                _vehicleTag = null;
//                ModHelper.ModHelperFields.vehicleName = "";
//            }
        }

        return  canInteract;
    }



    @Inject(method = "writeCustomDataToTag", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        if (!true) {
            return;
        }

        //tag.put("PlayerInVehicle", _playerInVehicle);
        //vehicle_setIsRiding(_playerInVehicle);

        _vehicleName = (this.vehicle != null) ?  EntityRegistry.getStringId(this.vehicle) : NULL_AS_STRING;
        tag.put("VehicleName", _vehicleName);

        if (!_vehicleName.equals(NULL_AS_STRING)) {
            //if (null != this.vehicle) {
                //this.vehicle.toTag(_vehicleTag);
                tag.put("VehicleTag", _vehicleTag);
                //vehicle_setVehicle(_vehicleTag);
            //}
        }
    }

    @Inject(method = "readCustomDataFromTag", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if (!true) {
            return;
        }

        //_playerInVehicle = tag.getBoolean("PlayerInVehicle");
        //this.vehicle_setIsRiding(_playerInVehicle);

        _vehicleName = tag.getString("VehicleName");

        if (!_vehicleName.equals(NULL_AS_STRING)) {
            _vehicleTag = tag.getCompoundTag("VehicleTag");
            //vehicle_setVehicle(_vehicleTag);
        }


        if (level.isServerSide) return; // We are client connected to server, server do all the job
        PlayerBase singlePlayer = PlayerHelper.getPlayerFromGame();
        if (null == singlePlayer) return;
        //if ()
        //_vehicleTag = vehicle_getVehicle();
        if (_vehicleName.equals(NULL_AS_STRING)) return; // Not riding anything
        if (_vehicleTag == null) return; // Not riding anything
        //String vehicleName = _vehicleTag.getString("Vehicle"); // Or in any other way that you store that data
        EntityBase vehicle = EntityRegistry.create(_vehicleName, level);
        if (null != vehicle) {

            try {
                vehicle.fromTag(_vehicleTag);
            } catch(Exception ex) {
                vehicle.setPositionAndAngles(x, y, z, yaw, pitch);
                System.out.println("Failed to read vehicle data");
            }

            /** - Remove old entity if there is one */
            for (int entityIndex = 0; entityIndex < level.entities.size(); entityIndex++) {
                EntityBase entityToCheck = (EntityBase)level.entities.get(entityIndex);

                if (  (entityToCheck.getClass().equals(vehicle.getClass()))
                   && (entityToCheck.x == vehicle.x)
                   && (entityToCheck.y == vehicle.y)
                   && (entityToCheck.z == vehicle.z)
                ) {
                    //System.out.println("Found: " + entityToCheck.getClass());
                    level.removeEntity(entityToCheck);
                }
            }

            level.spawnEntity(vehicle);
            startRiding(vehicle);
        }
    }

    @Inject(method = "initDataTracker", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Living;initDataTracker()V",
            shift = At.Shift.AFTER
    ))
    private void creative_trackData(CallbackInfo info) {
        //this.dataTracker.startTracking(ModHelper.IS_RIDING_VEHICLE_ID, (byte) 0);
        //this.dataTracker.startTracking(ModHelper.VEHICLE_INFO_ID, new CompoundTag());
    }
}
