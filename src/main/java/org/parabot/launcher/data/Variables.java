package org.parabot.launcher.data;

/**
 * @author EmmaStone
 */
public class Variables {

    private static boolean noVerify, loadLocal, verbose, debug, login, server, clearCache;
    private static String username, password;

    public static boolean isNoVerifySelected() {
        return noVerify;
    }

    public static boolean isLoadLocalSelected() {
        return loadLocal;
    }

    public static boolean isVerboseSelected() {
        return verbose;
    }

    public static boolean isDebugSelected() {
        return debug;
    }

    public static boolean isLoginSelected() {
        return login;
    }

    public static boolean isServerSelected() {
        return server;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static boolean isClearCache() {
        return clearCache;
    }
}
