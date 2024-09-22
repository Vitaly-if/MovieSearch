package com.example.main_movies.domain

sealed class ServerResponseError : Exception() {
    object ServerException : ServerResponseError()
    object BadRequestException : ServerResponseError()
}