ALTER TABLE transactions
    ADD CHECK ( amount <> 0 );
