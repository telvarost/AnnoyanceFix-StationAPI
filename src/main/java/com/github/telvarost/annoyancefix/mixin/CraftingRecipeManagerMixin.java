package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import com.github.telvarost.annoyancefix.compat.FarnNameTagCompat;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** - All credit for the code in this class goes to Dany and his mod UniTweaks
 *  See: https://github.com/DanyGames2014/UniTweaks
 */
@Mixin(CraftingRecipeManager.class)
public class CraftingRecipeManagerMixin {

    @Inject(method = "craft", at = @At(value = "HEAD"), cancellable = true)
    public void combineDurability(CraftingInventory inventory, CallbackInfoReturnable<ItemStack> cir) {
        if (Config.config.RECIPES_CONFIG.recipesRepairItemsEnabled || Config.config.RECIPES_CONFIG.recipesRepairArmorEnabled)
        {
            ItemStack stack1 = null;
            ItemStack stack2 = null;
            int craftingStacks = 0;

            for (ItemStack stack : inventory.stacks) {
                if (stack != null) {
                    craftingStacks++;

                    if (stack.getItem().isDamageable()) {
                        Item stackItem = stack.getItem();

                        if (stackItem instanceof ArmorItem) {
                            if (Config.config.RECIPES_CONFIG.recipesRepairArmorEnabled) {
                                if (stack1 != null && stack1.getItem() == stack.getItem()) {
                                    stack2 = stack;
                                } else {
                                    stack1 = stack;
                                }
                            }
                        } else {
                            if (Config.config.RECIPES_CONFIG.recipesRepairItemsEnabled) {
                                if (stack1 != null && stack1.getItem() == stack.getItem()) {
                                    stack2 = stack;
                                } else {
                                    stack1 = stack;
                                }
                            }
                        }
                    }
                }
            }

            if(stack1 != null && stack2 != null && craftingStacks == 2) {
                Item stackItem = stack1.getItem();

                int item1durability = stackItem.getMaxDamage() - stack1.getDamage();
                int item2durability = stackItem.getMaxDamage() - stack2.getDamage();
                int addedDurability = item1durability + item2durability + stackItem.getMaxDamage() * 10 / 100;
                int resultDurability = stackItem.getMaxDamage() - addedDurability;
                if (resultDurability < 0) {
                    resultDurability = 0;
                }

                ItemStack result = new ItemStack(stackItem, 1, resultDurability);

                // Farn's Nametags compat
                if (ModHelper.ModHelperFields.isFarnNameTagLoaded) {
                    FarnNameTagCompat.checkForCustomNametagAndAddToResult(stack1, stack2, result);
                }

                cir.setReturnValue(result);
            }
        }
    }
}
