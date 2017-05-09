package org.parabot.launcher.models;

import org.json.simple.JSONObject;

/**
 * @author JKetelaar
 */
public class Setting {

    private final String  setting;
    private       boolean enabled;
    /**
     * Defines if the setting should be put before or after the application
     * if true: java -jar application.jar -setting
     * if false: java -jar -setting application.jar
     */
    private boolean applicationArgument = true;

    public Setting(String setting) {
        this.setting = setting;
    }

    public Setting(String setting, boolean applicationArgument) {
        this.setting = setting;
        this.applicationArgument = applicationArgument;
        this.enabled = false;
    }

    public Setting(String setting, boolean applicationArgument, boolean enabled) {
        this.setting = setting;
        this.applicationArgument = applicationArgument;
        this.enabled = enabled;
    }

    public Setting(boolean enabled, String setting) {
        this.enabled = enabled;
        this.setting = setting;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSetting() {
        return setting;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        object.put("enabled", enabled);

        return object;
    }

    public void setFromJSONObject(JSONObject object) {
        this.enabled = (boolean) object.get("enabled");
    }

    public String toCommand() {
        return "-" + setting;
    }

    public boolean isApplicationArgument() {
        return applicationArgument;
    }
}
