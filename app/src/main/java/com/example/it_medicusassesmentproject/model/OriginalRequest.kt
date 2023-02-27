package com.example.it_medicusassesmentproject.model

data class OriginalRequest(
    val body: BodyX,
    val header: List<HeaderXX>,
    val method: String,
    val url: Url
)