package com.example.it_medicusassesmentproject.newTest

data class LoginResponse2(
    val success: Boolean,
    val key: String,
    val message: Message?
)

data class Message(
    val body: List<String>
)