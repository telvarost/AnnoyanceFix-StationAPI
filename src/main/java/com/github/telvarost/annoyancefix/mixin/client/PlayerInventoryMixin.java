package com.github.telvarost.annoyancefix.mixin.client;

import com.github.telvarost.annoyancefix.Config;
import com.github.telvarost.annoyancefix.ModHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = PlayerInventory.class)
class PlayerInventoryMixin {

    @Shadow
    public PlayerEntity player;

    @Shadow
    public ItemStack[] main;

    @WrapOperation(
            method = "setHeldItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerInventory;indexOf(I)I"
            )
    )
    private int annoyanceFix_setHeldItemCheckMeta(PlayerInventory instance, int itemId, Operation<Integer> original) {
        if (0 < Config.config.pickBlockBehavior.ordinal()) {

            for(int var2 = 0; var2 < this.main.length; ++var2) {
                if (  this.main[var2] != null
                   && this.main[var2].itemId == itemId
                   && this.main[var2].getDamage() == ModHelper.ModHelperFields.pickBlockMeta
                ) {
                    return var2;
                }
            }

            return -1;
        } else {
            return original.call(instance, itemId);
        }
    }

    /**
     * Checks if the item is in the players inventory except the hotbar. If so, it moves it to the players currently
     * selected hotbar slot. If the player is already holding an item, it moves it to the players hotbar in an
     * empty spot. If no empty slot is available, it swaps the item with the currently selected one.
     *
     * @param itemID the ID of the item to select
     * @param unused unused variable needed to match original methods signature
     * @param ci the callback info
     */
    @Inject(
            method = "setHeldItem",
            at = @At("RETURN"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void annoyanceFix_setSelectedItemWithID(int itemID, boolean unused, CallbackInfo ci, int slotWithItem) {
        if (!Config.config.pickBlockFixesEnabled) {
            return;
        }

        // Don't modify inventory on server (because the server will reject the change)
        if (player.world.isRemote) {
            return;
        }

        // Let vanilla Minecraft (or other injectors) handle cases where it is simply in the hotbar
        if (slotWithItem < 9) {
            return;
        }

        PlayerInventory inventory = player.inventory;

        // Player has item in the rest of its inventory somewhere; find slot to place item in
        int slot;
        if (player.getHand() == null) {
            slot = player.inventory.selectedSlot;
        } else {
            slot = annoyanceFix_getEmptyHotbarSlot(inventory.main);
        }

        if (slot != -1) {
            inventory.selectedSlot = slot;
            inventory.main[slot] = inventory.main[slotWithItem];
            inventory.main[slotWithItem] = null;
            return;
        }

        // No room in hotbar, swap item with currently held item
        ItemStack tempItem = player.getHand();
        inventory.main[inventory.selectedSlot] = inventory.main[slotWithItem];
        inventory.main[slotWithItem] = tempItem;
    }

    /**
     * Returns the first empty slot of the players hotbar. If there is no empty slot, return -1.
     *
     * @param mainInventory The inventory to check.
     * @return the index of the first empty slot, or -1 if there is no empty slot
     */
    @Unique
    private int annoyanceFix_getEmptyHotbarSlot(ItemStack[] mainInventory) {
        for (int i = 0; i < 9; i++) {
            if (mainInventory[i] == null) {
                return i;
            }
        }
        return -1;

    }
}
