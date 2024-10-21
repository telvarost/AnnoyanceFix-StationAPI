package com.github.telvarost.annoyancefix;

import com.google.common.collect.ImmutableMap;
import net.glasslauncher.mods.gcapi3.api.*;
import net.glasslauncher.mods.gcapi3.impl.SeptFunction;
import net.glasslauncher.mods.gcapi3.impl.object.ConfigEntryHandler;
import net.glasslauncher.mods.gcapi3.impl.object.entry.EnumConfigEntryHandler;

import java.lang.reflect.*;
import java.util.function.*;

public class BoatCollisionEnumFactory implements ConfigFactoryProvider {

    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, SeptFunction<String, ConfigEntry, Field, Object, Boolean, Object, Object, ConfigEntryHandler<?>>> immutableBuilder) {
        immutableBuilder.put(BoatCollisionEnum.class, ((id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) ->
        {
            int enumOrdinal;
            if(enumOrOrdinal instanceof Integer ordinal) {
                enumOrdinal = ordinal;
            }
            else {
                enumOrdinal = ((BoatCollisionEnum) enumOrOrdinal).ordinal();
            }
            return new EnumConfigEntryHandler<BoatCollisionEnum>(id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrdinal, ((BoatCollisionEnum) defaultEnum).ordinal(), BoatCollisionEnum.class);
        }));
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, Object>> immutableBuilder) {
        immutableBuilder.put(BoatCollisionEnum.class, enumEntry -> enumEntry);
    }
}
