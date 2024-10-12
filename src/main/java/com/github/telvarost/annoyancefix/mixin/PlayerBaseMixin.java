package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import com.github.telvarost.annoyancefix.interfaces.VehicleInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerBaseMixin extends LivingEntity implements VehicleInterface {

    @Unique
    private static String NULL_AS_STRING = "null";

    @Unique
    public String _vehicleName = NULL_AS_STRING;

    @Unique
    public NbtCompound _vehicleTag = new NbtCompound();

    public PlayerBaseMixin(World arg) {
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
    public NbtCompound vehicle_getVehicleTag() {
        return _vehicleTag;
    }

    @Override
    public void vehicle_setVehicleTag(NbtCompound vehicleTag) {
        _vehicleTag = vehicleTag;
    }

    @Redirect(
            method = "interact",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;interact(Lnet/minecraft/entity/player/PlayerEntity;)Z"
            )
    )
    public boolean interactWith(Entity instance, PlayerEntity playerBase) {
        boolean canInteract = instance.interact(playerBase);

        if (Config.config.boatLogoutLoginFixesEnabled && canInteract) {
            /** - Set vehicle data */
            _vehicleName = (instance.passenger != null) ?  EntityRegistry.getId(instance) : NULL_AS_STRING;
            if (!_vehicleName.equals(NULL_AS_STRING)) {
                instance.write(_vehicleTag);
            }
        }

        return  canInteract;
    }



    @Inject(method = "writeNbt", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(NbtCompound tag, CallbackInfo info) {
        if (!Config.config.boatLogoutLoginFixesEnabled) {
            return;
        }

        /** - Save vehicle data */
        _vehicleName = (this.vehicle != null) ?  EntityRegistry.getId(this.vehicle) : NULL_AS_STRING;
        tag.putString("VehicleName", _vehicleName);
        if (!_vehicleName.equals(NULL_AS_STRING)) {
            this.vehicle.write(_vehicleTag);
            tag.put("VehicleTag", _vehicleTag);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(NbtCompound tag, CallbackInfo info) {
        if (!Config.config.boatLogoutLoginFixesEnabled) {
            return;
        }

        /** - Get vehicle data */
        _vehicleName = tag.getString("VehicleName");
        if (!_vehicleName.equals(NULL_AS_STRING)) {
            _vehicleTag = tag.getCompound("VehicleTag");
        }

        /** - Find saved vehicle if on single player */
        if (world.isRemote)   return;
        PlayerEntity singlePlayer = PlayerHelper.getPlayerFromGame();
        if (null == singlePlayer) return;
        if (null == _vehicleTag)  return;
        if (_vehicleName.equals(NULL_AS_STRING)) return;
        Entity vehicle = EntityRegistry.create(_vehicleName, world);
        if (null != vehicle) {
            ModHelper.ModHelperFields.isVehicleSaved = true;
            Entity savedVehicle = null;

            try {
                vehicle.read(_vehicleTag);
            } catch(Exception ex) {
                vehicle.setPositionAndAnglesKeepPrevAngles(x, y, z, yaw, pitch);
                System.out.println("Failed to read vehicle data");
            }

            ModHelper.ModHelperFields.savedVehicleClass = vehicle.getClass();
            ModHelper.ModHelperFields.savedVehicleX = vehicle.x;
            ModHelper.ModHelperFields.savedVehicleY = vehicle.y;
            ModHelper.ModHelperFields.savedVehicleZ = vehicle.z;

            /** - Search for vehicle */
            for (int entityIndex = 0; entityIndex < world.entities.size(); entityIndex++) {
                Entity entityToCheck = (Entity)world.entities.get(entityIndex);

                if (  (entityToCheck.getClass().equals(ModHelper.ModHelperFields.savedVehicleClass))
                   && (1 > Math.abs(entityToCheck.x - vehicle.x))
                   && (1 > Math.abs(entityToCheck.y - vehicle.y))
                   && (1 > Math.abs(entityToCheck.z - vehicle.z))
                ) {
                    savedVehicle = entityToCheck;
                }
            }

            if (null != savedVehicle) {
                setVehicle(savedVehicle);
                ModHelper.ModHelperFields.isVehicleSaved = false;
            }
        }
    }
}
