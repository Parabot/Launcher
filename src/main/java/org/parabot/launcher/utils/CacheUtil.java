package org.parabot.launcher.utils;

import org.parabot.launcher.data.Configuration;

import java.io.File;

/**
 * @author Everel, JKetelaar
 */
public class CacheUtil {

    /**
     * Returns the Parabot cache folder.
     *
     * @return
     */
    public static File getCachePath() {
        return new File(Configuration.DIRECTORY_LOCATION + "/cache/");
    }

    /**
     * Returns the Parabot settings folder.
     *
     * @return
     */
    public static File getSettingsPath() {
        return new File(Configuration.DIRECTORY_LOCATION + "/settings/");
    }

    public void clearCache() {
        File[] cache = getCachePath().listFiles();

        if (cache != null) {
            for (File f : cache) {
                long remove = 0;
                if (f != null && System.currentTimeMillis() / 1000 - f.lastModified() / 1000 > remove) {
                    f.delete();
                }
            }
        }
    }
}
