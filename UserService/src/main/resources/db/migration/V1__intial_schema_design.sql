CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,

                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,

                       password_hash TEXT NOT NULL,

                       first_name VARCHAR(100),
                       last_name VARCHAR(100),

                       profile_pic_url TEXT,

                       role VARCHAR(30) NOT NULL DEFAULT 'LEARNER',

                       provider VARCHAR(20) DEFAULT 'LOCAL',

                       is_verified BOOLEAN DEFAULT FALSE,

                       status VARCHAR(20) DEFAULT 'ACTIVE',

                       last_login_at TIMESTAMP NULL,

                       profile_completed BOOLEAN DEFAULT FALSE,

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                           ON UPDATE CURRENT_TIMESTAMP,

                       PRIMARY KEY (id),

                       INDEX idx_users_role (role),
                       INDEX idx_users_status (status)
);