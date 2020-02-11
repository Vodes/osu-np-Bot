package cf.vodes.osubot.options.optiontypes;

import cf.vodes.osubot.options.Option;
import cf.vodes.osubot.options.OptionManager;

public class OptionString extends Option {

    public String value;

    public OptionString(String name, String option) {
        super(name, EnumOptionTypes.String);
        this.value = option;
    }

    public OptionString(String name, String option, String category) {
        super(name, EnumOptionTypes.String);
        this.value = option;
        this.setCategory(category);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}