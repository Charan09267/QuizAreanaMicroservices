-- ==========================================================
-- CONTESTS
-- ==========================================================

CREATE TABLE contests (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                          title VARCHAR(255) NOT NULL,

                          description TEXT,

    -- Reference to auth-service user id
                          created_by BIGINT NOT NULL,

                          visibility VARCHAR(20) DEFAULT 'PUBLIC',

                          start_time TIMESTAMP NOT NULL,

                          end_time TIMESTAMP NOT NULL,

                          duration_seconds INT,

                          max_participants INT,

                          contest_type VARCHAR(50),

                          status VARCHAR(20) DEFAULT 'UPCOMING',

                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                              ON UPDATE CURRENT_TIMESTAMP

) ENGINE=InnoDB;



-- ==========================================================
-- TAGS
-- ==========================================================

CREATE TABLE tags (

                      id BIGINT AUTO_INCREMENT PRIMARY KEY,

                      name VARCHAR(100) NOT NULL,

                      category VARCHAR(50),

                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                      CONSTRAINT uk_tags_name UNIQUE(name)

) ENGINE=InnoDB;



-- ==========================================================
-- CONTEST TAGS
-- ==========================================================

CREATE TABLE contest_tags (

                              id BIGINT AUTO_INCREMENT PRIMARY KEY,

                              contest_id BIGINT NOT NULL,

                              tag_id BIGINT NOT NULL,


                              CONSTRAINT fk_contest_tags_contest
                                  FOREIGN KEY(contest_id)
                                      REFERENCES contests(id)
                                      ON DELETE CASCADE,


                              CONSTRAINT fk_contest_tags_tag
                                  FOREIGN KEY(tag_id)
                                      REFERENCES tags(id)
                                      ON DELETE CASCADE,


                              CONSTRAINT uk_contest_tag
                                  UNIQUE(contest_id, tag_id)

) ENGINE=InnoDB;



-- ==========================================================
-- CONTEST PARTICIPANTS
-- ==========================================================

CREATE TABLE contest_participants (

                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,


                                      contest_id BIGINT NOT NULL,


    -- Reference to auth-service user id
                                      user_id BIGINT NOT NULL,


                                      joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


                                      status VARCHAR(20) DEFAULT 'REGISTERED',


                                      CONSTRAINT fk_contest_participant_contest
                                          FOREIGN KEY(contest_id)
                                              REFERENCES contests(id)
                                              ON DELETE CASCADE,


                                      CONSTRAINT uk_contest_user
                                          UNIQUE(contest_id,user_id)

) ENGINE=InnoDB;



-- ==========================================================
-- CONTEST QUESTIONS
-- ==========================================================

CREATE TABLE contest_questions (

                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,


                                   contest_id BIGINT NOT NULL,


                                   question_text TEXT NOT NULL,


                                   question_type VARCHAR(50),


                                   marks INT DEFAULT 1,


                                   explanation TEXT,


                                   order_index INT,


                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


                                   CONSTRAINT fk_contest_questions_contest
                                       FOREIGN KEY(contest_id)
                                           REFERENCES contests(id)
                                           ON DELETE CASCADE

) ENGINE=InnoDB;



-- ==========================================================
-- CONTEST OPTIONS
-- ==========================================================

CREATE TABLE contest_options (

                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,


                                 question_id BIGINT NOT NULL,


                                 option_text TEXT NOT NULL,


                                 is_correct BOOLEAN DEFAULT FALSE,


                                 CONSTRAINT fk_contest_options_question
                                     FOREIGN KEY(question_id)
                                         REFERENCES contest_questions(id)
                                         ON DELETE CASCADE

) ENGINE=InnoDB;