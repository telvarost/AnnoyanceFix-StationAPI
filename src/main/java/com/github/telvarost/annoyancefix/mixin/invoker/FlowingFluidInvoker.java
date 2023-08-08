package com.github.telvarost.annoyancefix.mixin.invoker;

import net.minecraft.block.FlowingFluid;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FlowingFluid.class)
public interface FlowingFluidInvoker {

    @Invoker("method_1058")
    boolean annoyanceFix_method_1058(Level arg, int i, int j, int k);

    @Invoker("method_1057")
    boolean annoyanceFix_method_1057(Level arg, int i, int j, int k);

    @Invoker("method_1056")
    boolean[] annoyanceFix_method_1056(Level arg, int i, int j, int k);

    @Invoker("method_1055")
    void annoyanceFix_method_1055(Level arg, int i, int j, int k);

    @Invoker("method_1054")
    void annoyanceFix_method_1054(Level arg, int i, int j, int k, int l);

    @Invoker("method_1053")
    int annoyanceFix_method_1053(Level arg, int i, int j, int k, int l);
}
