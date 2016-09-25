package org.parabot.launcher.updater;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.parabot.launcher.utils.CacheUtil;
import org.parabot.launcher.Terminal;
import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.data.Settings;
import org.parabot.launcher.utils.WebUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author EmmaStone
 */
public class VersionManager {
    private UpdateManager download;
    private SettingsManager settingsManager;
    private CacheUtil cacheUtil;

    public String getCurrentVersion() {
        String version = null;
        BufferedReader br = WebUtil.getReader(Configuration.GET_BOT_VERSION);

        try {
            if (br != null) {
                JSONObject object = (JSONObject) WebUtil.getJsonParser().parse(br);
                version = (String) object.get("result");
            }
        } catch (NumberFormatException | IOException | ParseException e) {
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

        return version;
    }

    public void compareVersions() throws IOException, ParseException, InterruptedException {
        if (Configuration.SETTINGS_LOCATION.exists()) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(Configuration.SETTINGS_LOCATION));

            JSONObject jsonObject = (JSONObject) obj;
            String version = (String) jsonObject.get("version");

            if (getCurrentVersion().equals(version)) {
                System.out.println("Parabot is on the latest version!");
                if (Settings.isClearCache()) {
                    if (cacheUtil == null) {
                        System.out.println("Clearing cacheUtil.");
                        cacheUtil = new CacheUtil();
                        cacheUtil.clearCache();
                    }
                }

                System.out.println("Launching Parabot.");
                Terminal terminal = new Terminal();
                terminal.execute();
            } else {
                if (download == null) {
                    download = new UpdateManager();
                    if (!Configuration.CLIENT_LOCATION.exists()) {
                        System.out.println("Downloading Parabot.");
                        download.downloadClient();
                    } else {
                        System.out.println("Deleting the old version.");
                        Configuration.CLIENT_LOCATION.delete();

                        System.out.println("UpdateManager Parabot.");
                        download.downloadClient();
                    }
                }
            }
        } else {
            System.out.println("Creating settingsManager updater.");

            if (settingsManager == null) {
                settingsManager = new SettingsManager(this);
                settingsManager.createSettingsFile();
            }
        }
    }
}
