package org.parabot.launcher.io;

import org.parabot.api.io.WebUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author EmmaStone
 */
public class Downloader {

    public static void downloadFile(String url, String location) {
        try {
            WebUtil.downloadFile(new URL(url), new File(location), null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
