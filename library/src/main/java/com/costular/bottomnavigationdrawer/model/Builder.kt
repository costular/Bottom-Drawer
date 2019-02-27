package com.costular.bottomnavigationdrawer.model

abstract class Builder<out T> {

    abstract fun build(): T

}