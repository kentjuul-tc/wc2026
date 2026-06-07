package dk.kentjuul.footballtracker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Centralised application configuration.
 *
 * <p>The API key is resolved in the following priority order:
 * <ol>
 *   <li>Environment variable {@code FOOTBALL_API_KEY}</li>
 *   <li>Property {@code football.api.key} in {@code application.properties}</li>
 * </ol>
 *
 * <p>The base URL defaults to {@code https://api.football-data.org/v4} and can
 * be overridden via the property {@code football.api.base-url}.
 */
public class ApplicationConfig {

    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    private static final String ENV_API_KEY = "FOOTBALL_API_KEY";
    private static final String PROP_API_KEY = "football.api.key";
    private static final String PROP_BASE_URL = "football.api.base-url";
    private static final String DEFAULT_BASE_URL = "https://api.football-data.org/v4";
    private static final String PROPERTIES_FILE = "/application.properties";

    private final String apiKey;
    private final String baseUrl;

    public ApplicationConfig() {
        Properties props = loadProperties();
        this.apiKey = resolveApiKey(props);
        this.baseUrl = props.getProperty(PROP_BASE_URL, DEFAULT_BASE_URL);
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream is = getClass().getResourceAsStream(PROPERTIES_FILE)) {
            if (is != null) {
                props.load(is);
            } else {
                log.warn("'{}' not found on classpath – using defaults", PROPERTIES_FILE);
            }
        } catch (IOException e) {
            log.warn("Could not read '{}': {}", PROPERTIES_FILE, e.getMessage());
        }
        return props;
    }

    private String resolveApiKey(Properties props) {
        String envKey = System.getenv(ENV_API_KEY);
        if (envKey != null && !envKey.isBlank()) {
            log.debug("API key loaded from environment variable '{}'", ENV_API_KEY);
            return envKey;
        }
        String propKey = props.getProperty(PROP_API_KEY, "");
        if (!propKey.isBlank()) {
            log.debug("API key loaded from '{}'", PROPERTIES_FILE);
            return propKey;
        }
        log.warn("No API key configured. Set environment variable '{}' or property '{}'",
                ENV_API_KEY, PROP_API_KEY);
        return "";
    }

    /** Returns the football-data.org API key. */
    public String getApiKey() {
        return apiKey;
    }

    /** Returns the base URL for the football-data.org API. */
    public String getBaseUrl() {
        return baseUrl;
    }
}
