package com.example.it_medicusassesmentproject.testingpurpose

class RegisterResponse{

    private val success = false
    private val message: String? = null

    fun isSuccess(): Boolean {
        return success
    }

    fun getMessage(): String? {
        return message
    }
}