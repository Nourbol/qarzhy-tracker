DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions
(
    id             UUID NOT NULL,
    operation_date  DATE NOT NULL,
    amount         NUMERIC(15,6) NOT NULL,
    details        VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

