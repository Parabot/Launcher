package org.parabot.launcher.data;

import org.parabot.launcher.utils.CacheUtil;
import org.parabot.launcher.utils.OperatingSystem;

import java.io.File;

/**
 * @author EmmaStone
 */
public class Configuration {

    public static final String CLIENT_NAME = "Parabot";
    public static final String DIRECTORY_LOCATION = System.getProperty("user.home") + (OperatingSystem.getOS() == OperatingSystem.WINDOWS ? "/Documents/" : "") + "/Parabot/";
    public static final String GET_BOT_VERSION = "http://bdn.parabot.org/api/v2/bot/version";
    public static final String GET_CLIENT_URL = "http://bdn.parabot.org/versions/";
    public static final File SETTINGS_LOCATION = new File(CacheUtil.getSettingsPath() + "/settings.json");
    public static final File CLIENT_LOCATION = new File(DIRECTORY_LOCATION + CLIENT_NAME + ".jar");
}
