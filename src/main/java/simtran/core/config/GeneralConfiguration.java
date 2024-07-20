package simtran.core.config;


import org.aeonbits.owner.Config;
import org.apache.logging.log4j.Level;

/**
 * This interface maps properties from general.properties file into Java methods
 *
 * @author simtran
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "file:./src/main/resources/config/general.properties"})
public interface GeneralConfiguration extends Config {
    @Key("headless")
    Boolean headless();

    @Key("log.level")
    Level logLevel();

    @Key("retry")
    int retry();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();
}
