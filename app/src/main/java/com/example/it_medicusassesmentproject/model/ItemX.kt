package com.example.it_medicusassesmentproject.model

data class ItemX(
    val event: List<Event>,
    val id: String,
    val name: String,
    val protocolProfileBehavior: ProtocolProfileBehavior,
    val request: Request,
    val response: List<Response>
)