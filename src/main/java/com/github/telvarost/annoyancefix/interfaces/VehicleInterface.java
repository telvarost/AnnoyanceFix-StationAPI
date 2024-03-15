package com.github.telvarost.annoyancefix.interfaces;

import net.modificationstation.stationapi.api.util.Util;

public interface VehicleInterface {
    default boolean vehicle_isRiding() {
        return Util.assertImpl();
    }

    default void vehicle_setIsRiding(boolean creative) {
        Util.assertImpl();
    }

    default String vehicle_getVehicleName() {
        return Util.assertImpl();
    }

    default void vehicle_setVehicleName(String vehicleName) {
        Util.assertImpl();
    }
}