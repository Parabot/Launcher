package org.parabot.launcher.helpers;

import org.json.simple.JSONObject;
import org.parabot.launcher.models.ServerSetting;
import org.parabot.launcher.models.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JKetelaar
 */
public class SettingHelper {

    private static final List<Setting> settings;

    static {
        settings = new ArrayList<>();
        createSettings();
    }

    private static void createSettings() {
        settings.add(new Setting("noverify", false));
        settings.add(new Setting("loadlocal"));
        settings.add(new Setting("verbose"));
        settings.add(new Setting("debug"));
        settings.add(new ServerSetting());
    }

    public static List<Setting> getSettings() {
        return settings;
    }

    public static Setting getSettingByCommand(String command) {
        for (Setting setting : settings) {
            if (setting.getSetting().equals(command)) {
                return setting;
            }
        }

        return null;
    }

    public static JSONObject createJSONObjects() {
        JSONObject object = new JSONObject();
        for (Setting setting : settings) {
            if (setting.isEnabled()) {
                object.put(setting.getSetting(), setting.toJSON());
            }
        }

        return object;
    }

    public static String[] createApplicationCommandLine() {
        ArrayList<String> total = new ArrayList<>();
        for (Setting setting : settings) {
            if (setting.isEnabled() && setting.isApplicationArgument()) {
                total.add(setting.toCommand());
            }
        }

        return total.toArray(new String[0]);
    }

    public static String[] createJavaCommandLine() {
        ArrayList<String> total = new ArrayList<>();
        for (Setting setting : settings) {
            if (setting.isEnabled() && !setting.isApplicationArgument()) {
                total.add(setting.toCommand());
            }
        }

        return total.toArray(new String[0]);
    }
}
