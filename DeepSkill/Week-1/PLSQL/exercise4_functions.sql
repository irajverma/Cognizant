-- ============================================================
-- Exercise 4: Functions
-- ============================================================

-- Scenario 1: CalculateAge
-- Takes a customer's date of birth and returns their age in years.

CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob IN DATE
) RETURN NUMBER AS
    v_age NUMBER;
BEGIN
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
END;
/

-- Test CalculateAge
DECLARE
    v_age NUMBER;
BEGIN
    FOR rec IN (SELECT CustomerID, Name, DOB FROM Customers) LOOP
        v_age := CalculateAge(rec.DOB);
        DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.Name || ', Age: ' || v_age || ' years');
    END LOOP;
END;
/

-- ============================================================
-- Scenario 2: CalculateMonthlyInstallment
-- Takes the loan amount, interest rate, and loan duration in years
-- and returns the monthly installment amount.
-- Uses the EMI formula: EMI = [P * r * (1+r)^n] / [(1+r)^n - 1]

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount    IN NUMBER,
    p_interest_rate  IN NUMBER,
    p_duration_years IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate  NUMBER;
    v_num_payments  NUMBER;
    v_emi           NUMBER;
BEGIN
    -- Convert annual interest rate to monthly rate (as a decimal)
    v_monthly_rate := p_interest_rate / 12 / 100;
    v_num_payments := p_duration_years * 12;

    -- Handle zero interest rate case
    IF v_monthly_rate = 0 THEN
        v_emi := p_loan_amount / v_num_payments;
    ELSE
        -- EMI formula: [P * r * (1+r)^n] / [(1+r)^n - 1]
        v_emi := (p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_num_payments))
                 / (POWER(1 + v_monthly_rate, v_num_payments) - 1);
    END IF;

    RETURN ROUND(v_emi, 2);
END;
/

-- Test CalculateMonthlyInstallment
DECLARE
    v_emi NUMBER;
BEGIN
    FOR rec IN (SELECT LoanID, LoanAmount, InterestRate,
                       TRUNC(MONTHS_BETWEEN(EndDate, StartDate) / 12) AS DurationYears
                FROM Loans) LOOP
        v_emi := CalculateMonthlyInstallment(rec.LoanAmount, rec.InterestRate, rec.DurationYears);
        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec.LoanID ||
                             ', Amount: $' || rec.LoanAmount ||
                             ', Rate: ' || rec.InterestRate || '%' ||
                             ', Monthly Installment: $' || v_emi);
    END LOOP;
END;
/

-- ============================================================
-- Scenario 3: HasSufficientBalance
-- Takes an account ID and an amount as input and returns a boolean
-- indicating whether the account has at least the specified amount.

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    RETURN (v_balance >= p_amount);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/

-- Test HasSufficientBalance
DECLARE
    v_result BOOLEAN;
BEGIN
    -- Test with Account 1 (Balance: 1000), checking for $500
    v_result := HasSufficientBalance(1, 500);
    IF v_result THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for $500: YES');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for $500: NO');
    END IF;

    -- Test with Account 1 (Balance: 1000), checking for $2000
    v_result := HasSufficientBalance(1, 2000);
    IF v_result THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for $2000: YES');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for $2000: NO');
    END IF;

    -- Test with non-existent account
    v_result := HasSufficientBalance(999, 100);
    IF v_result THEN
        DBMS_OUTPUT.PUT_LINE('Account 999 has sufficient balance for $100: YES');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 999 has sufficient balance for $100: NO');
    END IF;
END;
/
