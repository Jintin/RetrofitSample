package com.jintin.dynamicproxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface Animal {
    fun walk()

    fun eat(food: Any)
}

class Dog : Animal {
    override fun walk() {
        println("dog walk")
    }

    override fun eat(food: Any) {
        println("dog eat $food")
    }
}

class Cat : Animal {
    override fun walk() {
        println("cat walk")
    }

    override fun eat(food: Any) {
        println("cat eat $food")
    }
}

class AnimalProxy(private val animal: Animal) : Animal {
    override fun walk() {
        println("walk detect")
        animal.walk()
    }

    override fun eat(food: Any) {
        println("eat detect")
        animal.eat(food)
    }
}

class AnimalDynamicProxy : InvocationHandler {
    private var animal: Animal? = null

    fun bind(animal: Animal): Animal {
        this.animal = animal
        return Proxy.newProxyInstance(
            animal.javaClass.classLoader,
            animal.javaClass.interfaces,
            this
        ) as Animal
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        println("invoke method $method")
        println("invoke args $method")
        return method?.invoke(animal, *(args ?: arrayOfNulls<Any>(0)))
    }
}

fun main() {
    val animals = listOf<Animal>(Dog(), Cat())
    animals.forEach {
        it.walk()
        it.eat(Unit)
    }

    val dog = Dog()
    val proxy = AnimalProxy(dog)
    proxy.walk()
    proxy.eat(Unit)
    
    val cat = Cat()
    val dynamicProxy = AnimalDynamicProxy().bind(cat)
    dynamicProxy.walk()
    dynamicProxy.eat(Unit)

}
