DROP TABLE IF EXISTS categories;

CREATE TABLE categories
(
    id         UUID         NOT NULL,
    name       VARCHAR(255) NOT NULL,
    user_id    UUID         NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT categories_users_fk
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE categories
    ADD CONSTRAINT categories_name_user_id_key UNIQUE (name, user_id);

ALTER TABLE transactions
    ADD COLUMN category_id UUID
        CONSTRAINT transactions_categories_fk REFERENCES categories (id)
            ON DELETE SET NULL;
