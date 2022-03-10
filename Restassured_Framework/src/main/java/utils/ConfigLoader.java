package utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("url");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId() {
        String prop = properties.getProperty("client_id");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");
    }

    public String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if (prop != null) return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");
    }
    public String getClientType(){
        String prop = properties.getProperty("client_Type");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_type is not specified in the config.properties file");
    }
    public String getClientToken(){
        String prop = properties.getProperty("client_token");
        if(prop != null) return prop;
        else throw new RuntimeException("property client_token is not specified in the config.properties file");

    }
}