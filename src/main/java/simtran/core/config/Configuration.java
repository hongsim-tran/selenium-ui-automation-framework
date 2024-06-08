package simtran.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.apache.logging.log4j.Level;

/**
 * This interface maps properties from config.properties file into Java methods
 *
 * @author simtran
 */
@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties",
        "file:./src/main/resources/config/config.properties"})
public interface Configuration extends Config {

    @Key("${env}.baseurl")
    String baseUrl();

    @Key("${env}.username")
    String username();

    @Key("${env}.password")
    String password();

    @Key("${env}.admin.username")
    String adminUsername();

    @Key("${env}.admin.password")
    String adminPassword();

    @Key("${env}.database.url")
    String databaseUrl();

    @Key("${env}.database.username")
    String databaseUsername();

    @Key("${env}.database.password")
    String databasePassword();

    @Key("${env}.long.timeout")
    int longTimeout();

    @Key("${env}.short.timeout")
    int shortTimeout();

    @Key("${env}.headless")
    Boolean headless();

    @Key("log.level")
    Level logLevel();

    @Key("retry")
    int retry();
}
