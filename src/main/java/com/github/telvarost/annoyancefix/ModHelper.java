package com.github.telvarost.annoyancefix;

public class ModHelper {

    public static byte toByte(boolean value) {
        return (byte) (value ? 1 : 0);
    }

    public static boolean toBool(byte value) {
        return value > 0;
    }

    public static class ModHelperFields {
        /** @brief - Special data for disabling mixins */
        public static boolean loadMixinConfigs = true;

        /** @brief - Special data for picking the correct block */
        public static Integer pickBlockMeta = 0;

        /** @brief - Special data for flint and steel fixes */
        public static Boolean isFireLit = false;

        /** @brief - Special data for finding vehicle on login */
        public static Boolean isVehicleSaved = false;
        public static Class savedVehicleClass = null;
        public static Double savedVehicleX = 0.0D;
        public static Double savedVehicleY = 0.0D;
        public static Double savedVehicleZ = 0.0D;
    }
}
