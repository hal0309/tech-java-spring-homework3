CREATE TABLE IF NOT EXISTS user_table
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    favorite_ramen_id INT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS ramen_table
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    price INT,
    place VARCHAR(100),
    PRIMARY KEY(id)
);

