package se.coolcode.spicy.util.featureflags;

import java.util.function.Supplier;

public class CanaryFeatureFlag extends AbstractFeatureFlag {

    private int percent;

    public CanaryFeatureFlag(String key, int percent) {
        super(key);
        this.percent = percent;
    }

    public boolean isActive(String id) {
        return id != null && Math.abs(id.hashCode() % 100) < percent;
    }

    public <T> T get(String id, Supplier<T> active, Supplier<T> inactive) {
        return isActive(id) ? active.get() : inactive.get();
    }

    public void run(String id, Runnable active, Runnable inactive) {
        if (isActive(id)) {
            active.run();
        } else {
            inactive.run();
        }
    }
}
