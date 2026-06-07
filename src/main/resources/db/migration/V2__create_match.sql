CREATE TABLE IF NOT EXISTS match (
    id            INTEGER PRIMARY KEY,
    stage         TEXT NOT NULL,
    group_name    TEXT,
    match_date    TEXT NOT NULL,
    home_team_id  INTEGER NOT NULL REFERENCES team(id),
    away_team_id  INTEGER NOT NULL REFERENCES team(id),
    home_goals    INTEGER,
    away_goals    INTEGER,
    status        TEXT NOT NULL
);
