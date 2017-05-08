package org.parabot.launcher.file;

import org.parabot.launcher.data.Constants;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author EmmaStone
 */
public class Download {
    private final File client = new File(Constants.DIRECTORY_LOCATION + Constants.CLIENT_NAME + ".jar");

    private boolean doesClientExist() {
        return client.exists();
    }

    private String getVersion() {
        return null; //TODO
    }

    public void downloadClient() {
        if (!doesClientExist()) {
            OutputStream out = null;
            URLConnection conn;
            InputStream in = null;
            try {
                URL url = new URL(Constants.CLIENT_URL);
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
            System.out.println("Download complete");
        } else {
            //TODO
        }
    }
}
