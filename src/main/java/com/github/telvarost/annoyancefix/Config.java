package com.github.telvarost.annoyancefix;

import net.glasslauncher.mods.gcapi3.api.*;

public class Config {

    @ConfigRoot(value = "config", visibleName = "AnnoyanceFix")
    public static ConfigFields config = new ConfigFields();

    public static class AxesConfig {

        @ConfigEntry(
                name = "Effective On Workbench",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWorkbench = true;

        @ConfigEntry(
                name = "Effective On Noteblock",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnNoteblock = true;

        @ConfigEntry(
                name = "Effective On Wood Door",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWoodDoor = true;

        @ConfigEntry(
                name = "Effective On Ladders",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnLadders = true;

        @ConfigEntry(
                name = "Effective On Signs",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnSigns = true;

        @ConfigEntry(
                name = "Effective On Wood Pressure Plate",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWoodPressurePlate = true;

        @ConfigEntry(
                name = "Effective On Jukebox",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnJukebox = true;

        @ConfigEntry(
                name = "Effective On Wood Stairs",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWoodStairs = true;

        @ConfigEntry(
                name = "Effective On Fence",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnFence = true;

        @ConfigEntry(
                name = "Effective On Pumpkin",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnPumpkin = true;

        @ConfigEntry(
                name = "Effective On Jack o Lantern",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnJackOLantern = true;

        @ConfigEntry(
                name = "Effective On Trapdoor",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnTrapdoor = true;
    }

    public static class PickaxesConfig {

        @ConfigEntry(
                name = "Effective On Dispenser",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnDispenser = true;

        @ConfigEntry(
                name = "Effective On Normal Rails",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnNormalRails = true;

        @ConfigEntry(
                name = "Effective On Detector Rails",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnDetectorRails = true;

        @ConfigEntry(
                name = "Effective On Golden Rails",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnGoldenRails = true;

        @ConfigEntry(
                name = "Effective On Furnace",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnFurnace = true;

        @ConfigEntry(
                name = "Effective On Furnace Lit",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnFurnaceLit = true;

        @ConfigEntry(
                name = "Effective On Cobblestone Stairs",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnCobblestoneStairs = true;

        @ConfigEntry(
                name = "Effective On Stone Pressure Plate",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnStonePressurePlate = true;

        @ConfigEntry(
                name = "Effective On Iron Door",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnIronDoor = true;

        @ConfigEntry(
                name = "Effective On Redstone Ore",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnRedstoneOre = true;

        @ConfigEntry(
                name = "Effective On Redstone Ore Lit",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnRedstoneOreLit = true;

        @ConfigEntry(
                name = "Effective On Stone Button",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnStoneButton = true;

        @ConfigEntry(
                name = "Effective On Bricks",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnBricks = true;

        @ConfigEntry(
                name = "Effective On Mob Spawner",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnMobSpawner = true;
    }

    public static class ShovelsConfig {

        @ConfigEntry(
                name = "Effective On Soul Sand",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableShovelsEffectiveOnSoulSand = true;
    }

    public static class RecipesConfig {

        @ConfigEntry(
                name = "Crafting: Repair Armor",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean recipesRepairArmorEnabled = true;

        @ConfigEntry(
                name = "Crafting: Repair Tools",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean recipesRepairToolsEnabled = true;

        @ConfigEntry(
                name = "Furnace: Add More Wood Items As Fuel",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean recipesAdditionalWoodFuelsEnabled = true;

    }

    public static class ConfigFields {

        @ConfigCategory(
                name = "Config: Effectivity List Additions For Axes",
                description = "Options here require restart to take effect"
        )
        public final AxesConfig AXES_CONFIG = new AxesConfig();

        @ConfigCategory(
                name = "Config: Effectivity List Additions For Pickaxes",
                description = "Options here require restart to take effect"
        )
        public final PickaxesConfig PICKAXES_CONFIG = new PickaxesConfig();

        @ConfigCategory(
                name = "Config: Effectivity List Additions For Shovels",
                description = "Options here require restart to take effect"
        )
        public final ShovelsConfig SHOVELS_CONFIG = new ShovelsConfig();

        @ConfigCategory(
                name = "Config: Recipes/Fuels For Crafting/Furnaces",
                description = "Options here require restart to take effect"
        )
        public final RecipesConfig RECIPES_CONFIG = new RecipesConfig();

        @ConfigEntry(
                name = "Boat Collision Behavior",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(integerValue = 0)
        public BoatCollisionEnum boatCollisionBehavior = BoatCollisionEnum.INVINCIBLE;

        @ConfigEntry(
                name = "Boat Drop Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean boatDropFixesEnabled = true;

        // Should rename to "Vehicle Logout/Login Fixes Enabled"
        @ConfigEntry(
                name = "Boat Logout/Login Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean boatLogoutLoginFixesEnabled = true;

        @ConfigEntry(
                name = "Bookshelf Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean bookshelfFixesEnabled = true;

        @ConfigEntry(
                name = "Cobweb Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean cobwebFixesEnabled = true;

        @ConfigEntry(
                name = "Fence Placement Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean fenceFixesEnabled = true;

        @ConfigEntry(
                name = "Fence Shape Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean fenceShapeFixesEnabled = true;

        @ConfigEntry(
                name = "Flint and Steel Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean flintAndSteelFixesEnabled = true;

        @ConfigEntry(
                name = "Lava Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean lavaFixesEnabled = true;

        @ConfigEntry(
                name = "Pick Block Fixes Enabled",
                description = "Only searches hotbar on multiplayer",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean pickBlockFixesEnabled = true;

        @ConfigEntry(
                name = "Pick Block Behavior",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(integerValue = 0)
        public PickBlockEnum pickBlockBehavior = PickBlockEnum.CHECK_META;

        @ConfigEntry(
                name = "Pig Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean pigFixesEnabled = true;

        @ConfigEntry(
                name = "Plant Placement Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean plantPlacementFixesEnabled = true;

        @ConfigEntry(
                name = "Slab Placement Fixes Enabled",
                description = "Restart required for changes to take effect",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean slabPlacementFixesEnabled = true;

        @ConfigEntry(
                name = "Stair Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean stairFixesEnabled = true;

        @ConfigEntry(
                name = "Water Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean waterFixesEnabled = true;

        @ConfigEntry(
                name = "Wooden Slab Fixes Enabled",
                multiplayerSynced = true
        )
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean woodenSlabFixesEnabled = true;
    }
}
