package com.github.telvarost.annoyancefix;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "AnnoyanceFix Config")
    public static ConfigFields config = new ConfigFields();

    public static class AxesConfig {

        @ConfigName("Effective On Workbench")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnWorkbench = true;

        @ConfigName("Effective On Noteblock")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnNoteblock = true;

        @ConfigName("Effective On Wood Door")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnWoodDoor = true;

        @ConfigName("Effective On Ladders")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnLadders = true;

        @ConfigName("Effective On Signs")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnSigns = true;

        @ConfigName("Effective On Wood Pressure Plate")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnWoodPressurePlate = true;

        @ConfigName("Effective On Jukebox")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnJukebox = true;

        @ConfigName("Effective On Wood Stairs")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnWoodStairs = true;

        @ConfigName("Effective On Fence")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnFence = true;

        @ConfigName("Effective On Pumpkin")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnPumpkin = true;

        @ConfigName("Effective On Jack o Lantern")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnJackOLantern = true;

        @ConfigName("Effective On Trapdoor")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enableAxesEffectiveOnTrapdoor = true;
    }

    public static class PickaxesConfig {

        @ConfigName("Effective On Dispenser")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnDispenser = true;

        @ConfigName("Effective On Normal Rails")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnNormalRails = true;

        @ConfigName("Effective On Detector Rails")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnDetectorRails = true;

        @ConfigName("Effective On Golden Rails")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnGoldenRails = true;

        @ConfigName("Effective On Furnace")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnFurnace = true;

        @ConfigName("Effective On Furnace Lit")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnFurnaceLit = true;

        @ConfigName("Effective On Cobblestone Stairs")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnCobblestoneStairs = true;

        @ConfigName("Effective On Stone Pressure Plate")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnStonePressurePlate = true;

        @ConfigName("Effective On Iron Door")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnIronDoor = true;

        @ConfigName("Effective On Redstone Ore")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnRedstoneOre = true;

        @ConfigName("Effective On Redstone Ore Lit")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnRedstoneOreLit = true;

        @ConfigName("Effective On Stone Button")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnStoneButton = true;

        @ConfigName("Effective On Bricks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnBricks = true;

        @ConfigName("Effective On Mob Spawner")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean enablePickaxesEffectiveOnMobSpawner = true;
    }

    public static class RecipesConfig {

        @ConfigName("Crafting: Repair Armor")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean recipesRepairArmorEnabled = true;

        @ConfigName("Crafting: Repair Tools")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean recipesRepairToolsEnabled = true;

        @ConfigName("Furnace: Add More Wood Items As Fuel")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
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
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean boatDropFixesEnabled = true;

        @ConfigName("Boat Speed Collision Behavior")
        @MultiplayerSynced
        @ValueOnVanillaServer(integerValue = 0)
        public static BoatCollisionEnum boatCollisionBehavior = BoatCollisionEnum.INVINCIBLE;

        @ConfigName("Bookshelf Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean bookshelfFixesEnabled = true;

        @ConfigName("Cobweb Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean cobwebFixesEnabled = true;

        @ConfigName("Fence Placement Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean fenceFixesEnabled = true;

        @ConfigName("Fence Shape Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean fenceShapeFixesEnabled = true;

        @ConfigName("Flint and Steel Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean flintAndSteelFixesEnabled = true;

        @ConfigName("Lava Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean lavaFixesEnabled = true;

        @ConfigName("Pick Block Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean pickBlockFixesEnabled = true;

        @ConfigName("Pig Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean pigFixesEnabled = true;

        @ConfigName("Stair Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean stairFixesEnabled = true;

        @ConfigName("Water Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean waterFixesEnabled = true;

        @ConfigName("Wood Slab Fixes Enabled (Experimental)")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public static Boolean woodenSlabFixesEnabled = false;
    }
}
