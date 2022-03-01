package se.coolcode.spicy.utils.settings;

import java.util.HashMap;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> properties = new HashMap<>();
        properties.put("feature", "false");
        ConfigurationSource source = new MapConfigSource(properties);
        Settings settings = new Settings("features", source, 3);
        Setting<Boolean> setting = settings.createSetting("feature", false);
        setting.addListener((oldVal, newVal) -> System.out.printf("Value updated from %s to %s.\n", oldVal, newVal));
        
        settings.init();

        Thread.sleep(5000);
        properties.put("feature", "true");
        Thread.sleep(5000);
        settings.destroy();
        System.out.println("Done " + setting.getKey() +" = "+setting.getValue());
    }

    public static class MapConfigSource implements ConfigurationSource {

        private Map<String, String> values;

        public MapConfigSource(Map<String, String> values) {
            this.values = values;
        }

        @Override
        public String getValue(String key) {
            return values.get(key);
        }

    }
}
