package test.automation.framework.config;

import com.google.common.io.Resources;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public final class ConfigReader {

    private static ConfigReader configReader;

    private JSONObject configJson;

    private ConfigReader() {
        try {
            String configFileContent = Resources.toString(Resources.getResource("config.json"), StandardCharsets.UTF_8);
            configJson = new JSONObject(configFileContent);
        } catch (Exception e) {
            System.out.println("Please provide valid config file: ");
            e.printStackTrace();
        }
    }

    public static ConfigReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    private String readValue(String key) {
        return configJson.getString(key);
    }

    public static String getValue(String key) {
        return getInstance().readValue(key);
    }

    public static String getPlatform() {
        String platform = System.getProperty("platform");

        if (platform == null) {
            platform = getValue("platform");
        }

        return platform;
    }

    public String getHost() {
        return getValue("host");
    }

    public String getEmail() {return getValue("email");}

    public String getPassword() {return getValue("password");}

}
