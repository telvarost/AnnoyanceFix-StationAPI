package com.github.telvarost.annoyancefix.events.init;

import com.github.telvarost.annoyancefix.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            if (Config.config.RECIPES_CONFIG.recipesAdditionalWoodFuelsEnabled) {
                /** - 60 second fuel duration */
                FuelRegistry.addFuelItem(Item.BOAT, 1200);

                /** - 15 second fuel duration */
                FuelRegistry.addFuelItem(Item.BOW, 300);
                FuelRegistry.addFuelItem(Item.FISHING_ROD, 300);
                FuelRegistry.addFuelItem(Block.LADDER.asItem(), 300);

                /** - 10 second fuel duration */
                FuelRegistry.addFuelItem(Item.WOODEN_AXE, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_HOE, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_PICKAXE, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_SHOVEL, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_SWORD, 200);
                FuelRegistry.addFuelItem(Item.SIGN, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_DOOR, 200);

                /** - 5 second fuel duration */
                FuelRegistry.addFuelItem(Item.BOWL, 100);
            }
        }

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            if (Config.config.RECIPES_CONFIG.recipesRepairArmorEnabled) {
                /** - Armour Repair */
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.LEATHER_HELMET.id, 1, Item.LEATHER_HELMET.getMaxDamage()), new ItemStack(Item.LEATHER_HELMET.id, 1, -1), new ItemStack(Item.LEATHER_HELMET.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.LEATHER_CHESTPLATE.id, 1, Item.LEATHER_CHESTPLATE.getMaxDamage()), new ItemStack(Item.LEATHER_CHESTPLATE.id, 1, -1), new ItemStack(Item.LEATHER_CHESTPLATE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.LEATHER_LEGGINGS.id, 1, Item.LEATHER_LEGGINGS.getMaxDamage()), new ItemStack(Item.LEATHER_LEGGINGS.id, 1, -1), new ItemStack(Item.LEATHER_LEGGINGS.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.LEATHER_BOOTS.id, 1, Item.LEATHER_BOOTS.getMaxDamage()), new ItemStack(Item.LEATHER_BOOTS.id, 1, -1), new ItemStack(Item.LEATHER_BOOTS.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.CHAIN_HELMET.id, 1, Item.CHAIN_HELMET.getMaxDamage()), new ItemStack(Item.CHAIN_HELMET.id, 1, -1), new ItemStack(Item.CHAIN_HELMET.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.CHAIN_CHESTPLATE.id, 1, Item.CHAIN_CHESTPLATE.getMaxDamage()), new ItemStack(Item.CHAIN_CHESTPLATE.id, 1, -1), new ItemStack(Item.CHAIN_CHESTPLATE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.CHAIN_LEGGINGS.id, 1, Item.CHAIN_LEGGINGS.getMaxDamage()), new ItemStack(Item.CHAIN_LEGGINGS.id, 1, -1), new ItemStack(Item.CHAIN_LEGGINGS.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.CHAIN_BOOTS.id, 1, Item.CHAIN_BOOTS.getMaxDamage()), new ItemStack(Item.CHAIN_BOOTS.id, 1, -1), new ItemStack(Item.CHAIN_BOOTS.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_HELMET.id, 1, Item.IRON_HELMET.getMaxDamage()), new ItemStack(Item.IRON_HELMET.id, 1, -1), new ItemStack(Item.IRON_HELMET.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_CHESTPLATE.id, 1, Item.IRON_CHESTPLATE.getMaxDamage()), new ItemStack(Item.IRON_CHESTPLATE.id, 1, -1), new ItemStack(Item.IRON_CHESTPLATE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_LEGGINGS.id, 1, Item.IRON_LEGGINGS.getMaxDamage()), new ItemStack(Item.IRON_LEGGINGS.id, 1, -1), new ItemStack(Item.IRON_LEGGINGS.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_BOOTS.id, 1, Item.IRON_BOOTS.getMaxDamage()), new ItemStack(Item.IRON_BOOTS.id, 1, -1), new ItemStack(Item.IRON_BOOTS.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_HELMET.id, 1, Item.DIAMOND_HELMET.getMaxDamage()), new ItemStack(Item.DIAMOND_HELMET.id, 1, -1), new ItemStack(Item.DIAMOND_HELMET.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_CHESTPLATE.id, 1, Item.DIAMOND_CHESTPLATE.getMaxDamage()), new ItemStack(Item.DIAMOND_CHESTPLATE.id, 1, -1), new ItemStack(Item.DIAMOND_CHESTPLATE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_LEGGINGS.id, 1, Item.DIAMOND_LEGGINGS.getMaxDamage()), new ItemStack(Item.DIAMOND_LEGGINGS.id, 1, -1), new ItemStack(Item.DIAMOND_LEGGINGS.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_BOOTS.id, 1, Item.DIAMOND_BOOTS.getMaxDamage()), new ItemStack(Item.DIAMOND_BOOTS.id, 1, -1), new ItemStack(Item.DIAMOND_BOOTS.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_HELMET.id, 1, Item.GOLDEN_HELMET.getMaxDamage()), new ItemStack(Item.GOLDEN_HELMET.id, 1, -1), new ItemStack(Item.GOLDEN_HELMET.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_CHESTPLATE.id, 1, Item.GOLDEN_CHESTPLATE.getMaxDamage()), new ItemStack(Item.GOLDEN_CHESTPLATE.id, 1, -1), new ItemStack(Item.GOLDEN_CHESTPLATE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_LEGGINGS.id, 1, Item.GOLDEN_LEGGINGS.getMaxDamage()), new ItemStack(Item.GOLDEN_LEGGINGS.id, 1, -1), new ItemStack(Item.GOLDEN_LEGGINGS.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_BOOTS.id, 1, Item.GOLDEN_BOOTS.getMaxDamage()), new ItemStack(Item.GOLDEN_BOOTS.id, 1, -1), new ItemStack(Item.GOLDEN_BOOTS.id, 1, -1));
            }

            if (Config.config.RECIPES_CONFIG.recipesRepairToolsEnabled) {
                /** - Tool Repair */
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.WOODEN_AXE.id, 1, Item.WOODEN_AXE.getMaxDamage()), new ItemStack(Item.WOODEN_AXE.id, 1, -1), new ItemStack(Item.WOODEN_AXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.WOODEN_HOE.id, 1, Item.WOODEN_HOE.getMaxDamage()), new ItemStack(Item.WOODEN_HOE.id, 1, -1), new ItemStack(Item.WOODEN_HOE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.WOODEN_PICKAXE.id, 1, Item.WOODEN_PICKAXE.getMaxDamage()), new ItemStack(Item.WOODEN_PICKAXE.id, 1, -1), new ItemStack(Item.WOODEN_PICKAXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.WOODEN_SHOVEL.id, 1, Item.WOODEN_SHOVEL.getMaxDamage()), new ItemStack(Item.WOODEN_SHOVEL.id, 1, -1), new ItemStack(Item.WOODEN_SHOVEL.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.WOODEN_SWORD.id, 1, Item.WOODEN_SWORD.getMaxDamage()), new ItemStack(Item.WOODEN_SWORD.id, 1, -1), new ItemStack(Item.WOODEN_SWORD.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.STONE_AXE.id, 1, Item.STONE_AXE.getMaxDamage()), new ItemStack(Item.STONE_AXE.id, 1, -1), new ItemStack(Item.STONE_AXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.STONE_HOE.id, 1, Item.STONE_HOE.getMaxDamage()), new ItemStack(Item.STONE_HOE.id, 1, -1), new ItemStack(Item.STONE_HOE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.STONE_PICKAXE.id, 1, Item.STONE_PICKAXE.getMaxDamage()), new ItemStack(Item.STONE_PICKAXE.id, 1, -1), new ItemStack(Item.STONE_PICKAXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.STONE_SHOVEL.id, 1, Item.STONE_SHOVEL.getMaxDamage()), new ItemStack(Item.STONE_SHOVEL.id, 1, -1), new ItemStack(Item.STONE_SHOVEL.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.STONE_SWORD.id, 1, Item.STONE_SWORD.getMaxDamage()), new ItemStack(Item.STONE_SWORD.id, 1, -1), new ItemStack(Item.STONE_SWORD.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_AXE.id, 1, Item.IRON_AXE.getMaxDamage()), new ItemStack(Item.IRON_AXE.id, 1, -1), new ItemStack(Item.IRON_AXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_HOE.id, 1, Item.IRON_HOE.getMaxDamage()), new ItemStack(Item.IRON_HOE.id, 1, -1), new ItemStack(Item.IRON_HOE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_PICKAXE.id, 1, Item.IRON_PICKAXE.getMaxDamage()), new ItemStack(Item.IRON_PICKAXE.id, 1, -1), new ItemStack(Item.IRON_PICKAXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_SHOVEL.id, 1, Item.IRON_SHOVEL.getMaxDamage()), new ItemStack(Item.IRON_SHOVEL.id, 1, -1), new ItemStack(Item.IRON_SHOVEL.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_SWORD.id, 1, Item.IRON_SWORD.getMaxDamage()), new ItemStack(Item.IRON_SWORD.id, 1, -1), new ItemStack(Item.IRON_SWORD.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_AXE.id, 1, Item.DIAMOND_AXE.getMaxDamage()), new ItemStack(Item.DIAMOND_AXE.id, 1, -1), new ItemStack(Item.DIAMOND_AXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_HOE.id, 1, Item.DIAMOND_HOE.getMaxDamage()), new ItemStack(Item.DIAMOND_HOE.id, 1, -1), new ItemStack(Item.DIAMOND_HOE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_PICKAXE.id, 1, Item.DIAMOND_PICKAXE.getMaxDamage()), new ItemStack(Item.DIAMOND_PICKAXE.id, 1, -1), new ItemStack(Item.DIAMOND_PICKAXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_SHOVEL.id, 1, Item.DIAMOND_SHOVEL.getMaxDamage()), new ItemStack(Item.DIAMOND_SHOVEL.id, 1, -1), new ItemStack(Item.DIAMOND_SHOVEL.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND_SWORD.id, 1, Item.DIAMOND_SWORD.getMaxDamage()), new ItemStack(Item.DIAMOND_SWORD.id, 1, -1), new ItemStack(Item.DIAMOND_SWORD.id, 1, -1));

                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_AXE.id, 1, Item.GOLDEN_AXE.getMaxDamage()), new ItemStack(Item.GOLDEN_AXE.id, 1, -1), new ItemStack(Item.GOLDEN_AXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_HOE.id, 1, Item.GOLDEN_HOE.getMaxDamage()), new ItemStack(Item.GOLDEN_HOE.id, 1, -1), new ItemStack(Item.GOLDEN_HOE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_PICKAXE.id, 1, Item.GOLDEN_PICKAXE.getMaxDamage()), new ItemStack(Item.GOLDEN_PICKAXE.id, 1, -1), new ItemStack(Item.GOLDEN_PICKAXE.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_SHOVEL.id, 1, Item.GOLDEN_SHOVEL.getMaxDamage()), new ItemStack(Item.GOLDEN_SHOVEL.id, 1, -1), new ItemStack(Item.GOLDEN_SHOVEL.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLDEN_SWORD.id, 1, Item.GOLDEN_SWORD.getMaxDamage()), new ItemStack(Item.GOLDEN_SWORD.id, 1, -1), new ItemStack(Item.GOLDEN_SWORD.id, 1, -1));

                /** - Misc. Tool Repair */
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.FISHING_ROD.id, 1, Item.FISHING_ROD.getMaxDamage()), new ItemStack(Item.FISHING_ROD.id, 1, -1), new ItemStack(Item.FISHING_ROD.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.FLINT_AND_STEEL.id, 1, Item.FLINT_AND_STEEL.getMaxDamage()), new ItemStack(Item.FLINT_AND_STEEL.id, 1, -1), new ItemStack(Item.FLINT_AND_STEEL.id, 1, -1));
                CraftingRegistry.addShapelessRecipe(new ItemStack(Item.SHEARS.id, 1, Item.SHEARS.getMaxDamage()), new ItemStack(Item.SHEARS.id, 1, -1), new ItemStack(Item.SHEARS.id, 1, -1));
            }
        }
    }
}