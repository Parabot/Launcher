package org.parabot.launcher.io;

import org.json.simple.JSONObject;
import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.helpers.SettingHelper;
import org.parabot.launcher.helpers.VersionHelper;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author JKetelaar
 */
public class Writer {

    public static void writeConfiguration() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commands", SettingHelper.createJSONObjects());
            jsonObject.put("version", VersionHelper.getCurrentVersion());

            FileWriter fileWriter = new FileWriter(Configuration.LAUNCHER_CONFIG_LOCATION);

            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
