-- ============================================================
-- Exercise 3: Stored Procedures
-- ============================================================

-- Scenario 1: ProcessMonthlyInterest
-- Calculates and updates the balance of all savings accounts by applying
-- an interest rate of 1% to the current balance.

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_count NUMBER := 0;
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 1 / 100),
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    v_count := SQL%ROWCOUNT;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processed for ' || v_count ||
                         ' savings account(s).');
END;
/

-- ============================================================
-- Scenario 2: UpdateEmployeeBonus
-- Updates the salary of employees in a given department by adding
-- a bonus percentage passed as a parameter.

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department       IN VARCHAR2,
    p_bonus_percentage IN NUMBER
) AS
    v_count NUMBER := 0;
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percentage / 100)
    WHERE Department = p_department;

    v_count := SQL%ROWCOUNT;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_percentage ||
                             '% applied to ' || v_count ||
                             ' employee(s) in ' || p_department || ' department.');
    END IF;
END;
/

-- ============================================================
-- Scenario 3: TransferFunds
-- Transfers a specified amount from one account to another, checking
-- that the source account has sufficient balance before making the transfer.

CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_from_balance NUMBER;
BEGIN
    -- Validate amount
    IF p_amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be positive.');
        RETURN;
    END IF;

    -- Get the balance of the source account
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account
    FOR UPDATE;  -- Lock the row to prevent concurrent modifications

    -- Check for sufficient balance
    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient balance in Account ' ||
                             p_from_account || '. Available: $' || v_from_balance ||
                             ', Requested: $' || p_amount);
        RETURN;
    END IF;

    -- Deduct from source account
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account;

    -- Credit to destination account
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred $' || p_amount ||
                         ' from Account ' || p_from_account ||
                         ' to Account ' || p_to_account || '.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.');
        ROLLBACK;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        ROLLBACK;
END;
/
