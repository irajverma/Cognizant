-- ============================================================
-- Exercise 2: Error Handling
-- ============================================================

-- Scenario 1: SafeTransferFunds - Handle exceptions during fund transfers

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_from_balance NUMBER;
    v_to_exists    NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    -- Check if source account exists and get balance
    SELECT Balance INTO v_from_balance
    FROM Accounts
    WHERE AccountID = p_from_account;

    -- Check if destination account exists
    SELECT COUNT(*) INTO v_to_exists
    FROM Accounts
    WHERE AccountID = p_to_account;

    IF v_to_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Destination account does not exist.');
    END IF;

    -- Check for sufficient funds
    IF v_from_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Deduct from source account
    UPDATE Accounts
    SET Balance = Balance - p_amount
    WHERE AccountID = p_from_account;

    -- Credit to destination account
    UPDATE Accounts
    SET Balance = Balance + p_amount
    WHERE AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of $' || p_amount || ' from Account ' ||
                         p_from_account || ' to Account ' || p_to_account ||
                         ' completed successfully.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ErrorMessage, ErrorDate, ProcedureName)
        VALUES (ErrorLog_Seq.NEXTVAL, 'Source account ' || p_from_account ||
                ' does not exist.', SYSDATE, 'SafeTransferFunds');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Source account does not exist.');

    WHEN insufficient_funds THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ErrorMessage, ErrorDate, ProcedureName)
        VALUES (ErrorLog_Seq.NEXTVAL, 'Insufficient funds in account ' ||
                p_from_account || '. Available: $' || v_from_balance ||
                ', Requested: $' || p_amount, SYSDATE, 'SafeTransferFunds');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds for transfer.');

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ErrorMessage, ErrorDate, ProcedureName)
        VALUES (ErrorLog_Seq.NEXTVAL, 'Unexpected error: ' || SQLERRM,
                SYSDATE, 'SafeTransferFunds');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- ============================================================
-- Scenario 2: UpdateSalary - Handle errors when updating employee salaries

CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage  IN NUMBER
) AS
    v_current_salary NUMBER;
BEGIN
    -- Fetch current salary (raises NO_DATA_FOUND if employee doesn't exist)
    SELECT Salary INTO v_current_salary
    FROM Employees
    WHERE EmployeeID = p_employee_id;

    -- Update the salary
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for Employee ID ' || p_employee_id ||
                         '. New salary: $' ||
                         ROUND(v_current_salary + (v_current_salary * p_percentage / 100), 2));

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        INSERT INTO ErrorLog (ErrorID, ErrorMessage, ErrorDate, ProcedureName)
        VALUES (ErrorLog_Seq.NEXTVAL, 'Employee ID ' || p_employee_id ||
                ' does not exist.', SYSDATE, 'UpdateSalary');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' not found.');

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ErrorMessage, ErrorDate, ProcedureName)
        VALUES (ErrorLog_Seq.NEXTVAL, 'Unexpected error: ' || SQLERRM,
                SYSDATE, 'UpdateSalary');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- ============================================================
-- Scenario 3: AddNewCustomer - Ensure data integrity when adding a new customer

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name        IN VARCHAR2,
    p_dob         IN DATE,
    p_balance     IN NUMBER
) AS
    v_exists NUMBER;
BEGIN
    -- Check if customer already exists
    SELECT COUNT(*) INTO v_exists
    FROM Customers
    WHERE CustomerID = p_customer_id;

    IF v_exists > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Customer with ID ' || p_customer_id ||
                                ' already exists.');
    END IF;

    -- Insert the new customer
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_name || ' (ID: ' || p_customer_id ||
                         ') added successfully.');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        INSERT INTO ErrorLog (ErrorID, ErrorMessage, ErrorDate, ProcedureName)
        VALUES (ErrorLog_Seq.NEXTVAL, 'Duplicate Customer ID: ' || p_customer_id,
                SYSDATE, 'AddNewCustomer');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id ||
                             ' already exists. Insertion prevented.');

    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ErrorMessage, ErrorDate, ProcedureName)
        VALUES (ErrorLog_Seq.NEXTVAL, 'Error adding customer: ' || SQLERRM,
                SYSDATE, 'AddNewCustomer');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
