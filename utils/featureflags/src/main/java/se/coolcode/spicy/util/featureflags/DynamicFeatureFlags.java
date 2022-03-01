package se.coolcode.spicy.util.featureflags;

import se.coolcode.spicy.utils.settings.Setting;
import se.coolcode.spicy.utils.settings.Settings;

public class DynamicFeatureFlags extends FeatureFlags {

    DynamicFeatureFlags(Settings settings) {
        settings.stream()
        .forEach(this::createFeatureFlag);
    }

    private void createFeatureFlag(Setting<?> setting) {
        
    }
    
}
