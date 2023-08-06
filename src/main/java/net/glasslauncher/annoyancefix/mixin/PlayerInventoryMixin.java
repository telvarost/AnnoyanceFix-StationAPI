package net.glasslauncher.annoyancefix.mixin;

import net.glasslauncher.annoyancefix.Config;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerInventory.class)
public abstract class PlayerInventoryMixin {

    @Shadow
    protected abstract int getSlotWithItem(int i);

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
    @Inject(at = @At("HEAD"), method = "setSelectedItemWithID", cancellable = true)
    public void setSelectedItemWithID(int itemID, boolean unused, CallbackInfo ci) {
        if (Config.ConfigFields.pickBlockFixesEnabled) {
            int slotWithItem = getSlotWithItem(itemID);
            PlayerInventory inventory = player.inventory;

            // Let vanilla Minecraft (or other injectors) handle cases where it is simply in the hotbar
            if (slotWithItem < 9) {
                return;
            }

            // Player has item in the rest of its inventory somewhere; find slot to place item in
            int slot;
            if (player.getHeldItem() == null) {
                slot = player.inventory.selectedHotbarSlot;
            } else {
                slot = getEmptyHotbarSlot(inventory.main);
            }

            if (slot != -1) {
                inventory.selectedHotbarSlot = slot;
                inventory.main[slot] = inventory.main[slotWithItem];
                inventory.main[slotWithItem] = null;
                ci.cancel();
                return;
            }

            // No room in hotbar, swap item with currently held item
            ItemInstance tempItem = player.getHeldItem();
            inventory.main[inventory.selectedHotbarSlot] = inventory.main[slotWithItem];
            inventory.main[slotWithItem] = tempItem;

            ci.cancel();
        }
    }

    /**
     * Returns the first empty slot of the players hotbar. If there is no empty slot, return -1.
     *
     * @param mainInventory The inventory to check.
     * @return the index of the first empty slot, or -1 if there is no empty slot
     */
    @Unique
    private int getEmptyHotbarSlot(ItemInstance[] mainInventory) {
        for (int i = 0; i < 9; i++) {
            if (mainInventory[i] == null) {
                return i;
            }
        }
        return -1;

    }
}
