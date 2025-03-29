-- Create Database
CREATE DATABASE IF NOT EXISTS student_grading_system;
USE student_grading_system;

-- Create Students Table with `stu_id` as Primary Key
CREATE TABLE IF NOT EXISTS students (
    student_id CHAR(7) PRIMARY KEY CHECK (student_id REGEXP '^[0-9]{7}$'),  -- Must be exactly 7 digits
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create Courses Table
CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    course_credits INT
);

-- Create Grades Table with `student_id` as Foreign Key
CREATE TABLE IF NOT EXISTS grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id CHAR(7),
    course_id INT,
    grade DECIMAL(5,2) CHECK (grade BETWEEN 0 AND 100),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Insert Sample Students
INSERT INTO students (student_id, name, email, password) VALUES
('0257891', 'Alice Johnson', 'alice@example.com', '$2a$12$nBv4DloL4l5SGaG/V6Uifef.chIuWyYDTsBmOphyZrJEI/rEE9x6K'),
('0256723', 'Bob Smith', 'bob@example.com', '$2a$12$519vYLljM4GtdzQHgkgJzu7tmFvkriCEU/m6Qzza92FvbjuJiY4m6'),
('0251459', 'Mark Brown', 'mark@example.com', '$2a$12$LMOeeG.1xRKAxbXnw5C31uHW1qhJMlJadbmGePomO9HbTU7Gi9yLK');

-- Insert Courses
INSERT INTO courses (course_name, course_credits) VALUES
('Discrete', 3),
('Computer Science', 4),
('Java', 2),
('Data Structure', 3);

-- Insert Grades
INSERT INTO grades (student_id, course_id, grade) VALUES
('0257891', 1, 95.00),
('0257891', 2, 88.50),
('0256723', 1, 76.00),
('0256723', 3, 85.75),
('0251459', 4, 92.00),
('0251459', 2, 78.25);
