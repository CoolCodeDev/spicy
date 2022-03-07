package se.coolcode.spicy.util.featureflags;

import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import se.coolcode.spicy.utils.settings.Settings;

public class FeatureFlags {

    public static FeatureFlags dynamic() {
        return null;
    }

    public static FeatureFlags dynamic(Settings settings) {
        return new DynamicFeatureFlags(settings);
    }

    public static FeatureFlags fixed() {
        return new FixedFeatureFlags();
    }

    @SuppressWarnings("unchecked")
    public static <T> T objectToggle(Supplier<Boolean> featureFlag, Class<T> type, T active, T inactive) {
        return (T) Proxy.newProxyInstance(FeatureFlags.class.getClassLoader(), 
        new Class[] {type}, 
        (proxy, method, arguments) -> {
            return featureFlag.get() ? method.invoke(active, arguments) : method.invoke(inactive, arguments);
        });
    }
}
