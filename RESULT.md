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
mysql> select user.name, age, favorite_ramen_id, shop_name, price, place_id,place.name from user left outer join ramen o
n user.favorite_ramen_id = ramen.id left outer join place on ramen.place_id = place.id;
+-----------+------+-------------------+-----------+-------+----------+----------+
| name      | age  | favorite_ramen_id | shop_name | price | place_id | name     |
+-----------+------+-------------------+-----------+-------+----------+----------+
| Yamada    |   24 |                 1 | Iekei     |   800 |        1 | Yokohama |
| Takeshi   |   35 |                 3 | Tonkotsu  |   850 |        3 | Hukuoka  |
| Suzuki    |   18 |                 2 | Ziroukei  |   900 |        2 | Mita     |
| Tanaka    |   29 |                 4 | NULL      |  NULL |     NULL | NULL     |
| Sato      |   22 |                 1 | Iekei     |   800 |        1 | Yokohama |
| Nakamura  |   31 |                 3 | Tonkotsu  |   850 |        3 | Hukuoka  |
| Kobayashi |   27 |                 2 | Ziroukei  |   900 |        2 | Mita     |
| Kato      |   20 |                 4 | NULL      |  NULL |     NULL | NULL     |
| Watanabe  |   33 |                 1 | Iekei     |   800 |        1 | Yokohama |
| Ito       |   26 |                 3 | Tonkotsu  |   850 |        3 | Hukuoka  |
+-----------+------+-------------------+-----------+-------+----------+----------+
~~~


**課題3**
~~~
mysql> select user.id,user.name,age,favorite_ramen_id,shop_name, price,place.name AS place_name,place.id from user left
outer join ramen on user.favorite_ramen_id = ramen.id left outer join place on ramen.place_id = place.id;
+----+-----------+------+-------------------+-----------+-------+------------+------+
| id | name      | age  | favorite_ramen_id | shop_name | price | place_name | id   |
+----+-----------+------+-------------------+-----------+-------+------------+------+
|  1 | Yamada    |   24 |                 1 | Iekei     |   800 | Yokohama   |    1 |
|  2 | Takeshi   |   35 |                 3 | Tonkotsu  |   850 | Hukuoka    |    3 |
|  3 | Suzuki    |   18 |                 2 | Ziroukei  |   900 | Mita       |    2 |
|  4 | Tanaka    |   29 |                 4 | NULL      |  NULL | NULL       | NULL |
|  5 | Sato      |   22 |                 1 | Iekei     |   800 | Yokohama   |    1 |
|  6 | Nakamura  |   31 |                 3 | Tonkotsu  |   850 | Hukuoka    |    3 |
|  7 | Kobayashi |   27 |                 2 | Ziroukei  |   900 | Mita       |    2 |
|  8 | Kato      |   20 |                 4 | NULL      |  NULL | NULL       | NULL |
|  9 | Watanabe  |   33 |                 1 | Iekei     |   800 | Yokohama   |    1 |
| 10 | Ito       |   26 |                 3 | Tonkotsu  |   850 | Hukuoka    |    3 |
+----+-----------+------+-------------------+-----------+-------+------------+------+
~~~