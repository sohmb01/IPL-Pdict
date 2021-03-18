CREATE TABLE "user" (
  "user_id" SERIAL PRIMARY KEY,
  "username" varchar,
  "password" varchar,
  "full_name" varchar,
  "fav_team" varchar
);

CREATE TABLE "prediction" (
  "user_id" int,
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
  PRIMARY KEY ("user_id", "tournament_year"),
  FOREIGN KEY ("user_id") REFERENCES "user" ("user_id")
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
  "user_id" int,
  "match_id" int,
  "team_win" varchar,
  "team1_high" int,
  "team1_low" int,
  "team2_high" int,
  "team2_low" int,
  "wickets" int,
  PRIMARY KEY ("user_id", "match_id"),
  FOREIGN KEY ("team_win") REFERENCES "team" ("team_code"),
  FOREIGN KEY ("user_id") REFERENCES "user" ("user_id")
);

CREATE TABLE "points" (
  "user_id" int,
  "match_id" int,
  "points" int,
  PRIMARY KEY ("user_id", "match_id"),
  FOREIGN KEY ("user_id") REFERENCES "user" ("user_id"),
  FOREIGN KEY ("match_id") REFERENCES "match" ("match_id")
);
