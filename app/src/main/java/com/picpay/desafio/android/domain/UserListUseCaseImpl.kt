package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.UserListRepositoryImpl
import com.picpay.desafio.android.model.User

class UserListUseCaseImpl(
    private val repository: UserListRepositoryImpl
) : UserListUseCase {
    override suspend fun fetchUsers(): List<User> {
        return repository.fetchUsers()
    }
}