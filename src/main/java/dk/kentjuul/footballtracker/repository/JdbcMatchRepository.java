package dk.kentjuul.footballtracker.repository;

import dk.kentjuul.footballtracker.db.DatabaseConfig;
import dk.kentjuul.footballtracker.model.Match;
import dk.kentjuul.footballtracker.model.MatchStatus;
import dk.kentjuul.footballtracker.model.Stage;
import dk.kentjuul.footballtracker.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC-based implementation of {@link MatchRepository} backed by SQLite.
 *
 * <p>All read queries use a JOIN against the {@code team} table to avoid N+1 lookups.
 */
public class JdbcMatchRepository implements MatchRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcMatchRepository.class);

    /** SELECT that fetches a match with its home and away team data in one query. */
    private static final String SELECT_WITH_TEAMS =
            "SELECT m.id, m.stage, m.group_name, m.match_date, m.home_goals, m.away_goals, m.status, "
            + "ht.id AS ht_id, ht.name AS ht_name, ht.short_name AS ht_short_name, ht.tla AS ht_tla, "
            + "at.id AS at_id, at.name AS at_name, at.short_name AS at_short_name, at.tla AS at_tla "
            + "FROM match m "
            + "JOIN team ht ON ht.id = m.home_team_id "
            + "JOIN team at ON at.id = m.away_team_id";

    private final DatabaseConfig db;

    public JdbcMatchRepository(DatabaseConfig db) {
        this.db = db;
    }

    @Override
    public void save(Match match) {
        String sql = "INSERT OR REPLACE INTO match "
                + "(id, stage, group_name, match_date, home_team_id, away_team_id, home_goals, away_goals, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, match.id());
            ps.setString(2, match.stage().name());
            ps.setString(3, match.group());
            ps.setString(4, match.date().toString());
            ps.setLong(5, match.homeTeam().id());
            ps.setLong(6, match.awayTeam().id());
            if (match.homeGoals() != null) {
                ps.setInt(7, match.homeGoals());
            } else {
                ps.setNull(7, Types.INTEGER);
            }
            if (match.awayGoals() != null) {
                ps.setInt(8, match.awayGoals());
            } else {
                ps.setNull(8, Types.INTEGER);
            }
            ps.setString(9, match.status().name());
            ps.executeUpdate();
            log.debug("Saved match: {} vs {}", match.homeTeam().name(), match.awayTeam().name());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save match id: " + match.id(), e);
        }
    }

    @Override
    public void saveAll(List<Match> matches) {
        for (Match match : matches) {
            save(match);
        }
    }

    @Override
    public Optional<Match> findById(long id) {
        String sql = SELECT_WITH_TEAMS + " WHERE m.id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapMatch(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find match by id: " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_WITH_TEAMS)) {
            while (rs.next()) {
                matches.add(mapMatch(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list matches", e);
        }
        return List.copyOf(matches);
    }

    private Match mapMatch(ResultSet rs) throws SQLException {
        Team homeTeam = new Team(
                rs.getLong("ht_id"),
                rs.getString("ht_name"),
                rs.getString("ht_short_name"),
                rs.getString("ht_tla")
        );
        Team awayTeam = new Team(
                rs.getLong("at_id"),
                rs.getString("at_name"),
                rs.getString("at_short_name"),
                rs.getString("at_tla")
        );

        int homeGoalsVal = rs.getInt("home_goals");
        Integer homeGoals = rs.wasNull() ? null : homeGoalsVal;
        int awayGoalsVal = rs.getInt("away_goals");
        Integer awayGoals = rs.wasNull() ? null : awayGoalsVal;

        return new Match(
                rs.getLong("id"),
                Stage.valueOf(rs.getString("stage")),
                rs.getString("group_name"),
                LocalDateTime.parse(rs.getString("match_date")),
                homeTeam,
                awayTeam,
                homeGoals,
                awayGoals,
                MatchStatus.valueOf(rs.getString("status"))
        );
    }
}
