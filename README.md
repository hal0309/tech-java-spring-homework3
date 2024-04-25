# SQL + SpringBoot 演習

SpringBoot と SQL を使って、データベースを操作する演習です。

## 2024年4月21日 課題
今回は、serviceに対するテストクラスの作成と、streamの再演習です。

### 3. Streamの演習
`UserController`内に以下のエンドポイントを作成してください。

`liveInCityMap`  
レスポンスが以下のように、住んでいる地域ごとに住んでいる人の名前のリストを持つMapを返すエンドポイントです。
~~~ json 
{
    "Tokyo": [
        "Watanabe",
        "Tanaka",
        "Takeshi",
        "Yoshioka"
    ],
    "Yokohama": [
        "Yamada"
    ],
    "Hukuoka": [
        "Nakano",
        "Suzuki"
    ]
}
~~~

`noriLikeUser`
レスポンスが以下のように、好きなラーメンのトッピングにノリを含むユーザの名前のリストを返すエンドポイントです。

~~~ json
[
    "Nakano",
    "Watanabe",
    "Yamada"
]
~~~

## 2024年4月14日 課題
今回は、streamやlambdaの復習です。

### 0. 今回の課題について
今回の課題は、以下に示す新しいエンドポイントを作成し、レスポンスが完全に一致している事が条件です。

**尚、streamの演習のため、for文の使用は禁止です。**

### 1. ラーメンの好みによる分類

`UserController`内にラーメンの好みを分類するエンドポイント(`ramenLikeMap`)を実装してください。

ラーメンの名称をキー、そのラーメンを好むユーザの名前のリストを値として持つMapを返すエンドポイントです。
実装する際、以下の条件を満たしてください。

* `"Suzuki(18)"`のように名前と年齢がセットになっていること
* 名前が年齢の高い順に並んでいること
* ラーメンの名称がアルファベット順に並んでいること

上記を満たせば以下と完全に一致しているはずです。

~~~ json
{
    "Iekei": [
        "Yamada(24)",
        "Watanabe(18)",
        "Nakano(12)"
    ],
    "Miso": [],
    "Shoyu": [
        "Takeshi(35)",
        "Tanaka(31)"
    ],
    "Tonkotsu": [
        "Yoshioka(42)",
        "Suzuki(18)"
    ]
}
~~~


### 2. 地元のラーメンが好きな人々

`UserController`内に一番好きなラーメンが地元にある幸せな人々を列挙するエンドポイント(`liveWithRamen`)を実装してください。

結果が以下と完全に一致するようにしてください。
見えていない条件は、年齢が小さい順に並んでいることです。

~~~ json
[
    "Hukuokaに住んでいるSuzukiさんは地元のTonkotsuラーメンが好きです。",
    "Yokohamaに住んでいるYamadaさんは地元のIekeiラーメンが好きです。",
    "Tokyoに住んでいるTanakaさんは地元のShoyuラーメンが好きです。",
    "Tokyoに住んでいるTakeshiさんは地元のShoyuラーメンが好きです。"
]
~~~
テンプレート
~~~ java
"%sに住んでいる%sさんは地元の%sラーメンが好きです。"
~~~


## 2024年4月6日 課題
今回は、Junitでのテストコードを実装します。

### 0. 今回の課題について
今回の課題は、Junitを用いて`RamenController`のテストコードを実装することです。
以下の条件が満たされればOKです。
* 実行時に毎回`ramen_table`と`topping_table`が初期化され、毎回同じ状態でテストが行われる
* `RamenController`に対して以下のテストが行われる
    * `findAll`メソッドを実行時、ステータスコード200が返ってくる
    * `findAll`メソッドを実行時、4つのラーメンが取得できる
    * `findAll`メソッドを実行時、1つのラーメンに対して2つのトッピングが取得でき、名称が`Nori`と`Tamago`で、価格がそれぞれ`50`と`100`である。
    * `findAll`メソッドを実行時、1つ目のラーメンのトッピング数と2つ目のラーメンのトッピング数が異なる
  
### 1. 現状確認
`application.properties`に行が追加されており、`data.sql`が実行される設定になっていることを確認してください。

その上で、`schema.sql`と`data.sql`を見ると、毎回`user_table`と`place_table`が初期化され、初期値として幾つかデータが追加される
設定になっていることを確認してください。

また、`UserControllerTest`クラスには、`UserController`のテストコードが実装されています。
これを参考にしてみてください。

### 2. DBの初期化処理の実装
`schema.sql`で`ramen_table`と`topping_table`を毎回初期化する処理を実装してください。

その上で、初期化したテーブルに毎回同じデータが存在するようにしてください。  
以下のサンプルを用いてもOKです。 

`ramen_table`
~~~ MySQL
INSERT INTO ramen_table(name, price) VALUES
    ("Iekei", 700),
    ("Tonkotsu", 800),
    ("Shoyu", 750),
    ("Miso", 750);
