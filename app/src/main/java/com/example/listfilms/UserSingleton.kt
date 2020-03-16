package com.example.listfilms

object UserSingleton {
    lateinit var email: String

    fun setUser(email: String) {
        this.email = email
    }

    fun getUser(): String {
        return this.email
    }
}
