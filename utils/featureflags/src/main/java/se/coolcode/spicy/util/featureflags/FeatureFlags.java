package se.coolcode.spicy.util.featureflags;

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
}