~~~
`topping_table`
~~~ MySQL
INSERT INTO topping_table(name, price, ramen_id) VALUES
    ("Nori", 50, 1),
    ("Tamago", 100, 1),
    ("Menma", 50, 2),
    ("Negi", 50, 3),
    ("Chashu", 100, 3),
    ("Nori", 50, 4),
    ("Tamago", 100, 4);
~~~

### 3. テストコードの実装
課題0でも挙げましたが、`RamenController`に対して以下のテストコードを実装してください。
* `findAll`メソッドを実行時、ステータスコード200が返ってくる
* `findAll`メソッドを実行時、4つのラーメンが取得できる
* `findAll`メソッドを実行時、1つのラーメンに対して2つのトッピングが取得でき、名称が`Nori`と`Tamago`で、価格がそれぞれ`50`と`100`である。
* `findAll`メソッドを実行時、1つ目のラーメンのトッピング数と2つ目のラーメンのトッピング数が異なる

`UserController`のテストコードを参考にしてみてください。

## 2024年3月31日 課題
今回は結合に変わってサブクエリを使ってみましょう。

### 0. 今回の課題について
今回は、サブクエリを用いてSQLの実装を行います。

今まで外部結合を用いていた処理を全てサブクエリに変更してみましょう。

### 1. 現状確認
現状結合を用いているのは2箇所です。

RamenRepository
~~~SQL
SELECT ramen_table.id, ramen_table.name, price, place_table.name AS place_name FROM ramen_table
LEFT JOIN place_table ON ramen_table.place_id = place_table.id
~~~

UserRepository
~~~SQL
SELECT user_table.id, user_table.name, age, ramen_table.name AS favorite_ramen_name, place_table.name as live_in_city_name FROM user_table
LEFT JOIN ramen_table ON user_table.favorite_ramen_id = ramen_table.id
LEFT JOIN place_table ON user_table.live_in_city_id = place_table.id
~~~

### 2. サブクエリの実装
上記のSQL文をサブクエリを用いて書き換えてください。


## 2024年3月24日 課題
今回は前回の復習に加え、1対多の関係を持つEntityを作成します。

### 0. 今回の課題について
今回は最終的に以下の要件が満たされていれば良いです。
* ユーザを取得した際に`liveInCityName`(住んでいる地域)が取得できる
* 上記のプロパティを取得するために適切に外部結合が行われている
* ラーメンに対してトッピングが存在し、1対多の関係が成り立っている

### 1. 現状確認
`UserRequest`クラスに、`liveInCityId`という変数を追加しました。これは、`place_table`の`id`と結びつけるためのものです。  
また、`UserResponse`クラスに、`liveInCityName`という変数を追加しました。

### 2. APIの構造見直し
`liveInCityName`が正しく取得できるようにSQL文等を見直してください。

### 3. トッピングの追加
ラーメン屋に対して、その店で可能なトッピングの一覧を取得できるようにしたいです。 
手法は問わないので、トッピング一覧を取得する機能を考えてください。

例えば、ラーメンを取得した際に以下のような形で取得できると良いです。
~~~
[
    {
        "id": 1,
        "name": "Iekei",
        "price": 100,
        "placeName": "Yokohama",
        "toppingList": [
            {
                "id": 1,
                "name": "Chashu",
                "price": 100
            },
            {
                "id": 2,
                "name": "Nori",
                "price": 50
            }
        ]
    },
    ...
]
~~~

ラーメンに対してトッピングを追加する方法は問いません。余裕があればAPIに実装しても良いですし、
難しければ追加はSQL文のみで行っても良いです。




ヒント
* ラーメン1つに対して、トッピングは複数存在します。
* `ramen_table`内に保存するのは難しいので、`topping_table`を作成すると良いでしょう。
* SQL文で結合するのではなく、別々に取得してServiceで結合するのが良いかもしれません。


## 2024年3月17日 課題
今回はSpringBoot上で内部結合を行いデータを取得します。

### 0. 今回の課題について
今回は最終的に以下の要件が満たされていれば良いです。
* `ramen_table`が`place`の代わりに`place_id`を持つ
* `place_table`が存在し、`id`と`name`を持つ
* APIで`ramen_table`に対してinsertする際は、`placeId`を指定する
* APIで`ramen_table`に対するfindAllを実行した際は、`placeId`ではなく`placeName`を取得する(外部結合により`place_table`の該当する`name`を取得する)

それが満たされれば以下の説明は読まなくてOKです。

### 1. 現状確認
今回は、DBの構造が前回少し変わっています。そのため、`schema.sql`にも変更を加えました。
`schema.sql`の内部はtableが存在する状態だと実行されないので、一度手動でテーブルを削除した上で課題を始めてください。  
(課題の中で、自身でschemaを編集することがあると思いますが、その際も手動でテーブルを削除することを忘れないように気を付けてください。)


