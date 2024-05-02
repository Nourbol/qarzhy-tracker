DROP TABLE IF EXISTS tokens;

CREATE TABLE tokens
(
    id          UUID NOT NULL,
    hash        BYTEA NOT NULL,
    user_id     UUID NOT NULL,
    expired_at  TIMESTAMP NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE tokens
ADD CONSTRAINT tokens_users_fk
FOREIGN KEY (user_id) REFERENCES users(id);
