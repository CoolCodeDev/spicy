package se.coolcode.spicy.util.featureflags;

import java.util.Set;
import java.util.function.Supplier;

public class ExplicitFeatureFlag extends AbstractFeatureFlag {
    
    private Set<String> ids;

    public ExplicitFeatureFlag(String key, Set<String> ids) {
        super(key);
        this.ids = ids;
    }

    public boolean isActive(String id) {
        return id != null && ids.contains(id);
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
