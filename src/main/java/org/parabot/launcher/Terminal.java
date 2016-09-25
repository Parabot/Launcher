package org.parabot.launcher;

import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.data.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author EmmaStone, JKetelaar
 */
public class Terminal {

    public void execute() throws IOException, InterruptedException {
        ArrayList<String> executions = new ArrayList<>();

        executions.add("java");
        executions.add("-jar");
        if (Settings.isNoVerifySelected()) {
            executions.add("-noverify");
        }
        executions.add(Configuration.DIRECTORY_LOCATION + Configuration.CLIENT_NAME + ".jar");
        if (Settings.isLoadLocalSelected()) {
            executions.add("-loadlocal");
        }
        if (Settings.isVerboseSelected()) {
            executions.add("-verbose");
        }
        if (Settings.isDebugSelected()) {
            executions.add("-debug");
        }
        if (Settings.isLoginSelected()) {
            executions.add("-login " + Settings.getUsername() + " " + Settings.getPassword());
        }
        if (Settings.isServerSelected()) {
            executions.add("-server "); // TODO: 25/09/16
        }

        Process process = Runtime.getRuntime().exec(executions.toArray(new String[0]));

        process.waitFor();

        InputStream inputStream = process.getInputStream();
        InputStream processErrorStream = process.getErrorStream();

        byte b[] = new byte[inputStream.available()];
        inputStream.read(b, 0, b.length);

        byte c[] = new byte[processErrorStream.available()];
        processErrorStream.read(c, 0, c.length);
    }
}
