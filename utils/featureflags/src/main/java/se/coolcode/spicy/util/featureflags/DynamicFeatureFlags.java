package se.coolcode.spicy.util.featureflags;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import se.coolcode.spicy.utils.logger.Logger;
import se.coolcode.spicy.utils.logger.LoggerFactory;
import se.coolcode.spicy.utils.settings.Setting;
import se.coolcode.spicy.utils.settings.Settings;

public class DynamicFeatureFlags extends FixedFeatureFlags {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicFeatureFlags.class);

    DynamicFeatureFlags(Settings settings) {
        settings.stream()
        .forEach(this::createFeatureFlag);
    }

    @SuppressWarnings("unchecked")
    private void createFeatureFlag(Setting<?> setting) {
        Class<?> type = setting.getType();
        if (String.class.equals(type)) {
            Setting<String> stringSetting = (Setting<String>) setting;
            this.createExplicit(stringSetting.getKey(), Stream.of(stringSetting.getValue().split(","))
            //TODO: add listener that updates the value on the feature flag when the setting changes.
            .map(String::trim)
            .collect(Collectors.toSet()));
        } else if (Integer.class.equals(type)) {
            Setting<Integer> intSetting = (Setting<Integer>) setting;
            this.createCanary(intSetting.getKey(), intSetting.getValue());
        } else if (Boolean.class.equals(type)) {
            Setting<Boolean> boolSetting = (Setting<Boolean>) setting;
            this.createBinary(boolSetting.getKey(), boolSetting.getValue());
        } else {
            LOGGER.warn(String.format("Ignoring setting %s. Can't create featureflag from type %s.", setting.getKey(), setting.getType()));
        }
    }
    
}
