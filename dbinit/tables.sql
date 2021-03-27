CREATE TABLE "team" (
  "team_code" varchar PRIMARY KEY,
  "team_name" varchar
);

CREATE TABLE "user" (
  "username" varchar PRIMARY KEY,
  "password" varchar,
  "full_name" varchar,
  "fav_team" varchar,
  FOREIGN KEY ("fav_team") REFERENCES "team" ("team_code")
);

CREATE TABLE "tournament" (
  "tournament_year" int PRIMARY KEY,
  "winning_team" varchar,
  "runner_up_team" varchar,
  "semi_finalists" varchar[],
  "orange_cap" varchar,
  "purple_cap" varchar,
  FOREIGN KEY ("winning_team") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("runner_up_team") REFERENCES "team" ("team_code")
);

CREATE TABLE "tournament_prediction" (
  "username" varchar,
  "tournament_year" int,
  "winning_team" varchar,
  "semi_finalists" varchar[],
  "orange_caps" varchar[],
  "purple_caps" varchar[],
  PRIMARY KEY ("username", "tournament_year"),
  FOREIGN KEY ("username") REFERENCES "user" ("username"),
  FOREIGN KEY ("tournament_year") REFERENCES "tournament" ("tournament_year"),
  FOREIGN KEY ("winning_team") REFERENCES "team" ("team_code")
);

CREATE TABLE "match" (
  "match_id" int UNIQUE,
  "tournament_year" int,
  "team_win" varchar,
  "team1_id" varchar,
  "team2_id" varchar,
  "team1_score" int,
  "team2_score" int,
  "wickets" int,
  PRIMARY KEY ("match_id", "tournament_year"),
  FOREIGN KEY ("tournament_year") REFERENCES "tournament" ("tournament_year"),
  FOREIGN KEY ("team_win") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("team1_id") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("team2_id") REFERENCES "team" ("team_code")
);

CREATE TABLE "match_prediction" (
  "username" varchar,
  "match_id" int,
  "team_win" varchar,
  "team1_high" int,
  "team1_low" int,
  "team2_high" int,
  "team2_low" int,
  "wickets" int,
  PRIMARY KEY ("username", "match_id"),
  FOREIGN KEY ("match_id") REFERENCES "match" ("match_id"),
  FOREIGN KEY ("team_win") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("username") REFERENCES "user" ("username")
);

CREATE TABLE "points" (
  "username" varchar,
  "match_id" int,
  "points" int,
  PRIMARY KEY ("username", "match_id"),
  FOREIGN KEY ("username") REFERENCES "user" ("username"),
  FOREIGN KEY ("match_id") REFERENCES "match" ("match_id")
);
