package org.parabot.launcher;

import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.data.Variables;

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
        if (Variables.isNoVerifySelected())
            executions.add("-noverify");
        executions.add(Configuration.DIRECTORY_LOCATION + Configuration.CLIENT_NAME + ".jar");
        if (Variables.isLoadLocalSelected()) {
            executions.add("-loadlocal");
        }
        if (Variables.isVerboseSelected()) {
            executions.add("-verbose");
        }
        if (Variables.isDebugSelected()) {
            executions.add("-debug");
        }
        if (Variables.isLoginSelected()) {
            executions.add("-login " + Variables.getUsername() + " " + Variables.getPassword());
        }
        if (Variables.isServerSelected()) {
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
