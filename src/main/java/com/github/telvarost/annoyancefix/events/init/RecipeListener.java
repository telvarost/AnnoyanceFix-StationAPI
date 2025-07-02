package com.github.telvarost.annoyancefix.events.init;

import com.github.telvarost.annoyancefix.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
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

                /** - 7.5 second fuel duration */
                FuelRegistry.addFuelItem(Block.SLAB.asItem(), 2, 150);

                /** - 5 second fuel duration */
                FuelRegistry.addFuelItem(Item.BOWL, 100);
            }
        }
    }
}