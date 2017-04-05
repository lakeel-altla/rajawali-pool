package com.lakeel.altla.android.binding.commandbinder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class CommandBindingDefinitionRegistry {

    private final Map<Class<?>, Map<String, CommandBindingDefinition>> definitionMap = new HashMap<>();

    public void register(@NonNull CommandBindingDefinition definition) {
        Map<String, CommandBindingDefinition> map = definitionMap.get(definition.getViewType());
        if (map == null) {
            map = new HashMap<>();
            definitionMap.put(definition.getViewType(), map);
        }
        map.put(definition.getCommandName(), definition);
    }

    public void deregister(@NonNull CommandBindingDefinition definition) {
        Map<String, CommandBindingDefinition> map = definitionMap.get(definition.getViewType());
        if (map != null) {
            map.remove(definition.getCommandName());
        }
    }

    @Nullable
    public CommandBindingDefinition find(@NonNull Class<?> viewType, @NonNull String commandName) {
        CommandBindingDefinition definition = null;

        Map<String, CommandBindingDefinition> map = definitionMap.get(viewType);
        if (map != null) {
            definition = map.get(commandName);
        }

        if (definition == null) {
            Class<?> superclass = viewType.getSuperclass();
            return find(superclass, commandName);
        } else {
            return definition;
        }
    }
}
