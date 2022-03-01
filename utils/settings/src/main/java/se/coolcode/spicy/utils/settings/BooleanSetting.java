package se.coolcode.spicy.utils.settings;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class BooleanSetting implements Setting<Boolean> {

    private String key;
    private boolean value;
    private Set<BiConsumer<Boolean, Boolean>> listeners;

    public BooleanSetting(String key, boolean value) {
        this.key = key;
        this.value = value;
        this.listeners = new HashSet<>();
    }

    @Override
    public Class<Boolean> getType() {
        return Boolean.TYPE;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void updateValue(String newValue) {
        boolean updatedValue = Boolean.parseBoolean(newValue);
        if (value != updatedValue) {
            boolean oldValue = value;
            value = updatedValue;
            listeners.stream()
            .forEach(listener -> listener.accept(oldValue, value));
        }
    }

    @Override
    public void addListener(BiConsumer<Boolean, Boolean> listener) {
        this.listeners.add(listener);
    }
    
}
