package org.parabot.launcher.updater;

import org.json.simple.JSONObject;
import org.parabot.launcher.data.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author EmmaStone
 */
public class SettingsManager {
    private VersionManager version;

    public SettingsManager(VersionManager version) {
        this.version = version;
    }

    public void createSettingsFile() throws IOException {
        Configuration.SETTINGS_LOCATION.createNewFile();
        FileWriter file = new FileWriter(Configuration.SETTINGS_LOCATION);

        writeSettings();

        file.flush();
        file.close();

        System.out.println("SettingsManager created!");
    }

    public void writeSettings() throws IOException {
        JSONObject versionObj = new JSONObject();
        versionObj.put("version", version.getCurrentVersion());

        //todo Add list for UI settings

        FileWriter file = new FileWriter(Configuration.SETTINGS_LOCATION);
        file.write(versionObj.toJSONString());
        file.flush();
        file.close();

        System.out.println("SettingsManager written!");
    }
}
