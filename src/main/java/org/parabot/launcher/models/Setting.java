package org.parabot.launcher.models;

import org.json.simple.JSONObject;

/**
 * @author JKetelaar
 */
public class Setting {

    private final String command;
    protected boolean enabled;
    /**
     * Defines if the command should be put before or after the application
     * if true: java -jar application.jar -command
     * if false: java -jar -command application.jar
     */
    protected boolean applicationArgument = true;

    public Setting(String command) {
        this.command = command;
    }

    public Setting(String command, boolean applicationArgument) {
        this.command = command;
        this.applicationArgument = applicationArgument;
    }

    public Setting(String command, boolean applicationArgument, boolean enabled) {
        this.command = command;
        this.applicationArgument = applicationArgument;
        this.enabled = enabled;
    }

    public Setting(boolean enabled, String command) {
        this.enabled = enabled;
        this.command = command;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCommand() {
        return command;
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
        return "-" + command;
    }

    public boolean isApplicationArgument() {
        return applicationArgument;
    }
}
