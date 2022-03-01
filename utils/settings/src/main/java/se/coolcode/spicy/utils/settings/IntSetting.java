package se.coolcode.spicy.utils.settings;

public class IntSetting extends AbstractSetting<Integer> {

    IntSetting(String key, Integer value) {
        super(key, value, Integer.TYPE);
    }

    @Override
    protected Integer parse(String newValue) {
        return newValue == null ? null : Integer.parseInt(newValue);
    }

}
