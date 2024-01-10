package com.miraelDev.vauma.domain.repository

import com.miraeldev.models.auth.AuthState
import com.miraeldev.user.UserEmail
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun checkAuthState()

    suspend fun getDarkTheme(): Boolean

    fun getUserStatus(): Flow<AuthState>

    suspend fun getLocalUser(): UserEmail

    suspend fun setDarkTheme(isDarkTheme: Boolean)

}