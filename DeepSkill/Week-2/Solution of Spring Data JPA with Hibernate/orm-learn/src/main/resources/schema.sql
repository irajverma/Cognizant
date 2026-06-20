-- Drop existing tables to ensure a clean start in H2
DROP TABLE IF EXISTS attempt_option;
DROP TABLE IF EXISTS attempt_question;
DROP TABLE IF EXISTS attempt;
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS country;

-- 1. Country Table (Part A)
CREATE TABLE country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50) NOT NULL
);

-- 2. Department, Employee, Skill Tables (Part B)
CREATE TABLE department (
    dp_id INT PRIMARY KEY AUTO_INCREMENT,
    dp_name VARCHAR(50) NOT NULL
);

CREATE TABLE employee (
    em_id INT PRIMARY KEY AUTO_INCREMENT,
    em_name VARCHAR(50) NOT NULL,
    em_salary DOUBLE NOT NULL,
    em_permanent BOOLEAN NOT NULL,
    em_date_of_birth DATE,
    em_dp_id INT,
    FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

CREATE TABLE skill (
    sk_id INT PRIMARY KEY AUTO_INCREMENT,
    sk_name VARCHAR(50) NOT NULL
);

CREATE TABLE employee_skill (
    es_em_id INT,
    es_sk_id INT,
    PRIMARY KEY (es_em_id, es_sk_id),
    FOREIGN KEY (es_em_id) REFERENCES employee(em_id) ON DELETE CASCADE,
    FOREIGN KEY (es_sk_id) REFERENCES skill(sk_id) ON DELETE CASCADE
);

-- 3. Quiz Tables (Part C)
CREATE TABLE user (
    us_id INT PRIMARY KEY AUTO_INCREMENT,
    us_name VARCHAR(50) NOT NULL,
    us_email VARCHAR(50) NOT NULL
);

CREATE TABLE question (
    qt_id INT PRIMARY KEY AUTO_INCREMENT,
    qt_text VARCHAR(255) NOT NULL,
    qt_score DOUBLE NOT NULL
);

CREATE TABLE options (
    op_id INT PRIMARY KEY AUTO_INCREMENT,
    op_qt_id INT NOT NULL,
    op_text VARCHAR(255) NOT NULL,
    op_is_correct BOOLEAN NOT NULL,
    FOREIGN KEY (op_qt_id) REFERENCES question(qt_id) ON DELETE CASCADE
);

CREATE TABLE attempt (
    at_id INT PRIMARY KEY AUTO_INCREMENT,
    at_us_id INT NOT NULL,
    at_date TIMESTAMP NOT NULL,
    FOREIGN KEY (at_us_id) REFERENCES user(us_id) ON DELETE CASCADE
);

CREATE TABLE attempt_question (
    aq_id INT PRIMARY KEY AUTO_INCREMENT,
    aq_at_id INT NOT NULL,
    aq_qt_id INT NOT NULL,
    FOREIGN KEY (aq_at_id) REFERENCES attempt(at_id) ON DELETE CASCADE,
    FOREIGN KEY (aq_qt_id) REFERENCES question(qt_id) ON DELETE CASCADE
);

CREATE TABLE attempt_option (
    ao_id INT PRIMARY KEY AUTO_INCREMENT,
    ao_aq_id INT NOT NULL,
    ao_op_id INT NOT NULL,
    FOREIGN KEY (ao_aq_id) REFERENCES attempt_question(aq_id) ON DELETE CASCADE,
    FOREIGN KEY (ao_op_id) REFERENCES options(op_id) ON DELETE CASCADE
);
