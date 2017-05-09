package org.parabot.launcher.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.parabot.api.io.WebUtil;
import org.parabot.api.misc.Version;
import org.parabot.launcher.ControllerImpl;
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

    private ControllerImpl controller;

    public Launcher(ControllerImpl controller){
        this.controller = controller;
    }

    @Override
    public void run() {
        controller.setStatus("Checking if client is up to date", true);
        if (!new File(Configuration.CLIENT_LOCATION).exists() || !VersionHelper.validVersion()) {
            System.out.println(VersionHelper.validVersion());
            controller.setStatus("Updating client (25%)", true);

            try {
                JSONArray  versions = (JSONArray) WebUtil.getJsonParser().parse(WebUtil.getReader(Configuration.GET_BOT_VERSIONS));
                JSONObject version = (JSONObject) versions.get(0);
                VersionHelper.setCurrentVersion(new Version((String) version.get("version")));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

            controller.setStatus("Updating client (50%)", true);

            Thread downloadThread = new Thread(new DownloadExecutor());
            downloadThread.start();
            try {
                downloadThread.join();
            } catch (InterruptedException e) {
                controller.setStatus("Client failed to update", true);
                e.printStackTrace();
            }

            controller.setStatus("Client updated", true);
        }

        Writer.writeConfiguration();
        controller.fillComponentsFromOtherThread();
        controller.setStatus("Starting client", true);

        controller.setReady(3500);

        try {
            new Terminal().execute();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class DownloadExecutor implements Runnable {
        @Override
        public void run() {
            Downloader.downloadFile(Configuration.DOWNLOAD_BOT, Configuration.CLIENT_LOCATION);
        }
    }
}
