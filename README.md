machine-learning
================

machine-learning java wrapper project

使い方は[こちらを](https://github.com/pollseed/machine-learning/blob/master/jp/com/pollseed/wrapper/test/HotToUse.java)

# 各クラスの役割

* [Evaluator](https://github.com/pollseed/machine-learning/tree/master/jp/com/pollseed/wrapper/eval) : Implementations of this interface evaluate the quality of a Recommender's recommendations.

* [User](https://github.com/pollseed/machine-learning/tree/master/jp/com/pollseed/wrapper/user) : Implementations of this interface define a notion of similarity between two users. Implementations should return values in the range -1.0 to 1.0, with 1.0 representing perfect similarity.
* [Item](https://github.com/pollseed/machine-learning/tree/master/jp/com/pollseed/wrapper/item) : Implementations of this interface define a notion of similarity between two items. Implementations should return values in the range -1.0 to 1.0, with 1.0 representing perfect similarity.

# 必要なライブラリ

[Mahout](https://mahout.apache.org/)
* version：.9
* ルート：org.apache.mahout
* プロジェクト名：mahout-core

```
    <dependencies>
        <dependency>
            <groupId>org.apache.mahout</groupId>
            <artifactId>mahout-core</artifactId>
            <version>0.9</version>
        </dependency>
    </dependencies>
```    

## gradle使う場合

```
dependencies {
    testCompile 'org.apache.mahout:mahout-core:0.9'
}
```

* gradle eclipseプラグインインストール
* build.gradleを生成する
* 以下コンソールにコマンド

```
$ gradle build
$ gradle eclipse
```