package simtran.core.config;

import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;

/**
 * This class loads configurations from properties file
 *
 * @author simtran
 */
public class ConfigManager {

    private ConfigManager() {
    }

    public static EnvironmentConfiguration envConfig(String target) {
        HashMap<String, String> map = new HashMap<>();
        map.put("env", target);
        return ConfigFactory.create(EnvironmentConfiguration.class, map);
    }

    public static GeneralConfiguration config() {
        return ConfigFactory.create(GeneralConfiguration.class);
    }
}

