machine-learning
================

machine-learning java wrapper project

使い方は[こちらを](https://github.com/pollseed/machine-learning/blob/master/jp/com/pollseed/wrapper/test/HotToUse.java)

# 各クラスの役割

* Evaluator : Implementations of this interface evaluate the quality of a Recommender's recommendations.
* User : Implementations of this interface define a notion of similarity between two users. Implementations should return values in the range -1.0 to 1.0, with 1.0 representing perfect similarity.
* Item : Implementations of this interface define a notion of similarity between two items. Implementations should return values in the range -1.0 to 1.0, with 1.0 representing perfect similarity.
