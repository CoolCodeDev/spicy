package se.coolcode.spicy.util.featureflags;

import java.util.function.Supplier;

public class BinaryFeatureFlag extends AbstractFeatureFlag {
    
    private boolean active;

    public BinaryFeatureFlag(String key, boolean active) {
        super(key);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public <T> T get(Supplier<T> active, Supplier<T> inactive) {
        return isActive() ? active.get() : inactive.get();
    }

    public void run(Runnable active, Runnable inactive) {
        if (isActive()) {
            active.run();
        } else {
            inactive.run();
        }
    }
}
