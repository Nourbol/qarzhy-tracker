DROP TABLE IF EXISTS balance_history_records;

CREATE TABLE balance_history_records
(
    id          UUID           NOT NULL,
    balance     NUMERIC(15, 6) NOT NULL,
    user_id     UUID           NOT NULL,
    recorded_at DATE           NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE balance_history_records
    ADD CONSTRAINT balance_history_records_users_fk
        FOREIGN KEY (user_id) REFERENCES users (id);
