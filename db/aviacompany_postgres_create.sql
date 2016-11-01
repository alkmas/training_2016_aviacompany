CREATE SCHEMA public
  AUTHORIZATION postgres;

CREATE TABLE "employee" (
	"id" serial NOT NULL,
	"first_name" character varying(256) NOT NULL,
	"last_name" character varying(256) NOT NULL,
	"birthday" DATE NOT NULL,
	"job_title_id" bigint,
	CONSTRAINT employee_pk PRIMARY KEY ("id"),
	UNIQUE ("first_name", "last_name", "birthday")
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



CREATE TABLE "flight_2_employee" (
	"id" serial NOT NULL,
	"flight_id" bigint NOT NULL,
	"employee_id" bigint NOT NULL,
	"departure" DATE NOT NULL,
	PRIMARY KEY ("id"),
	UNIQUE (employee_id, departure)
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



CREATE TABLE "flight_day_week" (
	"flight_id" bigint NOT NULL,
	"day_week" int NOT NULL,
	UNIQUE ("flight_id", "day_week")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "employee" ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("job_title_id") REFERENCES "job_title"("id");


ALTER TABLE "flight" ADD CONSTRAINT "flight_fk0" FOREIGN KEY ("airport_src_id") REFERENCES "airport"("id");
ALTER TABLE "flight" ADD CONSTRAINT "flight_fk1" FOREIGN KEY ("airport_dst_id") REFERENCES "airport"("id");

ALTER TABLE "flight_2_employee" ADD CONSTRAINT "flight_2_employee_fk0" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");
ALTER TABLE "flight_2_employee" ADD CONSTRAINT "flight_2_employee_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");


ALTER TABLE "flight_day_week" ADD CONSTRAINT "flight_day_week_fk0" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");

