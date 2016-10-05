package org.parabot.launcher;

import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.io.Downloader;

import java.io.File;
import java.io.IOException;

/**
 * @author JKetelaar, EmmaStone, Fryslan, Everel
 */
public class Core {

    public static void main(String[] args) {
        Terminal terminal = new Terminal();

        System.out.println(System.getProperty("java.home"));

        try {
            if (!new File(Configuration.CLIENT_LOCATION).exists()) {
                Downloader.downloadFile(Configuration.DOWNLOAD_BOT, Configuration.CLIENT_LOCATION);
            }
            terminal.execute();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}