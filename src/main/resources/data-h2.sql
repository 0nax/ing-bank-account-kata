INSERT INTO ACCOUNT(ID, BALANCE, ACCOUNT_NUMBER) VALUES
    (1, 100.50, '01234567891'),
    (2, 3250.17, '01234567892'),
    (3, 0.0, '01234567893');

INSERT INTO HISTORY(ID, ACCOUNT_NUMBER, TRANSACTION_TYPE, DATE_TRANSACTION, TRANSACTION_AMOUNT) VALUES
    (1, '01234567891', 'DEPOSIT', '2022-04-11', 120),
    (2, '01234567891', 'WITHDRAW', '2022-04-15', 40);
