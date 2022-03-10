package utils;


import java.util.Properties;

public class DataLoader {

    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties  = PropertyUtils.propertyLoader("url");}

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getUserListId(){
        String prop = properties.getProperty("get_user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property get_user_id is not specified in the data.properties file");

    }
    public String updateUserListId(){
        String prop = properties.getProperty("update_user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("property update_user_id is not specified in the data.properties file");

    }
}
