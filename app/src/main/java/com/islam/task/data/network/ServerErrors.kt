package com.islam.task.data.network

data class ServerErrors(
    val error: String, // invalid_grant
    val error_description: String // Invalid username or password!
)