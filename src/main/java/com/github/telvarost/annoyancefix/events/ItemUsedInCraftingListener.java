package com.github.telvarost.annoyancefix.events;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;

import static java.lang.Math.floor;

public class ItemUsedInCraftingListener {

    /**
     * Allows for armor repair via crafting two pieces together.
     * Minecraft defines the repair formula as follows:
     *      min(Item A uses + Item B uses + floor(Max uses / 20), Max uses)
     *      https://minecraft.fandom.com/wiki/Item_repair
     *
     * @param event Item used in crafting event which fires whenever an item is consumed in crafting or an item is crafted
     */
    @EventListener
    public void combineDurability(ItemUsedInCraftingEvent event) {
        if (  (null != event.itemUsed)
           && (true == event.itemUsed.hasDurability())
           && (null != event.itemCrafted)
           && (true == event.itemCrafted.hasDurability())
           )
        {
            int craftedItemMaxDurability = event.itemCrafted.getDurability();
            int durabilityToAdd = event.itemUsed.getDurability() - event.itemUsed.getDamage();
            int newDurability = craftedItemMaxDurability - event.itemCrafted.getDamage();
            int damageToSet = 0;

            /** - If this is the first item's durability being added, add a 5% repair buff */
            if (  (Config.ConfigFields.fivePercentBuffRepairsEnabled)
               && (event.itemCrafted.getDurability() == event.itemCrafted.getDamage())
               )
            {
                durabilityToAdd = durabilityToAdd + (int)floor((double)craftedItemMaxDurability / 20);
            }

            newDurability = newDurability + durabilityToAdd;

            /** - Only calculate damage if new durability is below max durability */
            if (craftedItemMaxDurability > newDurability) {
                /** - Max durability - new durability = damage to apply to max durability */
                damageToSet = craftedItemMaxDurability - newDurability;
            }

            event.itemCrafted.setDamage(damageToSet);
        }
    }
}