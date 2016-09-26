package org.parabot.launcher.models;

import org.json.simple.JSONObject;

/**
 * @author JKetelaar
 */
public class ServerSetting extends Setting {

    private String server;

    public ServerSetting() {
        super("server");
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject object = super.toJSON();
        object.put("server", server);

        return object;
    }

    @Override
    public void setFromJSONObject(JSONObject object) {
        super.setFromJSONObject(object);
        this.server = (String) object.get("server");
    }

    @Override
    public String toCommand() {
        return super.toCommand() + " " + server;
    }
}
