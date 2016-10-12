package org.parabot.launcher.io;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.parabot.api.io.WebUtil;
import org.parabot.api.misc.Version;
import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.helpers.SettingHelper;
import org.parabot.launcher.helpers.VersionHelper;
import org.parabot.launcher.models.Setting;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author JKetelaar
 */
public class Reader {

    public static void parseConfiguration() {
        if (new File(Configuration.LAUNCHER_CONFIG_LOCATION).exists()) {
            try {
                Object object = WebUtil.getJsonParser().parse(new FileReader(Configuration.LAUNCHER_CONFIG_LOCATION));
                JSONObject jsonObject = (JSONObject) object;

                if (jsonObject.containsKey("version")) {
                    String version = (String) jsonObject.get("version");
                    VersionHelper.setCurrentVersion(new Version(version));
                }

                for (Object keyObject : ((JSONObject) jsonObject.get("commands")).keySet()) {
                    String key = (String) keyObject;
                    JSONObject value = (JSONObject) ((JSONObject) jsonObject.get("commands")).get(key);

                    Setting setting;
                    if ((setting = SettingHelper.getSettingByCommand(key)) != null) {
                        setting.setFromJSONObject(value);
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
