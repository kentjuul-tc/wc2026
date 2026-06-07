package dk.kentjuul.footballtracker.db;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Establishes and manages an SQLite database connection and applies Flyway migrations.
 *
 * <p>A "keeper" connection is held open for the lifetime of this object so that
 * named in-memory SQLite databases (e.g. {@code jdbc:sqlite:file:name?mode=memory&cache=shared})
 * are not automatically destroyed when all other connections close.
 *
 * <p>Call {@link #close()} when the database is no longer needed (e.g. at end of tests).
 */
public class DatabaseConfig implements Closeable {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

    private final String jdbcUrl;
    private final Connection keepAlive;

    /**
     * Creates a {@code DatabaseConfig} for the given JDBC URL and immediately
     * runs any pending Flyway migrations.
     *
     * @param jdbcUrl the JDBC URL for the SQLite database
     *                (e.g. {@code jdbc:sqlite:wc2026.db} or
     *                {@code jdbc:sqlite:file:test?mode=memory&cache=shared})
     */
    public DatabaseConfig(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        try {
            this.keepAlive = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to open keeper connection to: " + jdbcUrl, e);
        }
        migrate();
    }

    private void migrate() {
        log.info("Running Flyway migrations on '{}'", jdbcUrl);
        Flyway flyway = Flyway.configure()
                .dataSource(jdbcUrl, null, null)
                .locations("classpath:db/migration")
                .load();
        flyway.migrate();
    }

    /**
     * Opens and returns a new {@link Connection} to the configured SQLite database.
     *
     * @return a fresh JDBC connection
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl);
    }

    /**
     * Closes the keeper connection, allowing any in-memory database to be released.
     */
    @Override
    public void close() {
        try {
            keepAlive.close();
        } catch (SQLException e) {
            log.warn("Error closing keeper connection: {}", e.getMessage());
        }
    }
}
