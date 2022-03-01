package se.coolcode.spicy.utils.settings;

public class BooleanSetting extends AbstractSetting<Boolean> {

    BooleanSetting(String key, Boolean value) {
        super(key, value, Boolean.TYPE);
    }

    @Override
    protected Boolean parse(String newValue) {
        return Boolean.parseBoolean(newValue);
    }
    
}
