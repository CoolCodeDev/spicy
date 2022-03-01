package se.coolcode.spicy.utils.settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Settings {

    private ScheduledExecutorService executor;
    private Set<Setting<?>> settings = new HashSet<>();
    private List<ConfigurationSource> configurationSources = new ArrayList<>();


    public Settings(String name, ConfigurationSource source) {
        initExecutor(name);
    }

    private void initExecutor(String name) {
        if (executor == null) {
            ThreadGroup threadGroup = new ThreadGroup("settings-"+name);
            executor = Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(threadGroup, runnable, "-update-thread-1"));
            executor.scheduleAtFixedRate(new SettingsUpdater(settings, configurationSources), 1, 30, TimeUnit.SECONDS);
        }
    }

    public Settings createSetting(String key, boolean initValue) {
        settings.add(new BooleanSetting(key, initValue));
        return this;
    }

    public Stream<Setting<?>> stream() {
        return settings.stream();
    }

    private static class SettingsUpdater implements Runnable {

        private Set<Setting<?>> settings;
        private List<ConfigurationSource> configurationSources;

        public SettingsUpdater(Set<Setting<?>> settings, List<ConfigurationSource> configurationSources) {
            this.settings = settings;
            this.configurationSources = configurationSources;
        }

        @Override
        public void run() {
            for (Setting<?> setting : settings) {
                for (ConfigurationSource configurationSource : configurationSources) {
                    String value = configurationSource.getValue(setting.getKey());
                    setting.updateValue(value);
                }
            }
        }

    }
    
}
