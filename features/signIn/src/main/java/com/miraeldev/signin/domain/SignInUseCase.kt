package com.miraeldev.signin.domain


import com.miraeldev.signin.data.repositories.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: SignInRepository) {
    suspend operator fun invoke(username:String,password:String) = repository.signIn(username,password)
}