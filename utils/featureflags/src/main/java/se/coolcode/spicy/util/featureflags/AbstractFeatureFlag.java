package se.coolcode.spicy.util.featureflags;

public abstract class AbstractFeatureFlag implements FeatureFlag {

    private String key;

    protected AbstractFeatureFlag(String key) {
        this.key = key;
    }

    public boolean is(String key) {
        return key != null && this.key.equalsIgnoreCase(key);
    }
}
