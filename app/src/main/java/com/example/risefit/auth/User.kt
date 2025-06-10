package com.example.risefit.auth

data class User(
    val username: String,
    val password: String,
    val role: String // "coach" или "athlete"
) 