package net.glasslauncher.annoyancefix.mixin;

import net.glasslauncher.annoyancefix.Config;
import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
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
    private int annoyancefix_skipPlanksDrop(int constant) {
        if (Config.ConfigFields.boatFixesEnabled)
        {
            return 0;
        }
        else
        {
            return 3;
        }
    }

    @ModifyConstant(
            method = "damage",
            constant = @Constant(intValue = 2)
    )
    private int annoyancefix_skipSticksDrop(int constant) {
        if (Config.ConfigFields.boatFixesEnabled)
        {
            return 0;
        }
        else
        {
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
    private void annoyancefix_dropBoat(EntityBase attacker, int damage, CallbackInfoReturnable<Boolean> cir) {
        if (Config.ConfigFields.boatFixesEnabled) {
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
    private boolean annoyancefix_stopBoatBreaking(Boat instance) {
        if (Config.ConfigFields.boatFixesEnabled) {
            return false;
        }
        else
        {
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
    private void annoyancefix_compensateForFloatingPointErrors(PlayerBase player, CallbackInfoReturnable<Boolean> cir) {
        if (Config.ConfigFields.boatFixesEnabled) {
            // If player is not riding anything after interacting with the boat, it must have unmounted
            if (player.vehicle == null)
                // Compensate for floating point errors
                player.setPosition(player.x, player.y + 0.01, player.z);
        }
    }
}

