package me.jeffreychang.walmarttakehome.util

sealed class ResultOf<out T> {

    data class Success<out R>(val value: R) : ResultOf<R>()

    data class Failure(
        val message: String?,
        val throwable: Throwable
    ) : ResultOf<Nothing>()

    fun <Domain> toDomain(mapper: (value: T) -> Domain): ResultOf<Domain> {
        return when (this) {
            is Success -> {
                Success(mapper(value))
            }

            is Failure -> {
                Failure(message, throwable)
            }
        }
    }
}