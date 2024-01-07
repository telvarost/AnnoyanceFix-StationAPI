package com.github.telvarost.annoyancefix.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            /** - 60 second fuel duration */
            FuelRegistry.addFuelItem(ItemBase.boat            , 1200);

            /** - 15 second fuel duration */
            FuelRegistry.addFuelItem(ItemBase.bow             , 300);
            FuelRegistry.addFuelItem(ItemBase.fishingRod      , 300);
            FuelRegistry.addFuelItem(BlockBase.LADDER.asItem(), 300);

            /** - 10 second fuel duration */
            FuelRegistry.addFuelItem(ItemBase.woodAxe         , 200);
            FuelRegistry.addFuelItem(ItemBase.woodHoe         , 200);
            FuelRegistry.addFuelItem(ItemBase.woodPickaxe     , 200);
            FuelRegistry.addFuelItem(ItemBase.woodShovel      , 200);
            FuelRegistry.addFuelItem(ItemBase.woodSword       , 200);
            FuelRegistry.addFuelItem(ItemBase.sign            , 200);
            FuelRegistry.addFuelItem(ItemBase.woodDoor        , 200);

            /** - 5 second fuel duration */
            FuelRegistry.addFuelItem(ItemBase.bowl            , 100);
        }

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            /** - Armour Repair */
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherHelmet     .id, 1, ItemBase.leatherHelmet      .getDurability()), new ItemInstance(ItemBase.leatherHelmet      .id, 1, -1), new ItemInstance(ItemBase.leatherHelmet     .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherChestplate .id, 1, ItemBase.leatherChestplate  .getDurability()), new ItemInstance(ItemBase.leatherChestplate  .id, 1, -1), new ItemInstance(ItemBase.leatherChestplate .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherLeggings   .id, 1, ItemBase.leatherLeggings    .getDurability()), new ItemInstance(ItemBase.leatherLeggings    .id, 1, -1), new ItemInstance(ItemBase.leatherLeggings   .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherBoots      .id, 1, ItemBase.leatherBoots       .getDurability()), new ItemInstance(ItemBase.leatherBoots       .id, 1, -1), new ItemInstance(ItemBase.leatherBoots      .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainHelmet       .id, 1, ItemBase.chainHelmet        .getDurability()), new ItemInstance(ItemBase.chainHelmet        .id, 1, -1), new ItemInstance(ItemBase.chainHelmet       .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainChestplate   .id, 1, ItemBase.chainChestplate    .getDurability()), new ItemInstance(ItemBase.chainChestplate    .id, 1, -1), new ItemInstance(ItemBase.chainChestplate   .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainLeggings     .id, 1, ItemBase.chainLeggings      .getDurability()), new ItemInstance(ItemBase.chainLeggings      .id, 1, -1), new ItemInstance(ItemBase.chainLeggings     .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainBoots        .id, 1, ItemBase.chainBoots         .getDurability()), new ItemInstance(ItemBase.chainBoots         .id, 1, -1), new ItemInstance(ItemBase.chainBoots        .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironHelmet        .id, 1, ItemBase.ironHelmet         .getDurability()), new ItemInstance(ItemBase.ironHelmet         .id, 1, -1), new ItemInstance(ItemBase.ironHelmet        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironChestplate    .id, 1, ItemBase.ironChestplate     .getDurability()), new ItemInstance(ItemBase.ironChestplate     .id, 1, -1), new ItemInstance(ItemBase.ironChestplate    .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironLeggings      .id, 1, ItemBase.ironLeggings       .getDurability()), new ItemInstance(ItemBase.ironLeggings       .id, 1, -1), new ItemInstance(ItemBase.ironLeggings      .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironBoots         .id, 1, ItemBase.ironBoots          .getDurability()), new ItemInstance(ItemBase.ironBoots          .id, 1, -1), new ItemInstance(ItemBase.ironBoots         .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondHelmet     .id, 1, ItemBase.diamondHelmet      .getDurability()), new ItemInstance(ItemBase.diamondHelmet      .id, 1, -1), new ItemInstance(ItemBase.diamondHelmet     .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondChestplate .id, 1, ItemBase.diamondChestplate  .getDurability()), new ItemInstance(ItemBase.diamondChestplate  .id, 1, -1), new ItemInstance(ItemBase.diamondChestplate .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondLeggings   .id, 1, ItemBase.diamondLeggings    .getDurability()), new ItemInstance(ItemBase.diamondLeggings    .id, 1, -1), new ItemInstance(ItemBase.diamondLeggings   .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondBoots      .id, 1, ItemBase.diamondBoots       .getDurability()), new ItemInstance(ItemBase.diamondBoots       .id, 1, -1), new ItemInstance(ItemBase.diamondBoots      .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldHelmet        .id, 1, ItemBase.goldHelmet         .getDurability()), new ItemInstance(ItemBase.goldHelmet         .id, 1, -1), new ItemInstance(ItemBase.goldHelmet        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldChestplate    .id, 1, ItemBase.goldChestplate     .getDurability()), new ItemInstance(ItemBase.goldChestplate     .id, 1, -1), new ItemInstance(ItemBase.goldChestplate    .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldLeggings      .id, 1, ItemBase.goldLeggings       .getDurability()), new ItemInstance(ItemBase.goldLeggings       .id, 1, -1), new ItemInstance(ItemBase.goldLeggings      .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldBoots         .id, 1, ItemBase.goldBoots          .getDurability()), new ItemInstance(ItemBase.goldBoots          .id, 1, -1), new ItemInstance(ItemBase.goldBoots         .id, 1, -1));

            /** - Tool Repair */
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.woodAxe           .id, 1, ItemBase.woodAxe            .getDurability()), new ItemInstance(ItemBase.woodAxe            .id, 1, -1), new ItemInstance(ItemBase.woodAxe           .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.woodHoe           .id, 1, ItemBase.woodHoe            .getDurability()), new ItemInstance(ItemBase.woodHoe            .id, 1, -1), new ItemInstance(ItemBase.woodHoe           .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.woodPickaxe       .id, 1, ItemBase.woodPickaxe        .getDurability()), new ItemInstance(ItemBase.woodPickaxe        .id, 1, -1), new ItemInstance(ItemBase.woodPickaxe       .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.woodShovel        .id, 1, ItemBase.woodShovel         .getDurability()), new ItemInstance(ItemBase.woodShovel         .id, 1, -1), new ItemInstance(ItemBase.woodShovel        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.woodSword         .id, 1, ItemBase.woodSword          .getDurability()), new ItemInstance(ItemBase.woodSword          .id, 1, -1), new ItemInstance(ItemBase.woodSword         .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.stoneAxe          .id, 1, ItemBase.stoneAxe           .getDurability()), new ItemInstance(ItemBase.stoneAxe           .id, 1, -1), new ItemInstance(ItemBase.stoneAxe          .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.stoneHoe          .id, 1, ItemBase.stoneHoe           .getDurability()), new ItemInstance(ItemBase.stoneHoe           .id, 1, -1), new ItemInstance(ItemBase.stoneHoe          .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.stonePickaxe      .id, 1, ItemBase.stonePickaxe       .getDurability()), new ItemInstance(ItemBase.stonePickaxe       .id, 1, -1), new ItemInstance(ItemBase.stonePickaxe      .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.stoneShovel       .id, 1, ItemBase.stoneShovel        .getDurability()), new ItemInstance(ItemBase.stoneShovel        .id, 1, -1), new ItemInstance(ItemBase.stoneShovel       .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.stoneSword        .id, 1, ItemBase.stoneSword         .getDurability()), new ItemInstance(ItemBase.stoneSword         .id, 1, -1), new ItemInstance(ItemBase.stoneSword        .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironAxe           .id, 1, ItemBase.ironAxe            .getDurability()), new ItemInstance(ItemBase.ironAxe            .id, 1, -1), new ItemInstance(ItemBase.ironAxe           .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironHoe           .id, 1, ItemBase.ironHoe            .getDurability()), new ItemInstance(ItemBase.ironHoe            .id, 1, -1), new ItemInstance(ItemBase.ironHoe           .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironPickaxe       .id, 1, ItemBase.ironPickaxe        .getDurability()), new ItemInstance(ItemBase.ironPickaxe        .id, 1, -1), new ItemInstance(ItemBase.ironPickaxe       .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironShovel        .id, 1, ItemBase.ironShovel         .getDurability()), new ItemInstance(ItemBase.ironShovel         .id, 1, -1), new ItemInstance(ItemBase.ironShovel        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironSword         .id, 1, ItemBase.ironSword          .getDurability()), new ItemInstance(ItemBase.ironSword          .id, 1, -1), new ItemInstance(ItemBase.ironSword         .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondAxe        .id, 1, ItemBase.diamondAxe         .getDurability()), new ItemInstance(ItemBase.diamondAxe         .id, 1, -1), new ItemInstance(ItemBase.diamondAxe        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondHoe        .id, 1, ItemBase.diamondHoe         .getDurability()), new ItemInstance(ItemBase.diamondHoe         .id, 1, -1), new ItemInstance(ItemBase.diamondHoe        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondPickaxe    .id, 1, ItemBase.diamondPickaxe     .getDurability()), new ItemInstance(ItemBase.diamondPickaxe     .id, 1, -1), new ItemInstance(ItemBase.diamondPickaxe    .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondShovel     .id, 1, ItemBase.diamondShovel      .getDurability()), new ItemInstance(ItemBase.diamondShovel      .id, 1, -1), new ItemInstance(ItemBase.diamondShovel     .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondSword      .id, 1, ItemBase.diamondSword       .getDurability()), new ItemInstance(ItemBase.diamondSword       .id, 1, -1), new ItemInstance(ItemBase.diamondSword      .id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldAxe           .id, 1, ItemBase.goldAxe            .getDurability()), new ItemInstance(ItemBase.goldAxe            .id, 1, -1), new ItemInstance(ItemBase.goldAxe           .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldHoe           .id, 1, ItemBase.goldHoe            .getDurability()), new ItemInstance(ItemBase.goldHoe            .id, 1, -1), new ItemInstance(ItemBase.goldHoe           .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldPickaxe       .id, 1, ItemBase.goldPickaxe        .getDurability()), new ItemInstance(ItemBase.goldPickaxe        .id, 1, -1), new ItemInstance(ItemBase.goldPickaxe       .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldShovel        .id, 1, ItemBase.goldShovel         .getDurability()), new ItemInstance(ItemBase.goldShovel         .id, 1, -1), new ItemInstance(ItemBase.goldShovel        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.goldSword         .id, 1, ItemBase.goldSword          .getDurability()), new ItemInstance(ItemBase.goldSword          .id, 1, -1), new ItemInstance(ItemBase.goldSword         .id, 1, -1));

            /** - Misc. Tool Repair */
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.fishingRod        .id, 1, ItemBase.fishingRod         .getDurability()), new ItemInstance(ItemBase.fishingRod         .id, 1, -1), new ItemInstance(ItemBase.fishingRod        .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.flintAndSteel     .id, 1, ItemBase.flintAndSteel      .getDurability()), new ItemInstance(ItemBase.flintAndSteel      .id, 1, -1), new ItemInstance(ItemBase.flintAndSteel     .id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.shears            .id, 1, ItemBase.shears             .getDurability()), new ItemInstance(ItemBase.shears             .id, 1, -1), new ItemInstance(ItemBase.shears            .id, 1, -1));
        }
    }
}