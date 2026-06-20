-- ============================================================
-- Exercise 5: Triggers
-- ============================================================

-- Scenario 1: UpdateCustomerLastModified
-- Automatically updates the LastModified column of the Customers table
-- to the current date whenever a customer's record is updated.

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Test: Update a customer record
UPDATE Customers SET Name = 'John Doe Updated' WHERE CustomerID = 1;
COMMIT;

SELECT CustomerID, Name, LastModified FROM Customers WHERE CustomerID = 1;

-- ============================================================
-- Scenario 2: LogTransaction
-- Inserts a record into an AuditLog table whenever a transaction
-- is inserted into the Transactions table.

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (AuditID, TransactionID, AccountID, TransactionDate,
                          Amount, TransactionType, LogTimestamp)
    VALUES (AuditLog_Seq.NEXTVAL, :NEW.TransactionID, :NEW.AccountID,
            :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END;
/

-- Test: Insert a new transaction (this should automatically log to AuditLog)
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (100, 1, SYSDATE, 500, 'Deposit');
COMMIT;

-- Verify the audit log
SELECT * FROM AuditLog WHERE TransactionID = 100;

-- ============================================================
-- Scenario 3: CheckTransactionRules
-- Ensures withdrawals do not exceed the balance and deposits are positive
-- before inserting a record into the Transactions table.

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- For deposits: ensure the amount is positive
    IF :NEW.TransactionType = 'Deposit' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20003,
                'Deposit amount must be positive. Amount: ' || :NEW.Amount);
        END IF;
    END IF;

    -- For withdrawals: ensure the amount does not exceed the account balance
    IF :NEW.TransactionType = 'Withdrawal' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20004,
                'Withdrawal amount must be positive. Amount: ' || :NEW.Amount);
        END IF;

        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20005,
                'Insufficient balance for withdrawal. Available: $' ||
                v_balance || ', Requested: $' || :NEW.Amount);
        END IF;
    END IF;
END;
/

-- Test: Try to insert a valid deposit
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (101, 1, SYSDATE, 100, 'Deposit');
COMMIT;
DBMS_OUTPUT.PUT_LINE('Valid deposit inserted successfully.');

-- Test: Try to insert a withdrawal that exceeds balance (this should fail)
-- Uncomment to test:
-- INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
-- VALUES (102, 1, SYSDATE, 999999, 'Withdrawal');
