package com.miraeldev.signin.domain.usecases

import com.miraeldev.signin.data.repositories.SignInRepository
import me.tatarka.inject.annotations.Inject

@Inject
class GetUserEmailUseCase(private val repository: SignInRepository) {
    suspend operator fun invoke() = repository.getUserEmail()
}