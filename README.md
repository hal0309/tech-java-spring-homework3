# SQL + SpringBoot 演習

SpringBoot と SQL を使って、データベースを操作する演習です。

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