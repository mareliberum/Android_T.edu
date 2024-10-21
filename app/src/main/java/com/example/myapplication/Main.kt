package com.example.myapplication

class Main {
    // Общий интерфейс для всех животных
    interface Animal {
        val weight: Double
        val age: Int
    }

    // Интерфейс для собак
    interface Dog : Animal {
        val biteType: BiteType
    }

    // Перечисление для типов прикуса у собак
    enum class BiteType {
        STRAIGHT, OVERBITE, UNDERBITE
    }

    // Интерфейс для кошек
    interface Cat : Animal {
        val behaviorType: BehaviorType
    }

    // Перечисление для типов поведения у кошек
    enum class BehaviorType {
        ACTIVE, PASSIVE
    }

    // Реализация для породы собак Хаски
    class Husky(
        override val weight: Double,
        override val age: Int,
        override val biteType: BiteType
    ) : Dog

    // Реализация для породы собак Корги
    class Corgi(
        override val weight: Double,
        override val age: Int,
        override val biteType: BiteType
    ) : Dog

    // Реализация для породы кошек Шотландская
    class ScottishFold(
        override val weight: Double,
        override val age: Int,
        override val behaviorType: BehaviorType
    ) : Cat

    // Реализация для породы кошек Сиамская
    class Siamese(
        override val weight: Double,
        override val age: Int,
        override val behaviorType: BehaviorType
    ) : Cat

    // Класс зоомагазина
    class PetStore {
        fun identifyAnimal(breed: String, weight: Double, age: Int): Animal {
            return when (breed.lowercase()) {
                "husky" -> Husky(weight, age, BiteType.STRAIGHT) // Здесь можно изменить тип прикуса по необходимости
                "corgi" -> Corgi(weight, age, BiteType.OVERBITE) // Здесь можно изменить тип прикуса по необходимости
                "scottishfold" -> ScottishFold(weight, age, BehaviorType.PASSIVE)
                "siamese" -> Siamese(weight, age, BehaviorType.ACTIVE)
                else -> throw IllegalArgumentException("Unknown breed: $breed")
            }
        }
    }


}