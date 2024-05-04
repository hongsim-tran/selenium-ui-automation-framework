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

    public static Configuration config(String target) {
        HashMap<String, String> map = new HashMap<>();
        map.put("env", target);
        return ConfigFactory.create(Configuration.class, map);
    }

    public static Configuration config() {
        return ConfigFactory.create(Configuration.class);
    }
}

