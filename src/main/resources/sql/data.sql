INSERT INTO user_table(name, age, favorite_ramen_id, live_in_city_id) VALUES
                                                          ("Yamada", 24, 1, 1),
                                                          ("Takeshi", 35, 3, 2),
                                                          ("Suzuki", 18, 2, 3);

INSERT INTO ramen_table(name, price) VALUES
    ("Iekei", 700),
    ("Tonkotsu", 800),
    ("Shoyu", 750),
    ("Miso", 750);

INSERT INTO place_table(name) VALUES
                                ("Yokohama"),
                                ("Tokyo"),
                                ("Hukuoka");

INSERT INTO topping_table(name, price, ramen_id) VALUES
    ("Nori", 50, 1),
    ("Tamago", 100, 1),
    ("Menma", 50, 2),
    ("Negi", 50, 3),
    ("Chashu", 100, 3),
    ("Nori", 50, 4),
    ("Tamago", 100, 4);