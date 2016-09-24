package org.parabot.launcher.data;

/**
 * @author EmmaStone
 */
public class Configuration {

    public static final String CLIENT_NAME = "Parabot";
    public static final String DIRECTORY_LOCATION = System.getProperty("user.home") + "/Parabot/";
    public static final String GET_SETTINGS_FOLDER = DIRECTORY_LOCATION + "/settings/";
    public static final String GET_BOT_VERSION = "http://bdn.parabot.org/api/v2/bot/version";
    public static final String GET_CLIENT_URL = "http://bdn.parabot.org/versions/";
}
