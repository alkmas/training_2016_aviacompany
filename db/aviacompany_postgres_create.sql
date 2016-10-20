CREATE TABLE "empoyee" (
	"id" serial NOT NULL,
	"first_name" character varying(256) NOT NULL,
	"last_name" character varying(256) NOT NULL,
	"birthday" DATE,
	"job_title_id" bigint,
	CONSTRAINT empoyee_pk PRIMARY KEY ("id")
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
	"name" character varying(20) NOT NULL,
	"airport_src_id" bigint NOT NULL,
	"airport_dst_id" bigint NOT NULL,
	"away_time" TIME NOT NULL,
	"arrival_time" TIME NOT NULL,
	CONSTRAINT flight_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "team" (
	"id" serial NOT NULL,
	"count_pilot" bigint NOT NULL,
	"count_navigator" bigint NOT NULL,
	"count_radioman" bigint NOT NULL,
	"count_stewardess" bigint NOT NULL,
	CONSTRAINT team_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "team_2_employee" (
	"team_id" bigint NOT NULL,
	"employee_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "team_2_flight" (
	"id" serial NOT NULL,
	"team_id" bigint NOT NULL,
	"flight_id" bigint NOT NULL,
	"arrival" DATE,
	CONSTRAINT team_2_flight_pk PRIMARY KEY ("id")
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



CREATE TABLE "flight_2_day_week" (
	"flight_id" bigint NOT NULL,
	"day_week" int NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "empoyee" ADD CONSTRAINT "empoyee_fk0" FOREIGN KEY ("job_title_id") REFERENCES "job_title"("id");


ALTER TABLE "flight" ADD CONSTRAINT "flight_fk0" FOREIGN KEY ("airport_src_id") REFERENCES "airport"("id");
ALTER TABLE "flight" ADD CONSTRAINT "flight_fk1" FOREIGN KEY ("airport_dst_id") REFERENCES "airport"("id");


ALTER TABLE "team_2_employee" ADD CONSTRAINT "team_2_employee_fk0" FOREIGN KEY ("team_id") REFERENCES "team"("id");
ALTER TABLE "team_2_employee" ADD CONSTRAINT "team_2_employee_fk1" FOREIGN KEY ("employee_id") REFERENCES "empoyee"("id");

ALTER TABLE "team_2_flight" ADD CONSTRAINT "team_2_flight_fk0" FOREIGN KEY ("team_id") REFERENCES "team"("id");
ALTER TABLE "team_2_flight" ADD CONSTRAINT "team_2_flight_fk1" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");


ALTER TABLE "flight_2_day_week" ADD CONSTRAINT "flight_2_day_week_fk0" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");

