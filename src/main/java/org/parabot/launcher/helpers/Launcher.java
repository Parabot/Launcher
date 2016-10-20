package org.parabot.launcher.helpers;

import org.parabot.launcher.Terminal;
import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.io.Downloader;
import org.parabot.launcher.io.Writer;

import java.io.File;
import java.io.IOException;

/**
 * @author JKetelaar
 */
public class Launcher extends Thread {

    @Override
    public void run() {
        if (!new File(Configuration.CLIENT_LOCATION).exists()
                || !VersionHelper.validVersion()) {
            Thread downloadThread = new Thread(new DownloadExecuter());
            downloadThread.start();
            try {
                downloadThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Writer.writeConfiguration();

        try {
            new Terminal().execute();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class DownloadExecuter implements Runnable {
        @Override
        public void run() {
            Downloader.downloadFile(Configuration.DOWNLOAD_BOT, Configuration.CLIENT_LOCATION);
        }
    }
}