まず、`UserRequest`クラスを見てください。これは、**DBに対してリクエストを行う為のEntity**です。
代表的な処理としては、insertです。insertする際は、`name`、`age`、`favoriteRamenId`を指定します。

次に、`UserResponse`クラスを見てください。これは、**DBからのレスポンスを受け取る為のEntity**です。
代表的な処理としてはfindAllです。findAllした際は、`id`、`name`、`age`、`favoriteRamenName`(結合により`ramen_table`から`favoriteRamenId`と一致したカラムの`name`を持ってきます)を受け取ります。

今回はこのように、DBからのレスポンスを受け取る際は結合によりプロパティが増えるため、RequestとResponseでEntityを2種類用意しました。

### 2. テーブルの構造見直し
現状、`ramen_table`は以下のように場所を文字列で保持しています。
~~~
private final String place;
~~~
これを、以下のように`place_id`という数値で保持し、`place_table`という別のテーブルの`id`と結びつけるようにしてください。
~~~
private final int placeId;
~~~

それを実現するために、`ramen_table`の構造を変更してください。  
その際、`place_table`を作成し、`id`と`name`を持つようにしてください。  
また、`place_table`に対してのエンドポイント(Controller等)は作成しないで良いのですが、何も中身が無いので自身で適当にデータを追加してください。

また、それらの変更に伴い、`schema.sql`も更新してください。


### 3. APIの構造見直し
まず、RequestとResponseでプロパティが異なるため、それぞれのEntityを作成し、
ソースコード内で、作成した2種類のEntityを使い分けましょう。

そのために、`Repository`や`Service`、`Controller`をリファクタリングする必要があると思います。
`User`に対しての例を参考に実装してみましょう。

最終的にpostmanでramenの`findAll`を叩くと、以下のようなレスポンスが返ってくるようになるはずです。
~~~
[
    {
        "id": 1,
        "name": "Iekei",
        "price": 700,
        "place": "Yokohama"
    },
    ...
]
~~~




## 2024年3月3日 課題
今週は内部結合・外部結合をマスターします。ただし、javaは使いません。

### 1.事前準備
shellから適当なユーザでmysqlにログインし、データベース`homework3_join`を作成したのち、
3つのテーブル、`userRequest`、`ramenRequest`、`place`を作成してください。
その際、プロパティが以下のようになるようにしてください。

~~~
mysql> show tables;
+--------------------------+
| Tables_in_homework3_join |
+--------------------------+
| place                    |
| ramenRequest                    |
| userRequest                     |
+--------------------------+
~~~
~~~
mysql> show columns from userRequest;
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
mysql> show columns from ramenRequest;
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
INSERT INTO userRequest(name, age, favorite_ramen_id) VALUES
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
INSERT INTO ramenRequest(shop_name, price, place_id) VALUES
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

以下は、`userRequest`テーブルと`ramenRequest`テーブルの内部結合とその結果です。

~~~
mysql> select * from userRequest join ramenRequest on userRequest.favorite_ramen_id = ramenRequest.id;
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

これは、`userRequest`の`favorite_ramen_id`をもとに、`id`が一致する`ramenRequest`を取得しています。

内部結合の特性上、`ramenRequest`が取得できない`userRequest`は表示されません。
これでは困るので、`ramenRequest`の有無に関わらず全ての`userRequest`を取得するために他の結合方法を行ってください。

その際に使用したSQL文とその結果を、`RESULT.md`の**課題1**の枠に添付してください。


### 4. 結合(2段階)

上記では、`userRequest`と`ramenRequest`を結び付けた。次はそれに加えて、`ramenRequest`の`place_id`と`place`の`id`を結びつけつ事で、
`userRequest`と`ramenRequest`と`place`の3つのテーブルが繋がった状態にしてください。

その際に使用したSQL文とその結果を、`RESULT.md`の**課題2**の枠に添付してください。


### 5. 整形

上記の結果では、恐らく重複した名称や不必要な情報が見られる。そのため以下の対策を行ってください。
- `favorite_ramen_id`と`ramenRequest`の`id`は同じ情報なので、片方削除
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

`getUser`メソッドでは、`homework3`データベースの`userRequest`テーブルから全てのデータを取得しリストで返します。

`insertUser`メソッドでは、`homework3`データベースの`userRequest`テーブルにデータを追加します。
`insertUser`メソッドを呼び出す際のリクエストボディは以下のようなJsonデータとしてください。

~~~
{
    "name": "Yamada",
    "age": 12
}
~~~


### 3. 類似機能の作成

今回はプロパティとして、`name`と`age`を持つ`userRequest`テーブルを操作する機能を作成しました。

新たに、`name`、`price`、`place`をもつ`ramenRequest`テーブルを作成し、
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