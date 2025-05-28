package com.levox.game_archive.domain

open class Either<T>() {

    class Success<T>(val data: T) : Either<T>()

    class Error<T>(val message: String) : Either<T>()

    class Loading<T> : Either<T>()
}