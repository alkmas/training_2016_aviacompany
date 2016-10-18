CREATE TABLE "empoyee" (
	"id" serial NOT NULL,
	"name" character varying(256) NOT NULL,
	"company_id" bigint NOT NULL,
	"job_title_id" bigint NOT NULL,
	"state_id" bigint NOT NULL DEFAULT '1',
	CONSTRAINT empoyee_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "state" (
	"id" serial NOT NULL,
	"name" character(8) NOT NULL UNIQUE,
	CONSTRAINT state_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "company" (
	"id" serial NOT NULL,
	"name" character varying(256) NOT NULL UNIQUE,
	CONSTRAINT company_pk PRIMARY KEY ("id")
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
	"name" character varying(256) NOT NULL,
	"flying_away" DATE,
	CONSTRAINT flight_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "team" (
	"id" serial NOT NULL,
	"number" character varying(256) NOT NULL,
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
	"team_id" bigint NOT NULL,
	"flight_id" bigint NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "empoyee" ADD CONSTRAINT "empoyee_fk0" FOREIGN KEY ("company_id") REFERENCES "company"("id");
ALTER TABLE "empoyee" ADD CONSTRAINT "empoyee_fk1" FOREIGN KEY ("job_title_id") REFERENCES "job_title"("id");
ALTER TABLE "empoyee" ADD CONSTRAINT "empoyee_fk2" FOREIGN KEY ("state_id") REFERENCES "state"("id");






ALTER TABLE "team_2_employee" ADD CONSTRAINT "team_2_employee_fk0" FOREIGN KEY ("team_id") REFERENCES "team"("id");
ALTER TABLE "team_2_employee" ADD CONSTRAINT "team_2_employee_fk1" FOREIGN KEY ("employee_id") REFERENCES "empoyee"("id");

ALTER TABLE "team_2_flight" ADD CONSTRAINT "team_2_flight_fk0" FOREIGN KEY ("team_id") REFERENCES "team"("id");
ALTER TABLE "team_2_flight" ADD CONSTRAINT "team_2_flight_fk1" FOREIGN KEY ("flight_id") REFERENCES "flight"("id");
