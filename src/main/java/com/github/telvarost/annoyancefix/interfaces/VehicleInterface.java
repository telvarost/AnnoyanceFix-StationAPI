package com.github.telvarost.annoyancefix.interfaces;

import net.minecraft.util.io.CompoundTag;
import net.modificationstation.stationapi.api.util.Util;

public interface VehicleInterface {
    default boolean vehicle_isRiding() {
        return Util.assertImpl();
    }

    default void vehicle_setIsRiding(boolean creative) {
        Util.assertImpl();
    }

    default CompoundTag vehicle_getVehicle() {
        return Util.assertImpl();
    }

    default void vehicle_setVehicle(CompoundTag vehicle) {
        Util.assertImpl();
    }
}