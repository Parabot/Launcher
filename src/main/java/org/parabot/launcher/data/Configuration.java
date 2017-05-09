package org.parabot.launcher.data;

import org.parabot.api.io.Directories;

import java.io.File;

/**
 * @author EmmaStone
 */
public class Configuration extends org.parabot.api.Configuration {

    public static final String CONFIG_NAME              = "launcher";
    public static final String LAUNCHER_CONFIG_LOCATION = Directories.getSettingsPath() + File.separator + Configuration.CONFIG_NAME + ".json";
    public static final String CLIENT_LOCATION          = Directories.getCachePath() + File.separator + BOT_TITLE + ".jar";
    public static final String GET_BOT_VERSION          = "http://bdn.parabot.org/api/v2/bot/version";
    public static final String GET_LATEST_BOT_VERSION   = "http://v3.bdn.parabot.org/api/bot/list/client?limit=1";
}
