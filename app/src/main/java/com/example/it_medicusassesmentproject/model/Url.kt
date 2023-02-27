package com.example.it_medicusassesmentproject.model

data class Url(
    val host: List<String>,
    val path: List<String>,
    val protocol: String,
    val raw: String
)