DROP TABLE IF EXISTS user_verifications;

CREATE TABLE user_verifications
(
    user_id    UUID        NOT NULL,
    code       VARCHAR(99) NOT NULL,
    expired_at TIMESTAMP   NOT NULL,
    attempts   INTEGER     NOT NULL DEFAULT 0,
    updated_at TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
);

ALTER TABLE user_verifications
    ADD CONSTRAINT user_verifications_users_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE;

ALTER TABLE users
    ADD COLUMN verified BOOLEAN NOT NULL DEFAULT FALSE;
