package com.example.it_medicusassesmentproject.model

data class Response(
    val _postman_previewlanguage: String,
    val body: String,
    val code: Int,
    val cookie: List<Any>,
    val header: List<HeaderX>,
    val id: String,
    val name: String,
    val originalRequest: OriginalRequest,
    val responseTime: Any,
    val status: String
)