package se.coolcode.spicy.utils.settings;

public class StringSetting extends AbstractSetting<String> {

    public StringSetting(String key, String value) {
        super(key, value, String.class);
    }

    @Override
    protected String parse(String newValue) {
        return newValue;
    }
    
}
