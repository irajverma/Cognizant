-- ============================================================
-- Exercise 1: Control Structures
-- ============================================================

-- Scenario 1: Apply 1% discount to loan interest rates for customers above 60 years old
-- Loops through all customers, checks their age, and if above 60, applies a 1% discount
-- to their current loan interest rates.

DECLARE
    v_age NUMBER;
    CURSOR c_customers IS
        SELECT CustomerID, Name, DOB
        FROM Customers;
BEGIN
    FOR rec IN c_customers LOOP
        -- Calculate age using MONTHS_BETWEEN
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12);

        IF v_age > 60 THEN
            -- Apply 1% discount to the interest rate of all loans for this customer
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = rec.CustomerID
              AND InterestRate > 1;  -- Ensure interest rate doesn't go below 1%

            DBMS_OUTPUT.PUT_LINE('Applied 1% discount for customer: ' || rec.Name ||
                                 ' (Age: ' || v_age || ')');
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Discount processing completed.');
END;
/

-- ============================================================
-- Scenario 2: Set IsVIP flag to TRUE for customers with balance over $10,000

DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, Name, Balance
        FROM Customers;
BEGIN
    FOR rec IN c_customers LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = rec.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Customer ' || rec.Name ||
                                 ' promoted to VIP (Balance: $' || rec.Balance || ')');
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CustomerID = rec.CustomerID;
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP status update completed.');
END;
/

-- ============================================================
-- Scenario 3: Send reminders for loans due within the next 30 days

DECLARE
    CURSOR c_loans IS
        SELECT l.LoanID, l.EndDate, c.Name, c.CustomerID
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
    v_days_remaining NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Loan Due Reminders ===');
    DBMS_OUTPUT.PUT_LINE('');

    FOR rec IN c_loans LOOP
        v_days_remaining := TRUNC(rec.EndDate - SYSDATE);

        DBMS_OUTPUT.PUT_LINE('REMINDER: Dear ' || rec.Name ||
                             ', your Loan (ID: ' || rec.LoanID ||
                             ') is due on ' || TO_CHAR(rec.EndDate, 'DD-MON-YYYY') ||
                             '. Days remaining: ' || v_days_remaining || '.');
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Reminder processing completed.');
END;
/
