package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.entity.animal.AnimalBase;
import net.minecraft.entity.animal.Pig;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Pig.class)
public abstract class PigMixin extends AnimalBase {

    @Shadow public abstract boolean isSaddled();

    public PigMixin(Level arg) {
        super(arg);
        this.texture = "/mob/pig.png";
        this.setSize(0.9F, 0.9F);
    }

    @Override
    protected void getDrops() {
        int var1 = this.getMobDrops();
        if (var1 > 0) {
            int var2 = this.rand.nextInt(3);

            for(int var3 = 0; var3 < var2; ++var3) {
                this.dropItem(var1, 1);
            }

            if (  (Config.config.pigFixesEnabled)
               && (this.isSaddled())
               )
            {
                this.dropItem(ItemBase.saddle.id, 1);
            }
        }
    }
}
