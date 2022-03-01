package se.coolcode.spicy.util.featureflags;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FixedFeatureFlags extends FeatureFlags {

    private Map<String, FeatureFlag> featureFlags;

    FixedFeatureFlags() {
        this.featureFlags = new HashMap<>();
    }

    public FeatureFlags createBinary(String key, boolean initValue) {
        this.featureFlags.putIfAbsent(key, new BinaryFeatureFlag(key, initValue));
        return this;
    }

    public FeatureFlags createCanary(String key, int initValue) {
        this.featureFlags.putIfAbsent(key, new CanaryFeatureFlag(key, initValue));
        return this;
    }

    public FeatureFlags createExplicit(String key, Set<String> initValues) {
        this.featureFlags.putIfAbsent(key, new ExplicitFeatureFlag(key, initValues));
        return this;
    }
}
