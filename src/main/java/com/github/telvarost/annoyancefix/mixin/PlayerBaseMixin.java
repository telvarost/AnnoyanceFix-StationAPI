package com.github.telvarost.annoyancefix.mixin;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerContainer;
import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerBase.class)
public class PlayerBaseMixin {

    @Unique
    private String _vehicleName = null;
    @Unique
    private boolean _playerInVehicle = false;
    @Unique
    private double _vehicleX = 0;
    @Unique
    private double _vehicleY = 70;
    @Unique
    private double _vehicleZ = 0;

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

            if (instance.passenger != null) {
                _vehicleName = instance.getClass().toString();
                _vehicleX = Math.floor(instance.x);
                _vehicleY = Math.floor(instance.y);
                _vehicleZ = Math.floor(instance.z);
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
            tag.put("VehicleName", _vehicleName);
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

        if (_playerInVehicle) {
            _vehicleName = tag.getString("VehicleName");
            _vehicleX = tag.getDouble("VehicleX");
            _vehicleY = tag.getDouble("VehicleY");
            _vehicleZ = tag.getDouble("VehicleZ");
        }
    }
}
