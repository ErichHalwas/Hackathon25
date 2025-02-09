/*
USE journal_app;

DROP TABLE IF EXISTS journal_entries;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
user_id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS journal_entries (
entry_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
title VARCHAR(25) NOT NULL,
date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
text TEXT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO users (username, password) VALUES ('disha', 'password');
*/
