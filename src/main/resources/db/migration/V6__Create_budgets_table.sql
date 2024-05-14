DROP TABLE IF EXISTS budgets;

CREATE TABLE budgets
(
    category_id UUID           NOT NULL,
    user_id     UUID           NOT NULL,
    amount      NUMERIC(15, 6) NOT NULL,
    repeat_cron VARCHAR(999),
    priority    INTEGER        NOT NULL,
    active      BOOLEAN        NOT NULL DEFAULT TRUE,
    updated_at  TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP               DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (category_id)
);

ALTER TABLE budgets
    ADD CONSTRAINT budgets_categories_fk
        FOREIGN KEY (category_id) REFERENCES categories (id)
            ON DELETE CASCADE;

ALTER TABLE budgets
    ADD CONSTRAINT budgets_users_fk
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE;

ALTER TABLE budgets
    ADD CONSTRAINT budgets_priority_user_id_key
        UNIQUE (priority, user_id)
            INITIALLY DEFERRED;
