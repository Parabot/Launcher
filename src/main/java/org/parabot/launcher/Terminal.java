package org.parabot.launcher;

import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.helpers.SettingHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author EmmaStone, JKetelaar
 */
public class Terminal {

    public void execute() throws IOException, InterruptedException {
        ArrayList<String> executions = new ArrayList<>();

        executions.add(System.getProperty("java.home") + File.separator + "bin/java");
        executions.add("-jar");

        Collections.addAll(executions, SettingHelper.createJavaCommandLine());

        executions.add(Configuration.CLIENT_LOCATION);

        Collections.addAll(executions, SettingHelper.createApplicationCommandLine());

        Process process = Runtime.getRuntime().exec(executions.toArray(new String[0]));

        process.waitFor();

        InputStream inputStream        = process.getInputStream();
        InputStream processErrorStream = process.getErrorStream();

        byte b[] = new byte[inputStream.available()];
        inputStream.read(b, 0, b.length);

        byte c[] = new byte[processErrorStream.available()];
        processErrorStream.read(c, 0, c.length);
    }
}
