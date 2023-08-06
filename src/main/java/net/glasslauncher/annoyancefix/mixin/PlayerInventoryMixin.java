package net.glasslauncher.annoyancefix.mixin;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;
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
    public PlayerBase player;

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
            method = "setSelectedItemWithID",
            at = @At("RETURN"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void annoyancefix_setSelectedItemWithID(
            int itemID, boolean unused, CallbackInfo ci,
            int slotWithItem
    ) {
        // Let vanilla Minecraft (or other injectors) handle cases where it is simply in the hotbar
        if (slotWithItem < 9) {
            return;
        }

        PlayerInventory inventory = player.inventory;

        // Player has item in the rest of its inventory somewhere; find slot to place item in
        int slot;
        if (player.getHeldItem() == null) {
            slot = player.inventory.selectedHotbarSlot;
        } else {
            slot = annoyancefix_getEmptyHotbarSlot(inventory.main);
        }

        if (slot != -1) {
            inventory.selectedHotbarSlot = slot;
            inventory.main[slot] = inventory.main[slotWithItem];
            inventory.main[slotWithItem] = null;
            return;
        }

        // No room in hotbar, swap item with currently held item
        ItemInstance tempItem = player.getHeldItem();
        inventory.main[inventory.selectedHotbarSlot] = inventory.main[slotWithItem];
        inventory.main[slotWithItem] = tempItem;
    }

    /**
     * Returns the first empty slot of the players hotbar. If there is no empty slot, return -1.
     *
     * @param mainInventory The inventory to check.
     * @return the index of the first empty slot, or -1 if there is no empty slot
     */
    @Unique
    private int annoyancefix_getEmptyHotbarSlot(ItemInstance[] mainInventory) {
        for (int i = 0; i < 9; i++) {
            if (mainInventory[i] == null) {
                return i;
            }
        }
        return -1;

    }
}
