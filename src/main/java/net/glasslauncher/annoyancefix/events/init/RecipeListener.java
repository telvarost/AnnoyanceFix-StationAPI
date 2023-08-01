package net.glasslauncher.annoyancefix.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherHelmet.id, 1, ItemBase.leatherHelmet.getDurability()), new ItemInstance(ItemBase.leatherHelmet.id, 1, -1), new ItemInstance(ItemBase.leatherHelmet.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherChestplate.id, 1, ItemBase.leatherChestplate.getDurability()), new ItemInstance(ItemBase.leatherChestplate.id, 1, -1), new ItemInstance(ItemBase.leatherChestplate.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherLeggings.id, 1, ItemBase.leatherLeggings.getDurability()), new ItemInstance(ItemBase.leatherLeggings.id, 1, -1), new ItemInstance(ItemBase.leatherLeggings.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.leatherBoots.id, 1, ItemBase.leatherBoots.getDurability()), new ItemInstance(ItemBase.leatherBoots.id, 1, -1), new ItemInstance(ItemBase.leatherBoots.id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainHelmet.id, 1, ItemBase.chainHelmet.getDurability()), new ItemInstance(ItemBase.chainHelmet.id, 1, -1), new ItemInstance(ItemBase.chainHelmet.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainChestplate.id, 1, ItemBase.chainChestplate.getDurability()), new ItemInstance(ItemBase.chainChestplate.id, 1, -1), new ItemInstance(ItemBase.chainChestplate.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainLeggings.id, 1, ItemBase.chainLeggings.getDurability()), new ItemInstance(ItemBase.chainLeggings.id, 1, -1), new ItemInstance(ItemBase.chainLeggings.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.chainBoots.id, 1, ItemBase.chainBoots.getDurability()), new ItemInstance(ItemBase.chainBoots.id, 1, -1), new ItemInstance(ItemBase.chainBoots.id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironHelmet.id, 1, ItemBase.ironHelmet.getDurability()), new ItemInstance(ItemBase.ironHelmet.id, 1, -1), new ItemInstance(ItemBase.ironHelmet.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironChestplate.id, 1, ItemBase.ironChestplate.getDurability()), new ItemInstance(ItemBase.ironChestplate.id, 1, -1), new ItemInstance(ItemBase.ironChestplate.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironLeggings.id, 1, ItemBase.ironLeggings.getDurability()), new ItemInstance(ItemBase.ironLeggings.id, 1, -1), new ItemInstance(ItemBase.ironLeggings.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.ironBoots.id, 1, ItemBase.ironBoots.getDurability()), new ItemInstance(ItemBase.ironBoots.id, 1, -1), new ItemInstance(ItemBase.ironBoots.id, 1, -1));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondHelmet.id, 1, ItemBase.diamondHelmet.getDurability()), new ItemInstance(ItemBase.diamondHelmet.id, 1, -1), new ItemInstance(ItemBase.diamondHelmet.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondChestplate.id, 1, ItemBase.diamondChestplate.getDurability()), new ItemInstance(ItemBase.diamondChestplate.id, 1, -1), new ItemInstance(ItemBase.diamondChestplate.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondLeggings.id, 1, ItemBase.diamondLeggings.getDurability()), new ItemInstance(ItemBase.diamondLeggings.id, 1, -1), new ItemInstance(ItemBase.diamondLeggings.id, 1, -1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.diamondBoots.id, 1, ItemBase.diamondBoots.getDurability()), new ItemInstance(ItemBase.diamondBoots.id, 1, -1), new ItemInstance(ItemBase.diamondBoots.id, 1, -1));
        }
    }
}