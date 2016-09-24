package org.parabot.launcher.web;

import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * A WebUtil class fetches data from an URL
 *
 * @author Everel
 */
public class WebUtil {

    private static JSONParser jsonParser;

    /**
     * Gets buffered reader from string url
     *
     * @param url
     * @return bufferedreader
     */
    public static BufferedReader getReader(final String url) {
        try {
            return getReader(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets BufferedReader from URL
     *
     * @param url
     * @return BufferedReader from URL
     */
    public static BufferedReader getReader(final URL url) {
        return getReader(getConnection(url));
    }

    public static BufferedReader getReader(final URLConnection urlConnection) {
        try {
            return new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    /**
     * Opens a connection
     *
     * @param url
     * @return URLConnection to URL
     */
    public static URLConnection getConnection(final URL url) {
        try {
            final URLConnection con = url.openConnection();
            String agent = "Mozilla/5.0 (Wind0ws NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
            con.setRequestProperty("User-Agent", agent);
            return con;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public static JSONParser getJsonParser() {
        if (jsonParser == null) {
            jsonParser = new JSONParser();
        }
        return jsonParser;
    }
}