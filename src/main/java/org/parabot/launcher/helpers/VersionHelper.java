package org.parabot.launcher.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.parabot.api.io.Directories;
import org.parabot.api.io.WebUtil;
import org.parabot.api.misc.Version;
import org.parabot.launcher.data.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author EmmaStone
 */
public class VersionHelper {

    private static Version currentVersion;

    public static String getCurrentVersion() {
        return currentVersion != null ? currentVersion.get() : "";
    }

    public static void setCurrentVersion(Version currentVersion) {
        VersionHelper.currentVersion = currentVersion;
    }

    public static boolean validVersion() {
        String url = String.format(Configuration.COMPARE_VERSION_URL, "client", getCurrentVersion());

        BufferedReader br = WebUtil.getReader(url);
        try {
            if (br != null) {
                JSONObject object = (JSONObject) WebUtil.getJsonParser().parse(br);
                boolean latest = Boolean.parseBoolean((String) object.get("result"));
                if (!latest) {
                    Directories.clearCache();
                }
                return latest;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public static String getLatestClient() {
        try {
            JSONObject object = (JSONObject) WebUtil.getJsonParser().parse(WebUtil.getContents(Configuration.GET_BOT_VERSION));

            return (String) object.get("version");
        } catch (MalformedURLException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
