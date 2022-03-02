package se.coolcode.spicy.utils.settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import se.coolcode.spicy.utils.settings.configuration.ConfigurationSource;

public class Settings {

    private ScheduledExecutorService executor;
    private Set<Setting<?>> settings = new HashSet<>();
    private List<ConfigurationSource> configurationSources = new ArrayList<>();
    private String name;
    private int updateFrequencyInSeconds;


    public Settings(String name, ConfigurationSource source) {
        this(name, source, 30);
    }
    
    public Settings(String name, ConfigurationSource source, int updateFrequencyInSeconds) {
        this.name = name;
        this.updateFrequencyInSeconds = updateFrequencyInSeconds;
        configurationSources.add(source);
    }

    public void init() {
        if (executor == null) {
            ThreadGroup parentThreadGroup = Thread.currentThread().getThreadGroup();
            ThreadGroup threadGroup = new ThreadGroup(parentThreadGroup, "settings-"+name);
            executor = Executors.newSingleThreadScheduledExecutor(runnable -> new Thread(threadGroup, runnable, "-update-thread-1"));
            executor.scheduleAtFixedRate(new SettingsUpdater(settings, configurationSources), 1, updateFrequencyInSeconds, TimeUnit.SECONDS);
        }
    }

    public void destroy() {
        executor.shutdown();
        int timeout = updateFrequencyInSeconds * 2;
        try {
          if (!executor.awaitTermination(timeout, TimeUnit.SECONDS)) {
            executor.shutdownNow();
            if (!executor.awaitTermination(timeout, TimeUnit.SECONDS))
                System.err.println("ScheduledExecutorService did not terminate.");
          }
        } catch (InterruptedException ie) {
          executor.shutdownNow();
          Thread.currentThread().interrupt();
        }
    }

    public Setting<Boolean> createSetting(String key, boolean initValue) {
        BooleanSetting setting = new BooleanSetting(key, initValue);
        settings.add(setting);
        return setting;
    }

    public Setting<Integer> createSetting(String key, int initValue) {
        IntSetting setting = new IntSetting(key, initValue);
        settings.add(setting);
        return setting;
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
