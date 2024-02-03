package com.github.telvarost.annoyancefix;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigCategory;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;
import net.minecraft.block.BlockBase;

public class Config {

    @GConfig(value = "config", visibleName = "AnnoyanceFix Config")
    public static ConfigFields config = new ConfigFields();

    public static class AxesConfig {

        @ConfigName("Effective On Workbench")
        public static Boolean enableAxesEffectiveOnWorkbench = true;

        @ConfigName("Effective On Noteblock")
        public static Boolean enableAxesEffectiveOnNoteblock = true;

        @ConfigName("Effective On Wood Door")
        public static Boolean enableAxesEffectiveOnWoodDoor = true;

        @ConfigName("Effective On Ladders")
        public static Boolean enableAxesEffectiveOnLadders = true;

        @ConfigName("Effective On Signs")
        public static Boolean enableAxesEffectiveOnSigns = true;

        @ConfigName("Effective On Wood Pressure Plate")
        public static Boolean enableAxesEffectiveOnWoodPressurePlate = true;

        @ConfigName("Effective On Jukebox")
        public static Boolean enableAxesEffectiveOnJukebox = true;

        @ConfigName("Effective On Wood Stairs")
        public static Boolean enableAxesEffectiveOnWoodStairs = true;

        @ConfigName("Effective On Fence")
        public static Boolean enableAxesEffectiveOnFence = true;

        @ConfigName("Effective On Pumpkin")
        public static Boolean enableAxesEffectiveOnPumpkin = true;

        @ConfigName("Effective On Jack o Lantern")
        public static Boolean enableAxesEffectiveOnJackOLantern = true;

        @ConfigName("Effective On Trapdoor")
        public static Boolean enableAxesEffectiveOnTrapdoor = true;
    }

    public static class PickaxesConfig {

        @ConfigName("Effective On Dispenser")
        public static Boolean enablePickaxesEffectiveOnDispenser = true;

        @ConfigName("Effective On Normal Rails")
        public static Boolean enablePickaxesEffectiveOnNormalRails = true;

        @ConfigName("Effective On Detector Rails")
        public static Boolean enablePickaxesEffectiveOnDetectorRails = true;

        @ConfigName("Effective On Golden Rails")
        public static Boolean enablePickaxesEffectiveOnGoldenRails = true;

        @ConfigName("Effective On Furnace")
        public static Boolean enablePickaxesEffectiveOnFurnace = true;

        @ConfigName("Effective On Furnace Lit")
        public static Boolean enablePickaxesEffectiveOnFurnaceLit = true;

        @ConfigName("Effective On Cobblestone Stairs")
        public static Boolean enablePickaxesEffectiveOnCobblestoneStairs = true;

        @ConfigName("Effective On Stone Pressure Plate")
        public static Boolean enablePickaxesEffectiveOnStonePressurePlate = true;

        @ConfigName("Effective On Iron Door")
        public static Boolean enablePickaxesEffectiveOnIronDoor = true;

        @ConfigName("Effective On Redstone Ore")
        public static Boolean enablePickaxesEffectiveOnRedstoneOre = true;

        @ConfigName("Effective On Redstone Ore Lit")
        public static Boolean enablePickaxesEffectiveOnRedstoneOreLit = true;

        @ConfigName("Effective On Stone Button")
        public static Boolean enablePickaxesEffectiveOnStoneButton = true;

        @ConfigName("Effective On Bricks")
        public static Boolean enablePickaxesEffectiveOnBricks = true;

        @ConfigName("Effective On Mob Spawner")
        public static Boolean enablePickaxesEffectiveOnMobSpawner = true;
    }

    public static class RecipesConfig {

        @ConfigName("Crafting: Repair Armor")
        public static Boolean recipesRepairArmorEnabled = true;

        @ConfigName("Crafting: Repair Tools")
        public static Boolean recipesRepairToolsEnabled = true;

        @ConfigName("Furnace: Add More Wood Items As Fuel")
        public static Boolean recipesAdditionalWoodFuelsEnabled = true;

    }

    public static class ConfigFields {

        @ConfigCategory("Config: Axes Are Effective Against")
        @Comment("Options here require restart to take effect")
        public static final AxesConfig AXES_CONFIG = new AxesConfig();

        @ConfigCategory("Config: Pickaxes Are Effective Against")
        @Comment("Options here require restart to take effect")
        public static final PickaxesConfig PICKAXES_CONFIG = new PickaxesConfig();

        @ConfigCategory("Config: Recipes/Fuels For Crafting/Furnaces")
        @Comment("Options here require restart to take effect")
        public static final RecipesConfig RECIPES_CONFIG = new RecipesConfig();

        @ConfigName("Boat Drop Fixes Enabled")
        public static Boolean boatDropFixesEnabled = true;

        @ConfigName("Boat Speed Collision Behavior")
        @MaxLength(3)
        @Comment("0 = vanilla, 1 = drop boat, 2 = invincible")
        public static Integer boatCollisionBehavior = 2;

        @ConfigName("Bookshelf Fixes Enabled")
        public static Boolean bookshelfFixesEnabled = true;

        @ConfigName("Cobweb Fixes Enabled")
        public static Boolean cobwebFixesEnabled = true;

        @ConfigName("Fence Placement Fixes Enabled")
        public static Boolean fenceFixesEnabled = true;

        @ConfigName("Fence Shape Fixes Enabled")
        public static Boolean fenceShapeFixesEnabled = true;

        @ConfigName("Flint and Steel Fixes Enabled")
        public static Boolean flintAndSteelFixesEnabled = true;

        @ConfigName("Lava Fixes Enabled")
        public static Boolean lavaFixesEnabled = true;

        @ConfigName("Pick Block Fixes Enabled")
        public static Boolean pickBlockFixesEnabled = true;

        @ConfigName("Pig Fixes Enabled")
        public static Boolean pigFixesEnabled = true;

        @ConfigName("Stair Fixes Enabled")
        public static Boolean stairFixesEnabled = true;

        @ConfigName("Water Fixes Enabled")
        public static Boolean waterFixesEnabled = true;

        @ConfigName("Wood Slab Fixes Enabled (Experimental)")
        public static Boolean woodenSlabFixesEnabled = false;
    }
}
