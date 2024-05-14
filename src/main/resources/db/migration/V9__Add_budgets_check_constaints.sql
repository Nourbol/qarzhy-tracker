ALTER TABLE budgets
    ADD CHECK ( amount > 0 );

ALTER TABLE budgets
    ADD CHECK ( priority >= 0 )
