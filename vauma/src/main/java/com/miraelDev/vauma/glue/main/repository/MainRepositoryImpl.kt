package com.miraelDev.vauma.glue.main.repository

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.miraelDev.vauma.domain.repository.MainRepository
import com.miraeldev.LocalUserDataRepository
import com.miraeldev.PreferenceDataStoreAPI
import com.miraeldev.UserAuthDataRepository
import com.miraeldev.UserDataRepository
import com.miraeldev.models.auth.AuthState
import com.miraeldev.user.UserEmail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val DARK_THEME_KEY = "dark theme key"

class MainRepositoryImpl @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val userAuthDataRepository: UserAuthDataRepository,
    private val localUserAuthDataRepository: LocalUserDataRepository,
    private val preferenceDataStoreAPI: PreferenceDataStoreAPI
) : MainRepository {
    override suspend fun checkAuthState() {
        userAuthDataRepository.checkAuthState()
    }

    override fun getDarkTheme(): Flow<Boolean> {
        return preferenceDataStoreAPI
            .getPreference(booleanPreferencesKey(DARK_THEME_KEY), false)
    }

    override fun getUserStatus(): Flow<AuthState> {
        return localUserAuthDataRepository.getUserStatus()
    }

    override suspend fun getLocalUser(): UserEmail {
        return userDataRepository.getUserEmail()
    }
}