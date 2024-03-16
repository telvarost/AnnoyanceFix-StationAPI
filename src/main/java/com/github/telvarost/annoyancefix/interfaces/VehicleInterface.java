package com.github.telvarost.annoyancefix.interfaces;

import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.util.Util;

public interface VehicleInterface {
    default String vehicle_getVehicleName() {
        return Util.assertImpl();
    }

    default void vehicle_setVehicleName(String vehicleName) {
        Util.assertImpl();
    }

    default CompoundTag vehicle_getVehicleTag() {
        return Util.assertImpl();
    }

    default void vehicle_setVehicleTag(CompoundTag vehicleTag) {
        Util.assertImpl();
    }
}