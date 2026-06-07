package dk.kentjuul.footballtracker.repository;

import dk.kentjuul.footballtracker.db.DatabaseConfig;
import dk.kentjuul.footballtracker.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC-based implementation of {@link TeamRepository} backed by SQLite.
 */
public class JdbcTeamRepository implements TeamRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcTeamRepository.class);

    private final DatabaseConfig db;

    public JdbcTeamRepository(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void save(Team team) {
        String sql = "INSERT OR REPLACE INTO team (id, name, short_name, tla) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, team.id());
            ps.setString(2, team.name());
            ps.setString(3, team.shortName());
            ps.setString(4, team.tla());
            ps.executeUpdate();
            log.debug("Saved team: {}", team.name());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save team: " + team.name(), e);
        }
    }

    @Override
    public void saveAll(List<Team> teams) {
        for (Team team : teams) {
            save(team);
        }
    }

    @Override
    public Optional<Team> findById(long id) {
        String sql = "SELECT id, name, short_name, tla FROM team WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapTeam(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find team by id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Team> findAll() {
        String sql = "SELECT id, name, short_name, tla FROM team";
        List<Team> teams = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                teams.add(mapTeam(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list teams", e);
        }
        return List.copyOf(teams);
    }

    private Team mapTeam(ResultSet rs) throws SQLException {
        return new Team(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("short_name"),
                rs.getString("tla")
        );
    }
}
