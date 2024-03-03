# SQL + SpringBoot 演習

SpringBoot と SQL を使って、データベースを操作する演習です。


## 2024年3月3日 課題
今週は内部結合・外部結合をマスターします。ただし、javaは使いません。

### 1.事前準備
shellから適当なユーザでmysqlにログインし、データベース`homework3_join`を作成したのち、
3つのテーブル、`user`、`ramen`、`place`を作成してください。
その際、プロパティが以下のようになるようにしてください。

~~~
mysql> show tables;
+--------------------------+
| Tables_in_homework3_join |
+--------------------------+
| place                    |
| ramen                    |
| user                     |
+--------------------------+
~~~
~~~
mysql> show columns from user;
+-------------------+--------------+------+-----+---------+----------------+
| Field             | Type         | Null | Key | Default | Extra          |
+-------------------+--------------+------+-----+---------+----------------+
| id                | int          | NO   | PRI | NULL    | auto_increment |
| name              | varchar(100) | YES  |     | NULL    |                |
| age               | int          | YES  |     | NULL    |                |
| favorite_ramen_id | int          | YES  |     | NULL    |                |
+-------------------+--------------+------+-----+---------+----------------+
~~~
~~~
mysql> show columns from ramen;
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id        | int          | NO   | PRI | NULL    | auto_increment |
| shop_name | varchar(100) | YES  |     | NULL    |                |
| price     | int          | YES  |     | NULL    |                |
| place_id  | int          | YES  |     | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
~~~
~~~
mysql> show columns from place;
+-------+--------------+------+-----+---------+----------------+
| Field | Type         | Null | Key | Default | Extra          |
+-------+--------------+------+-----+---------+----------------+
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(100) | YES  |     | NULL    |                |
+-------+--------------+------+-----+---------+----------------+
~~~



### 2.レコードの追加
以下のようにレコードを追加してください。



~~~
INSERT INTO user(name, age, favorite_ramen_id) VALUES
    ("Yamada", 24, 1),
    ("Takeshi", 35, 3),
    ("Suzuki", 18, 2),
    ("Tanaka", 29, 4),
    ("Sato", 22, 1),
    ("Nakamura", 31, 3),
    ("Kobayashi", 27, 2),
    ("Kato", 20, 4),
    ("Watanabe", 33, 1),
    ("Ito", 26, 3);
~~~

~~~
INSERT INTO ramen(shop_name, price, place_id) VALUES
    ("Iekei", 800, 1),
    ("Ziroukei", 900, 2),
    ("Tonkotsu", 850, 3);
~~~

~~~
INSERT INTO place(name) VALUES
    ("Yokohama"),
    ("Mita"),
    ("Hukuoka");
~~~


### 3. 結合(1段階)

以下は、`user`テーブルと`ramen`テーブルの内部結合とその結果です。

~~~
mysql> select * from user join ramen on user.favorite_ramen_id = ramen.id;
+----+-----------+------+-------------------+----+-----------+-------+----------+
| id | name      | age  | favorite_ramen_id | id | shop_name | price | place_id |
+----+-----------+------+-------------------+----+-----------+-------+----------+
|  1 | Yamada    |   24 |                 1 |  1 | Iekei     |   800 |        1 |
|  2 | Takeshi   |   35 |                 3 |  3 | Tonkotsu  |   850 |        3 |
|  3 | Suzuki    |   18 |                 2 |  2 | Ziroukei  |   900 |        2 |
|  5 | Sato      |   22 |                 1 |  1 | Iekei     |   800 |        1 |
|  6 | Nakamura  |   31 |                 3 |  3 | Tonkotsu  |   850 |        3 |
|  7 | Kobayashi |   27 |                 2 |  2 | Ziroukei  |   900 |        2 |
|  9 | Watanabe  |   33 |                 1 |  1 | Iekei     |   800 |        1 |
| 10 | Ito       |   26 |                 3 |  3 | Tonkotsu  |   850 |        3 |
+----+-----------+------+-------------------+----+-----------+-------+----------+
~~~

これは、`user`の`favorite_ramen_id`をもとに、`id`が一致する`ramen`を取得しています。

内部結合の特性上、`ramen`が取得できない`user`は表示されません。
これでは困るので、`ramen`の有無に関わらず全ての`user`を取得するために他の結合方法を行ってください。

