package com.miraeldev.signin.data.repositories

interface SignInRepository {

    suspend fun signIn(email: String, password: String): Boolean

    suspend fun loginWithVk(accessToken: String, userId: String, email: String?)

    suspend fun getUserEmail(): String

    suspend fun logInWithGoogle(idToken: String)
}