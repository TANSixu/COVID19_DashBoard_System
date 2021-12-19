package config;

import java.io.FileInputStream;
import java.util.Properties;

public final class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("./src//main/resources/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getJdbcUrl() {
        return properties.getProperty("jdbcUrl");
    }

    public static String getSQLUsername() {
        return properties.getProperty("username");
    }

    public static String getSQLPassword() {
        return properties.getProperty("password");
    }
}
