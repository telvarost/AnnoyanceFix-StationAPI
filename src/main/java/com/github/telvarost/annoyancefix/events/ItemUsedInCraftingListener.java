package com.github.telvarost.annoyancefix.events;

import com.github.telvarost.annoyancefix.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;

public class ItemUsedInCraftingListener {

    @EventListener
    public void combineDurability(ItemUsedInCraftingEvent event) {
        if (  (Config.ConfigFields.armorFixesEnabled)
           && (null != event.itemUsed)
           && (true == event.itemUsed.hasDurability())
           && (null != event.itemCrafted)
           && (true == event.itemCrafted.hasDurability())
           )
        {
            int durabilityToAdd = event.itemUsed.getDurability() - event.itemUsed.getDamage();
            int newDurability = event.itemCrafted.getDurability() - event.itemCrafted.getDamage();
            int damageToSet = 0;

            newDurability = newDurability + durabilityToAdd;

            /** - Only calculate damage if new durability is below max durability */
            if (event.itemCrafted.getDurability() > newDurability)
            {
                /** - Max durability - new durability = damage to apply to max durability */
                damageToSet = event.itemCrafted.getDurability() - newDurability;
            }

            event.itemCrafted.setDamage(damageToSet);
        }
    }
}