その際に使用したSQL文とその結果を、`RESULT.md`の**課題1**の枠に添付してください。


### 4. 結合(2段階)

上記では、`user`と`ramen`を結び付けた。次はそれに加えて、`ramen`の`place_id`と`place`の`id`を結びつけつ事で、
`user`と`ramen`と`place`の3つのテーブルが繋がった状態にしてください。

その際に使用したSQL文とその結果を、`RESULT.md`の**課題2**の枠に添付してください。


### 5. 整形

上記の結果では、恐らく重複した名称や不必要な情報が見られる。そのため以下の対策を行ってください。
- `favorite_ramen_id`と`ramen`の`id`は同じ情報なので、片方削除
- `place_id`とplace`の`id`は同じ情報なので、片方削除
- `name`というカラム名が重複しているので、`place`の`name`を`place_name`に変更

上記の対策を行ったSQL文とその結果を、`RESULT.md`の**課題3**の枠に添付してください。


## 2024年2月18日 課題

### 1. 現状確認

`*Controller`内部のエンドポイントの名前を少し変えました。
Postmanを使って、`User`の取得と追加ができるか確認してください。

※gradleを追記したのでsyncする必要があります。

### 2. Repositoryの実装

`user_table`を操作するための`UserRepository`を作成しておきました。
参考にしつつ、`ramen_table`テーブルを操作するための`RamenRepository`を作成してください。

### 3. Serviceの実装

`UserService`と`UserServiceImpl`を作成しておきました。
参考にしつつ、`RamenService`と`RamenServiceImpl`を作成してください。

### 4. Controllerのリファクタリング

`RamenController`を、serviceを使って`ramen_table`を操作できるようにリファクタリングしてください。

### 5. 機能追加

現状、各種Controllerには、`insert`と`findAll`の機能しかありません。

これに追加して様々な機能を追加しましょう。以下2つを必須としますが、他にも1つ以上自身で考えて実装してください。

* `find`  
  `id`を指定して、entityを取得する機能


* `delete`  
  `id`を指定して、entityを削除する機能










## 2024年2月11日 課題

### 1. 初期設定

`application.properties`ファイルが以下のようになっていることを確認してください。
ここにMySQLのための各種設定が記載されています。
~~~
spring.datasource.url=jdbc:mysql://localhost:3306/homework3
spring.datasource.username=tester
spring.datasource.password=000000
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:sql/schema.sql
spring.sql.init.encoding=utf-8
~~~

ここで、コマンドプロンプトを開いて、以下の3つの作業を実施してください。
1. `homework3`という名前のデータベースを作成
2. `tester`という名前でユーザーを作成(パスワードは`000000`)
3. `tester`ユーザーに`homework3`データベースへのアクセス権を付与



### 2. 現状確認

`UserController`クラスの内容を確認し、postmanを使って、
`getUser`メソッドと、`insertUser`メソッドを呼び出してください。

`getUser`メソッドでは、`homework3`データベースの`user`テーブルから全てのデータを取得しリストで返します。

`insertUser`メソッドでは、`homework3`データベースの`user`テーブルにデータを追加します。
`insertUser`メソッドを呼び出す際のリクエストボディは以下のようなJsonデータとしてください。

~~~
{
    "name": "Yamada",
    "age": 12
}
~~~


### 3. 類似機能の作成

今回はプロパティとして、`name`と`age`を持つ`user`テーブルを操作する機能を作成しました。

新たに、`name`、`price`、`place`をもつ`ramen`テーブルを作成し、
それらを操作する機能をSpring Bootで実装してください。  
その際、適宜必要なControllerやmodelを作成してください。


### 4. 機能修正

現状、`isnertUser`メソッドを行うとuserが追加されていきますが、SpringBootのアプリケーションを再起動すると、追加されたデータが消えてしまいます。  
再起動を行ってもデータが消えないようにしてください。

ヒント  
`application.properties`ファイルに以下の設定があると思います。
~~~
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:sql/schema.sql
~~~

これは、起動時に毎回`sql/schema.sql`ファイルを実行するという設定です。  
`schema.sql`ファイルの中身を工夫すると、再起動してもデータが消えないようになります。