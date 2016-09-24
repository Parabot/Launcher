package org.parabot.launcher.file;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.web.WebUtil;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author EmmaStone
 */
public class FileUtil {
    private final File client = new File(Configuration.DIRECTORY_LOCATION + Configuration.CLIENT_NAME + ".jar");
    private final File settings = new File(Configuration.DIRECTORY_LOCATION + Configuration.GET_SETTINGS_FOLDER + "settings.json");

    private String getCurrentVersion() {
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

    private void downloadClient() throws IOException {
        OutputStream out = null;
        URLConnection conn;
        InputStream in = null;
        try {
            URL url = new URL(Configuration.GET_CLIENT_URL);
            out = new BufferedOutputStream(new FileOutputStream(client));
            conn = url.openConnection();
            in = conn.getInputStream();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("FileUtil complete");
    }

    public void compareVersions() throws IOException, ParseException {
        System.out.println(settings);
        if (settings.exists()) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(settings));

            JSONObject jsonObject = (JSONObject) obj;

            String version = (String) jsonObject.get("version");

            if (getCurrentVersion().equals(version))
                System.out.println("Parabot is on the latest version!");
            else
                downloadClient();
        } else {
            createSettingsFile();
        }
    }

    private void createSettingsFile() throws IOException {
        settings.createNewFile();

        JSONObject object = new JSONObject();
        object.put("version", getCurrentVersion());
        FileWriter file = new FileWriter(settings);
        file.write(object.toJSONString());
        file.flush();
        file.close();
    }
}
