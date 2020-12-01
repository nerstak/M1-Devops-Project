-- You need to be connected to st2eedb to execute this part
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tutor
(
    tutor_id  UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name      varchar,
    firstname varchar,
    pwd       varchar,
    email     varchar
);

CREATE TABLE student
(
    student_id       UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name             varchar,
    firstname        varchar,
    email            varchar,
    student_group    varchar,
    linkedin_profile varchar,
    tutor_id         UUID
);

CREATE TABLE skills
(
    skill_id UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    skill    varchar
);

CREATE TABLE student_to_skills
(
    student_id UUID,
    skill_id   UUID,
    PRIMARY KEY (student_id, skill_id)
);

CREATE TABLE keywords
(
    keyword_id UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    keyword    varchar
);

CREATE TABLE internship_to_keywords
(
    internship_id UUID,
    keyword_id UUID,
    PRIMARY KEY (internship_id, keyword_id)
);

CREATE TABLE internship
(
    internship_id     UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    description       text,
    web_survey        boolean,
    mid_intern_info   boolean,
    beginning         date,
    ending            date,
    student_id        UUID,
    company_id        UUID,
    cdc               boolean,
    company_eval      boolean,
    defense           boolean,
    intern_supervisor varchar
);

CREATE TABLE company
(
    company_id UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name       varchar,
    address    varchar
);

CREATE TABLE marks
(
    marks_id      UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    tech          integer,
    communication integer,
    internship_id UUID
);

CREATE TABLE comments
(
    comments_id     UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    student_comm    varchar,
    supervisor_comm varchar,
    internship_id   UUID
);

CREATE TABLE final_report
(
    final_report_id UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    title           varchar,
    report          boolean,
    internship_id   UUID
);

CREATE TABLE visit
(
    visit_id      UUID NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    done          boolean,
    planned       boolean,
    visit_report  boolean,
    internship_id UUID
);

ALTER TABLE student
    ADD FOREIGN KEY (tutor_id) REFERENCES tutor (tutor_id);

ALTER TABLE student_to_skills
    ADD FOREIGN KEY (student_id) REFERENCES student (student_id);

ALTER TABLE student_to_skills
    ADD FOREIGN KEY (skill_id) REFERENCES skills (skill_id);

ALTER TABLE internship_to_keywords
    ADD FOREIGN KEY (internship_id) REFERENCES internship (internship_id);

ALTER TABLE internship_to_keywords
    ADD FOREIGN KEY (keyword_id) REFERENCES keywords (keyword_id);

ALTER TABLE internship
    ADD FOREIGN KEY (student_id) REFERENCES student (student_id);

ALTER TABLE internship
    ADD FOREIGN KEY (company_id) REFERENCES company (company_id);

ALTER TABLE marks
    ADD FOREIGN KEY (internship_id) REFERENCES internship (internship_id);

ALTER TABLE comments
    ADD FOREIGN KEY (internship_id) REFERENCES internship (internship_id);

ALTER TABLE final_report
    ADD FOREIGN KEY (internship_id) REFERENCES internship (internship_id);

ALTER TABLE visit
    ADD FOREIGN KEY (internship_id) REFERENCES internship (internship_id);


CREATE VIEW internships_data AS
(
SELECT S.*,
       I.internship_id,
       I.company_id,
       I.description,
       I.mid_intern_info,
       I.web_survey,
       I.beginning,
       I.ending,
       I.cdc,
       I.company_eval,
       I.defense,
       I.intern_supervisor,
       C.name AS company_name,
       C.address,
       V.done,
       V.planned,
       V.visit_report,
       V.visit_id,
       M.communication,
       M.tech,
       M.marks_id,
       Fr.final_report_id,
       Fr.title,
       fr.report


FROM student S
         JOIN internship I
                   ON S.student_id = I.student_id
         JOIN company C
              ON I.company_id = C.company_id
         JOIN visit V
                   ON I.internship_id = V.internship_id
         JOIN marks M
                   ON I.internship_id = M.internship_id
         JOIN final_report Fr
                   ON Fr.internship_id = I.internship_id

ORDER BY (I.beginning) DESC);

CREATE VIEW internships_data_details AS
(
SELECT S.*, C.comments_id, C.student_comm, C.supervisor_comm
FROM internships_data S
         JOIN comments C
                   ON S.internship_id = C.internship_id
    );

CREATE VIEW students_skills AS
(
SELECT S.*, sts.student_id
FROM skills S
         JOIN student_to_skills sts
              ON sts.skill_id = s.skill_id
    );

CREATE VIEW internship_keywords AS
(
SELECT k.keyword, itk.internship_id
FROM keywords k
         JOIN internship_to_keywords itk
              ON k.keyword_id = itk.keyword_id
    );

GRANT ALL PRIVILEGES ON DATABASE st2eedb TO adm;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to adm;

