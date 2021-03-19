CREATE TABLE "user" (
  "username" varchar PRIMARY KEY UNIQUE,
  "password" varchar,
  "full_name" varchar,
  "fav_team" varchar
);

CREATE TABLE "prediction" (
  "username" varchar UNIQUE,
  "tournament_year" int,
  "orange_cap1" varchar,
  "orange_cap2" varchar,
  "orange_cap3" varchar,
  "purple_cap1" varchar,
  "purple_cap2" varchar,
  "purple_cap3" varchar,
  "sf1" varchar,
  "sf2" varchar,
  "sf3" varchar,
  "sf4" varchar,
  PRIMARY KEY ("username", "tournament_year"),
  FOREIGN KEY ("username") REFERENCES "user" ("username")
);

CREATE TABLE "tournament_result" (
  "tournament_year" int PRIMARY KEY,
  "orange_cap" varchar,
  "purple_cap" varchar,
  "sf1" varchar,
  "sf2" varchar,
  "sf3" varchar,
  "sf4" varchar
);

CREATE TABLE "team" (
  "team_code" varchar PRIMARY KEY,
  "team_name" varchar
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
  FOREIGN KEY ("team_win") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("team2_id") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("team1_id") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("team_win") REFERENCES "team" ("team_code")
);

CREATE TABLE "matchwise_prediction" (
  "username" varchar UNIQUE,
  "match_id" int,
  "team_win" varchar,
  "team1_high" int,
  "team1_low" int,
  "team2_high" int,
  "team2_low" int,
  "wickets" int,
  PRIMARY KEY ("username", "match_id"),
  FOREIGN KEY ("team_win") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("username") REFERENCES "user" ("username")
);

CREATE TABLE "points" (
  "username" varchar UNIQUE,
  "match_id" int,
  "points" int,
  PRIMARY KEY ("username", "match_id"),
  FOREIGN KEY ("username") REFERENCES "user" ("username"),
  FOREIGN KEY ("match_id") REFERENCES "match" ("match_id")
);
