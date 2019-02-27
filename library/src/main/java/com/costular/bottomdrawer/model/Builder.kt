package com.costular.bottomdrawer.model

abstract class Builder<out T> {

    abstract fun build(): T

}