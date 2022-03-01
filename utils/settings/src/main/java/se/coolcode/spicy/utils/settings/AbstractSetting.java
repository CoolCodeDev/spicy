package se.coolcode.spicy.utils.settings;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class AbstractSetting<T> implements Setting<T> {

    private String key;
    private T value;
    private Set<BiConsumer<T, T>> listeners;
    private Class<T> type;

    AbstractSetting(String key, T value, Class<T> type) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.listeners = new HashSet<>();
    }

    @Override
    public Class<T> getType() {
        return type;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void updateValue(String newValue) {
        T updatedValue = parse(newValue);
        
        if (!value.equals(updatedValue)) {
            T oldValue = value;
            value = updatedValue;
            listeners.stream()
            .forEach(listener -> listener.accept(oldValue, value));
        }
    }

    @Override
    public void addListener(BiConsumer<T, T> listener) {
        this.listeners.add(listener);
    }

    protected abstract T parse(String newValue);
    
}
