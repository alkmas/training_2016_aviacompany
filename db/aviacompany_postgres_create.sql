CREATE SCHEMA public;

CREATE TABLE "employee" (
	"id" serial NOT NULL,
	"first_name" character varying(256) NOT NULL,
	"last_name" character varying(256) NOT NULL,
	"birthday" DATE,
	"job_title_id" bigint,
	UNIQUE("first_name", "last_name", "birthday"),
	CONSTRAINT employee_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "job_title" (
	"id" serial NOT NULL,
	"name" character varying(256) NOT NULL UNIQUE,
	CONSTRAINT job_title_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "flight" (
	"id" serial NOT NULL,
	"name" character varying(20) NOT NULL UNIQUE,
	"airport_src_id" bigint NOT NULL,
	"airport_dst_id" bigint NOT NULL,
	"departure_time" TIME NOT NULL,
	"arrival_time" TIME NOT NULL,
	CONSTRAINT flight_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "flight_2_team" (
	"id" serial NOT NULL,
	"flight_id" bigint NOT NULL,
	"team_id" bigint NOT NULL,
	"departure" DATE NOT NULL,
	UNIQUE("team_id", "departure"), 
	CONSTRAINT flight_2_team_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "airport" (
	"id" serial NOT NULL,
	"name" character varying(256) NOT NULL UNIQUE,
	CONSTRAINT airport_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "flight_days" (
	"id" bigint NOT NULL UNIQUE,
	"day1" BOOLEAN,
	"day2" BOOLEAN,
	"day3" BOOLEAN,
	"day4" BOOLEAN,
	"day5" BOOLEAN,
	"day6" BOOLEAN,
	"day7" BOOLEAN,
	CONSTRAINT flight_days_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "team" (
	"id" serial NOT NULL,
	"name" character varying(256) NOT NULL UNIQUE,
	CONSTRAINT team_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "team_2_employee" (
	"team_id" bigint NOT NULL,
	"employee_id" bigint NOT NULL,
	UNIQUE("team_id", "employee_id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "employee" ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("job_title_id") REFERENCES "job_title"("id");


ALTER TABLE "flight" ADD CONSTRAINT "flight_fk0" FOREIGN KEY ("airport_src_id") REFERENCES "airport"("id");
ALTER TABLE "flight" ADD CONSTRAINT "flight_fk1" FOREIGN KEY ("airport_dst_id") REFERENCES "airport"("id");

ALTER TABLE "flight_2_team" ADD CONSTRAINT "flight_2_team_fk0" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");
ALTER TABLE "flight_2_team" ADD CONSTRAINT "flight_2_team_fk1" FOREIGN KEY ("team_id") REFERENCES "team"("id");


ALTER TABLE "flight_days" ADD CONSTRAINT "flight_days_fk0" FOREIGN KEY ("id") REFERENCES "flight"("id");


ALTER TABLE "team_2_employee" ADD CONSTRAINT "team_2_employee_fk0" FOREIGN KEY ("team_id") REFERENCES "team"("id");
ALTER TABLE "team_2_employee" ADD CONSTRAINT "team_2_employee_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");

