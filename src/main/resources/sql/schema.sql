DROP TABLE IF EXISTS user_table;

CREATE TABLE user_table
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    PRIMARY KEY(id)
);