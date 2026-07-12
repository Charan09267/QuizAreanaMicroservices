-- ==========================================================
-- CONTEST ATTEMPTS
-- ==========================================================

CREATE TABLE contest_attempts (

                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,


    -- Reference to contest-service
                                  contest_id BIGINT NOT NULL,


    -- Reference to contest participant
                                  participant_id BIGINT NOT NULL,


                                  score DECIMAL(10,2) DEFAULT 0.00,


                                  total_questions INT DEFAULT 0,


                                  correct_answers INT DEFAULT 0,


                                  wrong_answers INT DEFAULT 0,


                                  unanswered_questions INT DEFAULT 0,


                                  started_at TIMESTAMP NULL,


                                  submitted_at TIMESTAMP NULL,


                                  expires_at TIMESTAMP NULL,


                                  status VARCHAR(20),


                                  time_taken_seconds INT,


                                  CONSTRAINT uk_attempt_participant
                                      UNIQUE(participant_id)

) ENGINE=InnoDB;



-- ==========================================================
-- QUESTION ATTEMPTS
-- ==========================================================

CREATE TABLE contest_question_attempts (

                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,


                                           contest_attempt_id BIGINT NOT NULL,


                                           question_id BIGINT NOT NULL,


                                           selected_option_id BIGINT,


                                           is_correct BOOLEAN,


                                           marks_awarded DECIMAL(5,2) NOT NULL DEFAULT 0.00,


                                           answered_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


                                           CONSTRAINT fk_question_attempt_attempt
                                               FOREIGN KEY(contest_attempt_id)
                                                   REFERENCES contest_attempts(id)
                                                   ON DELETE CASCADE,


                                           CONSTRAINT uk_attempt_question
                                               UNIQUE(contest_attempt_id,question_id)

) ENGINE=InnoDB;