package com.github.telvarost.annoyancefix;

public class ModHelper {
    public enum BlockTypeEnum {
        BLOCK_IS_NOT_A_SLAB,
        SLAB_BLOCK_IS_NOT_WOODEN,
        SLAB_BLOCK_IS_WOODEN
    }

    public static byte toByte(boolean value) {
        return (byte) (value ? 1 : 0);
    }

    public static boolean toBool(byte value) {
        return value > 0;
    }

    public static class ModHelperFields {

        /** @brief - Special data for remembering block type */
        public static BlockTypeEnum blockType = BlockTypeEnum.BLOCK_IS_NOT_A_SLAB;

        /** @brief - Special data for flint and steel fixes */
        public static Boolean isFireLit = false;
    }
}
