DROP TABLE IF EXISTS user_table,ramen_table;

CREATE TABLE user_table
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    PRIMARY KEY(id)
);

CREATE TABLE ramen_table
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    price INT,
    place VARCHAR(100),
    PRIMARY KEY(id)
);