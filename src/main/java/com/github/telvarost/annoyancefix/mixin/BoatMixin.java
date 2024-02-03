package com.github.telvarost.annoyancefix.mixin;

import com.github.telvarost.annoyancefix.Config;
import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
abstract class BoatMixin extends EntityBase {

    public BoatMixin(Level level) {
        super(level);
    }

    @ModifyConstant(
            method = "damage",
            constant = @Constant(intValue = 3)
    )
    private int annoyanceFix_skipPlanksDrop(int constant) {
        if (Config.ConfigFields.boatDropFixesEnabled) {
            return 0;
        } else {
            return 3;
        }
    }

    @ModifyConstant(
            method = "damage",
            constant = @Constant(intValue = 2)
    )
    private int annoyanceFix_skipSticksDrop(int constant) {
        if (Config.ConfigFields.boatDropFixesEnabled) {
            return 0;
        } else {
            return 2;
        }
    }

    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Boat;remove()V"
            )
    )
    private void annoyanceFix_dropBoat(EntityBase attacker, int damage, CallbackInfoReturnable<Boolean> cir) {
        if (Config.ConfigFields.boatDropFixesEnabled) {
            dropItem(ItemBase.boat.id, 1, 0);
        }
    }

    @ModifyConstant(
            method = "tick",
            constant = @Constant(intValue = 3)
    )
    private int annoyanceFix_skipPlanksDropOnCollision(int constant) {
        if (0 < Config.ConfigFields.boatCollisionBehavior) {
            return 0;
        } else {
            return 3;
        }
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Boat;dropItem(IIF)Lnet/minecraft/entity/Item;",
                    ordinal = 1
            )
    )
    private Item annoyanceFix_skipSticksDropOnCollision(Boat instance, int i, int j, float f) {
        if (0 < Config.ConfigFields.boatCollisionBehavior) {
            return null;
        } else {
            return instance.dropItem(i, j, f);
        }
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Boat;remove()V"
            )
    )
    private void annoyanceFix_dropBoatOnCollision(CallbackInfo ci) {
        if (0 < Config.ConfigFields.boatCollisionBehavior) {
            dropItem(ItemBase.boat.id, 1, 0);
        }
    }

    @Redirect(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/Boat;field_1624:Z",
                    opcode = Opcodes.GETFIELD
            )
    )
    private boolean annoyanceFix_stopBoatBreaking(Boat instance) {
        if (1 < Config.ConfigFields.boatCollisionBehavior) {
            return false;
        } else {
            return this.field_1624;
        }
    }

    @Inject(
            method = "interact",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerBase;startRiding(Lnet/minecraft/entity/EntityBase;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void annoyanceFix_compensateForFloatingPointErrors(PlayerBase player, CallbackInfoReturnable<Boolean> cir) {
        // If player is not riding anything after interacting with the boat, it must have unmounted
        if (player.vehicle == null) {
            // Compensate for floating point errors
            player.setPosition(player.x, player.y + 0.01, player.z);
        }
    }
}

