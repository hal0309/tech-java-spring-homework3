**課題1**
~~~
mysql> select * from user left outer join ramen on user.favorite_ramen_id = ramen.id;
+----+-----------+------+-------------------+------+-----------+-------+----------+
| id | name      | age  | favorite_ramen_id | id   | shop_name | price | place_id |
+----+-----------+------+-------------------+------+-----------+-------+----------+
|  1 | Yamada    |   24 |                 1 |    1 | Iekei     |   800 |        1 |
|  2 | Takeshi   |   35 |                 3 |    3 | Tonkotsu  |   850 |        3 |
|  3 | Suzuki    |   18 |                 2 |    2 | Ziroukei  |   900 |        2 |
|  4 | Tanaka    |   29 |                 4 | NULL | NULL      |  NULL |     NULL |
|  5 | Sato      |   22 |                 1 |    1 | Iekei     |   800 |        1 |
|  6 | Nakamura  |   31 |                 3 |    3 | Tonkotsu  |   850 |        3 |
|  7 | Kobayashi |   27 |                 2 |    2 | Ziroukei  |   900 |        2 |
|  8 | Kato      |   20 |                 4 | NULL | NULL      |  NULL |     NULL |
|  9 | Watanabe  |   33 |                 1 |    1 | Iekei     |   800 |        1 |
| 10 | Ito       |   26 |                 3 |    3 | Tonkotsu  |   850 |        3 |
+----+-----------+------+-------------------+------+-----------+-------+----------+
~~~


**課題2**
~~~
mysql> select * from user left outer join ramen on user.favorite_ramen_id = ramen.id left outer join place on ramen.place_id = place.id;
+----+-----------+------+-------------------+------+-----------+-------+----------+------+----------+
| id | name      | age  | favorite_ramen_id | id   | shop_name | price | place_id | id   | name     |
+----+-----------+------+-------------------+------+-----------+-------+----------+------+----------+
|  1 | Yamada    |   24 |                 1 |    1 | Iekei     |   800 |        1 |    1 | Yokohama |
|  2 | Takeshi   |   35 |                 3 |    3 | Tonkotsu  |   850 |        3 |    3 | Hukuoka  |
|  3 | Suzuki    |   18 |                 2 |    2 | Ziroukei  |   900 |        2 |    2 | Mita     |
|  4 | Tanaka    |   29 |                 4 | NULL | NULL      |  NULL |     NULL | NULL | NULL     |
|  5 | Sato      |   22 |                 1 |    1 | Iekei     |   800 |        1 |    1 | Yokohama |
|  6 | Nakamura  |   31 |                 3 |    3 | Tonkotsu  |   850 |        3 |    3 | Hukuoka  |
|  7 | Kobayashi |   27 |                 2 |    2 | Ziroukei  |   900 |        2 |    2 | Mita     |
|  8 | Kato      |   20 |                 4 | NULL | NULL      |  NULL |     NULL | NULL | NULL     |
|  9 | Watanabe  |   33 |                 1 |    1 | Iekei     |   800 |        1 |    1 | Yokohama |
| 10 | Ito       |   26 |                 3 |    3 | Tonkotsu  |   850 |        3 |    3 | Hukuoka  |
+----+-----------+------+-------------------+------+-----------+-------+----------+------+----------+
~~~


**課題3**
~~~
mysql> select user.id,age,favorite_ramen_id,shop_name, price,place.name AS place_name,place.id from user left outer join ramen on user.favorite_ramen_id = ramen.id left outer join place on ramen.place_id = place.id;
+----+------+-------------------+-----------+-------+------------+------+
| id | age  | favorite_ramen_id | shop_name | price | place_name | id   |
+----+------+-------------------+-----------+-------+------------+------+
|  1 |   24 |                 1 | Iekei     |   800 | Yokohama   |    1 |
|  2 |   35 |                 3 | Tonkotsu  |   850 | Hukuoka    |    3 |
|  3 |   18 |                 2 | Ziroukei  |   900 | Mita       |    2 |
|  4 |   29 |                 4 | NULL      |  NULL | NULL       | NULL |
|  5 |   22 |                 1 | Iekei     |   800 | Yokohama   |    1 |
|  6 |   31 |                 3 | Tonkotsu  |   850 | Hukuoka    |    3 |
|  7 |   27 |                 2 | Ziroukei  |   900 | Mita       |    2 |
|  8 |   20 |                 4 | NULL      |  NULL | NULL       | NULL |
|  9 |   33 |                 1 | Iekei     |   800 | Yokohama   |    1 |
| 10 |   26 |                 3 | Tonkotsu  |   850 | Hukuoka    |    3 |
+----+------+-------------------+-----------+-------+------------+------+
~~~