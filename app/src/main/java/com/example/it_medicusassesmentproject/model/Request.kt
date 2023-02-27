package com.example.it_medicusassesmentproject.model

data class Request(
    val body: Body,
    val header: List<Header>,
    val method: String,
    val url: Url
)