package org.parabot.launcher.data;

import org.parabot.api.io.Directories;

import java.io.File;

/**
 * @author EmmaStone
 */
public class Configuration extends org.parabot.api.Configuration {
    public static final String CONFIG_NAME = "launcher";
    public static final String LAUNCHER_CONFIG_LOCATION = Directories.getSettingsPath() + File.separator + Configuration.CONFIG_NAME + ".json";
    public static final String CLIENT_LOCATION = Directories.getCachePath() + BOT_TITLE + ".jar";
}
