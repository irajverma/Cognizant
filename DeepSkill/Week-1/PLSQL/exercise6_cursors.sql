-- ============================================================
-- Exercise 6: Cursors
-- ============================================================

-- Scenario 1: GenerateMonthlyStatements
-- Uses an explicit cursor to retrieve all transactions for the current month
-- and prints a statement for each customer.

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionID, t.TransactionDate,
               t.Amount, t.TransactionType, a.AccountID
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE)
        ORDER BY c.CustomerID, t.TransactionDate;

    v_current_customer NUMBER := -1;
    v_total            NUMBER := 0;
    rec GenerateMonthlyStatements%ROWTYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('============================================');
    DBMS_OUTPUT.PUT_LINE('       MONTHLY STATEMENT - ' ||
                         TO_CHAR(SYSDATE, 'MONTH YYYY'));
    DBMS_OUTPUT.PUT_LINE('============================================');

    OPEN GenerateMonthlyStatements;
    LOOP
        FETCH GenerateMonthlyStatements INTO rec;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

        -- Print customer header when we encounter a new customer
        IF rec.CustomerID != v_current_customer THEN
            IF v_current_customer != -1 THEN
                DBMS_OUTPUT.PUT_LINE('  -----------------------------------------');
                DBMS_OUTPUT.PUT_LINE('');
            END IF;
            v_current_customer := rec.CustomerID;
            v_total := 0;
            DBMS_OUTPUT.PUT_LINE('Customer: ' || rec.Name ||
                                 ' (ID: ' || rec.CustomerID || ')');
            DBMS_OUTPUT.PUT_LINE('  -----------------------------------------');
        END IF;

        -- Print transaction details
        DBMS_OUTPUT.PUT_LINE('  ' || TO_CHAR(rec.TransactionDate, 'DD-MON-YYYY') ||
                             ' | Account: ' || rec.AccountID ||
                             ' | ' || RPAD(rec.TransactionType, 10) ||
                             ' | $' || rec.Amount);

        -- Track running total
        IF rec.TransactionType = 'Deposit' THEN
            v_total := v_total + rec.Amount;
        ELSE
            v_total := v_total - rec.Amount;
        END IF;
    END LOOP;

    IF v_current_customer != -1 THEN
        DBMS_OUTPUT.PUT_LINE('  -----------------------------------------');
    END IF;

    CLOSE GenerateMonthlyStatements;
    DBMS_OUTPUT.PUT_LINE('============================================');
    DBMS_OUTPUT.PUT_LINE('Statement generation completed.');
END;
/

-- ============================================================
-- Scenario 2: ApplyAnnualFee
-- Uses an explicit cursor to deduct an annual maintenance fee
-- from the balance of all accounts.

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance, AccountType
        FROM Accounts
        FOR UPDATE OF Balance;

    v_annual_fee   NUMBER := 50;  -- Annual maintenance fee of $50
    v_count        NUMBER := 0;
    rec ApplyAnnualFee%ROWTYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Applying Annual Maintenance Fee ===');
    DBMS_OUTPUT.PUT_LINE('Fee Amount: $' || v_annual_fee);
    DBMS_OUTPUT.PUT_LINE('');

    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO rec;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;

        -- Only deduct if balance is sufficient
        IF rec.Balance >= v_annual_fee THEN
            UPDATE Accounts
            SET Balance = Balance - v_annual_fee,
                LastModified = SYSDATE
            WHERE CURRENT OF ApplyAnnualFee;

            v_count := v_count + 1;
            DBMS_OUTPUT.PUT_LINE('Account ' || rec.AccountID ||
                                 ' (' || rec.AccountType || '): Fee deducted. ' ||
                                 'Previous Balance: $' || rec.Balance ||
                                 ', New Balance: $' || (rec.Balance - v_annual_fee));
        ELSE
            DBMS_OUTPUT.PUT_LINE('Account ' || rec.AccountID ||
                                 ' (' || rec.AccountType ||
                                 '): Insufficient balance ($' || rec.Balance ||
                                 '). Fee NOT deducted.');
        END IF;
    END LOOP;

    CLOSE ApplyAnnualFee;
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE('Annual fee applied to ' || v_count || ' account(s).');
END;
/

-- ============================================================
-- Scenario 3: UpdateLoanInterestRates
-- Uses an explicit cursor to fetch all loans and update their interest rates
-- based on a new policy.
-- New Policy:
--   - Loans with interest rate > 7%: reduce by 0.5%
--   - Loans with interest rate between 5% and 7%: reduce by 0.25%
--   - Loans with interest rate < 5%: no change

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, CustomerID, LoanAmount, InterestRate
        FROM Loans
        FOR UPDATE OF InterestRate;

    v_new_rate NUMBER;
    v_count    NUMBER := 0;
    rec UpdateLoanInterestRates%ROWTYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Updating Loan Interest Rates (New Policy) ===');
    DBMS_OUTPUT.PUT_LINE('');

    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO rec;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        -- Apply new policy
        IF rec.InterestRate > 7 THEN
            v_new_rate := rec.InterestRate - 0.5;
        ELSIF rec.InterestRate >= 5 THEN
            v_new_rate := rec.InterestRate - 0.25;
        ELSE
            v_new_rate := rec.InterestRate;
        END IF;

        -- Update only if rate changed
        IF v_new_rate != rec.InterestRate THEN
            UPDATE Loans
            SET InterestRate = v_new_rate
            WHERE CURRENT OF UpdateLoanInterestRates;

            v_count := v_count + 1;
            DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec.LoanID ||
                                 ' | Old Rate: ' || rec.InterestRate || '%' ||
                                 ' | New Rate: ' || v_new_rate || '%');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Loan ID: ' || rec.LoanID ||
                                 ' | Rate: ' || rec.InterestRate ||
                                 '% | No change (below threshold).');
        END IF;
    END LOOP;

    CLOSE UpdateLoanInterestRates;
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('');
    DBMS_OUTPUT.PUT_LINE(v_count || ' loan(s) updated.');
END;
/
