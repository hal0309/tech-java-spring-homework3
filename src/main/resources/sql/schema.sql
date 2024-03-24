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
    place_id INT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS place_table
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS topping_table
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    price INT,
    ramen_id INT NOT NULL,
    PRIMARY KEY(id)
);

