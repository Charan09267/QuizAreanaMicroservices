-- ==========================================================
-- LEADERBOARD
-- ==========================================================

CREATE TABLE leaderboards (

                              id BIGINT AUTO_INCREMENT PRIMARY KEY,


    -- Reference to contest-service
                              contest_id BIGINT NOT NULL,


    -- Reference to auth-service
                              user_id BIGINT NOT NULL,


                              score DECIMAL(10,2) DEFAULT 0.00,


                              rank_position INT,


                              completion_time_seconds INT,


                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                                  ON UPDATE CURRENT_TIMESTAMP,


                              CONSTRAINT uk_leaderboard_contest_user
                                  UNIQUE(contest_id,user_id)

) ENGINE=InnoDB;