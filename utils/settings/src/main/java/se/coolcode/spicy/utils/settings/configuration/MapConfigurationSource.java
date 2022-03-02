package se.coolcode.spicy.utils.settings.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapConfigurationSource implements ConfigurationSource {

    private Map<String, String> properties;

    public MapConfigurationSource() {
        this(new HashMap<>());
    }

    public MapConfigurationSource(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String getValue(String key) {
        return properties.get(key);
    }

    @Override
    public Map<String, String> getValues(Set<String> keys) {
        Map<String, String> result = new HashMap<>();
        result.putAll(properties);
        result.keySet().retainAll(keys);
        return result;
    }
    
}
