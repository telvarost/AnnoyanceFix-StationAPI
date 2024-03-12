package com.github.telvarost.annoyancefix;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {

    @GConfig(value = "config", visibleName = "AnnoyanceFix")
    public static ConfigFields config = new ConfigFields();

    public static class AxesConfig {

        @ConfigName("Effective On Workbench")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWorkbench = true;

        @ConfigName("Effective On Noteblock")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnNoteblock = true;

        @ConfigName("Effective On Wood Door")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWoodDoor = true;

        @ConfigName("Effective On Ladders")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnLadders = true;

        @ConfigName("Effective On Signs")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnSigns = true;

        @ConfigName("Effective On Wood Pressure Plate")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWoodPressurePlate = true;

        @ConfigName("Effective On Jukebox")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnJukebox = true;

        @ConfigName("Effective On Wood Stairs")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnWoodStairs = true;

        @ConfigName("Effective On Fence")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnFence = true;

        @ConfigName("Effective On Pumpkin")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnPumpkin = true;

        @ConfigName("Effective On Jack o Lantern")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnJackOLantern = true;

        @ConfigName("Effective On Trapdoor")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enableAxesEffectiveOnTrapdoor = true;
    }

    public static class PickaxesConfig {

        @ConfigName("Effective On Dispenser")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnDispenser = true;

        @ConfigName("Effective On Normal Rails")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnNormalRails = true;

        @ConfigName("Effective On Detector Rails")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnDetectorRails = true;

        @ConfigName("Effective On Golden Rails")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnGoldenRails = true;

        @ConfigName("Effective On Furnace")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnFurnace = true;

        @ConfigName("Effective On Furnace Lit")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnFurnaceLit = true;

        @ConfigName("Effective On Cobblestone Stairs")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnCobblestoneStairs = true;

        @ConfigName("Effective On Stone Pressure Plate")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnStonePressurePlate = true;

        @ConfigName("Effective On Iron Door")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnIronDoor = true;

        @ConfigName("Effective On Redstone Ore")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnRedstoneOre = true;

        @ConfigName("Effective On Redstone Ore Lit")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnRedstoneOreLit = true;

        @ConfigName("Effective On Stone Button")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnStoneButton = true;

        @ConfigName("Effective On Bricks")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnBricks = true;

        @ConfigName("Effective On Mob Spawner")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean enablePickaxesEffectiveOnMobSpawner = true;
    }

    public static class RecipesConfig {

        @ConfigName("Crafting: Repair Armor")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean recipesRepairArmorEnabled = true;

        @ConfigName("Crafting: Repair Tools")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean recipesRepairToolsEnabled = true;

        @ConfigName("Furnace: Add More Wood Items As Fuel")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean recipesAdditionalWoodFuelsEnabled = true;

    }

    public static class ConfigFields {

        @ConfigCategory("Config: Axes Are Effective Against")
        @Comment("Options here require restart to take effect")
        public final AxesConfig AXES_CONFIG = new AxesConfig();

        @ConfigCategory("Config: Pickaxes Are Effective Against")
        @Comment("Options here require restart to take effect")
        public final PickaxesConfig PICKAXES_CONFIG = new PickaxesConfig();

        @ConfigCategory("Config: Recipes/Fuels For Crafting/Furnaces")
        @Comment("Options here require restart to take effect")
        public final RecipesConfig RECIPES_CONFIG = new RecipesConfig();

        @ConfigName("Boat Drop Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean boatDropFixesEnabled = true;

        @ConfigName("Boat Speed Collision Behavior")
        @MultiplayerSynced
        @ValueOnVanillaServer(integerValue = 0)
        public BoatCollisionEnum boatCollisionBehavior = BoatCollisionEnum.INVINCIBLE;

        @ConfigName("Bookshelf Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean bookshelfFixesEnabled = true;

        @ConfigName("Cobweb Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean cobwebFixesEnabled = true;

        @ConfigName("Fence Placement Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean fenceFixesEnabled = true;

        @ConfigName("Fence Shape Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean fenceShapeFixesEnabled = true;

        @ConfigName("Flint and Steel Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean flintAndSteelFixesEnabled = true;

        @ConfigName("Lava Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean lavaFixesEnabled = true;

        @ConfigName("Pick Block Fixes Enabled")
        @Comment("Only searches hotbar on multiplayer")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean pickBlockFixesEnabled = true;

        @ConfigName("Pig Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean pigFixesEnabled = true;

        @ConfigName("Stair Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean stairFixesEnabled = true;

        @ConfigName("Water Fixes Enabled")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean waterFixesEnabled = true;

        @ConfigName("Wood Slab Fixes Enabled (Experimental)")
        @Comment("Does not work in multiplayer")
        @MultiplayerSynced
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        public Boolean woodenSlabFixesEnabled = false;
    }
}
