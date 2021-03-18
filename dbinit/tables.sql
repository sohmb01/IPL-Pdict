CREATE TABLE "users" (
  "user_id" SERIAL PRIMARY KEY,
  "username" varchar,
  "password" varchar,
  "full_name" varchar,
  "fav_team" varchar
);

CREATE TABLE "predictions" (
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
  PRIMARY KEY ("user_id", "tournament_year")
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

CREATE TABLE "teams" (
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
  PRIMARY KEY ("match_id", "tournament_year")
);

CREATE TABLE "matchwise_predictions" (
  "user_id" int,
  "match_id" int,
  "team_win" varchar,
  "team1_high" int,
  "team1_low" int,
  "team2_high" int,
  "team2_low" int,
  "wickets" int,
  PRIMARY KEY ("user_id", "match_id")
);

CREATE TABLE "points" (
  "user_id" int,
  "match_id" int,
  "points" int,
  PRIMARY KEY ("user_id", "match_id")
);

ALTER TABLE "predictions" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");

ALTER TABLE "match" ADD FOREIGN KEY ("team_win") REFERENCES "teams" ("team_code");

ALTER TABLE "match" ADD FOREIGN KEY ("team1_id") REFERENCES "teams" ("team_code");

ALTER TABLE "match" ADD FOREIGN KEY ("team2_id") REFERENCES "teams" ("team_code");

ALTER TABLE "matchwise_predictions" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");

ALTER TABLE "matchwise_predictions" ADD FOREIGN KEY ("team_win") REFERENCES "teams" ("team_code");

ALTER TABLE "points" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");

ALTER TABLE "points" ADD FOREIGN KEY ("match_id") REFERENCES "match" ("match_id");

