package com.zhxh.codeproj.kotlin.datastructure.utils

sealed class Result<out T> {
    abstract val value: T
}

object Failure : Result<Nothing>() {
    override val value: Nothing
        get() = throw IllegalStateException("Failure")

    override fun toString(): String = "Failure"

    override fun hashCode(): Int = -1
}

class Success<out T>(override val value: T) : Result<T>() {
    override fun toString(): String = "Success($value)"

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other !is Success<*> -> false
        else -> value == other.value
    }

    override fun hashCode(): Int = value?.hashCode() ?: 0
}


inline infix fun <T> Result<T>?.ifSuccess(block: (T) -> Unit): Result<T>? =
    (this as? Success)?.apply { block(value) }

inline infix fun <T> Result<T>?.ifFailure(block: () -> Unit): Result<T>? =
    (this as? Failure)?.apply { block() }

fun <T> Result<T>.takeIfSuccess(): T? = (this as? Success)?.value

fun <T> Result<T>.toSuccess(valueIfFailure: T): Success<T> =
    (this as? Success<T>) ?: Success(valueIfFailure)