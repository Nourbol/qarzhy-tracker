ALTER TABLE balance_history_records
    ADD CHECK ( balance > 0 );
