USE journal_app;

-- Deletes the existing journal_entries table
DROP TABLE IF EXISTS journal_entries;
-- Deletes the existing users table
DROP TABLE IF EXISTS users;

-- Creates the users table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Creates the journal_entries table
CREATE TABLE journal_entries (
    entry_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    title VARCHAR(25) NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    content TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

/*
Inserting sample data 
*/
INSERT INTO users(user_id, username, password) 
VALUES
(1, 'disha', 'password1'),
(2, 'anika', 'password2'),
(3, 'erich', 'password3');

INSERT INTO journal_entries (user_id, title, content) VALUES
(1, 'Disha First Journal', 'This is the content for my first journal!'),
(1 , 'Disha Second Journal', 'This is the content for my second journal!'),
(2, 'Anika First Journal', 'This is the content for my first journal!'),
(2, 'Anika Second Journal', 'This is the content for my second journal!'),
(3, 'Erich First Journal', 'This is the content for my first journal!');

SELECT * FROM users;
SELECT * FROM journal_entries; 
