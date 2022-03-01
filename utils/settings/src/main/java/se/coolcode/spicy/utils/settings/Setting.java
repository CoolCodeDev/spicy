package se.coolcode.spicy.utils.settings;

import java.util.function.BiConsumer;

public interface Setting<T> {

    Class<T> getType();
    String getKey();
    T getValue();
    void updateValue(String newValue);
    void addListener(BiConsumer<T, T> listener);
    
}
