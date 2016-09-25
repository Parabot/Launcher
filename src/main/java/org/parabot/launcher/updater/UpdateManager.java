package org.parabot.launcher.updater;

import org.parabot.launcher.data.Configuration;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author EmmaStone
 */
public class UpdateManager {

    public void downloadClient() throws IOException {
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new URL(Configuration.GET_CLIENT_URL).openStream());
            fileOutputStream = new FileOutputStream(Configuration.DIRECTORY_LOCATION + Configuration.CLIENT_NAME + ".jar");

            final byte data[] = new byte[1024];
            int count;
            while ((count = bufferedInputStream.read(data, 0, 1024)) != -1) {
                fileOutputStream.write(data, 0, count);
            }
        } finally {
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }

        System.out.println("UpdateManager complete");
    }
}
