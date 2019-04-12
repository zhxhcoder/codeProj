package com.zhxh.codeproj.kotlin

/**
Created by zhxh on 2019/4/12

 */
interface Product {
    val name: String
}

class ProductA(override val name: String = "ProductA") : Product
class ProductB(override val name: String = "ProductB") : Product
interface Factory {
    fun makeProduct(): Product
}

class FactoryA : Factory {
    override fun makeProduct() = ProductA()
}

class FactoryB : Factory {
    override fun makeProduct() = ProductB()
}