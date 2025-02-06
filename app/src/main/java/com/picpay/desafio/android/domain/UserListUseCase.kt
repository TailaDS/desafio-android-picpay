package com.picpay.desafio.android.domain

import com.picpay.desafio.android.model.User

interface UserListUseCase {
    suspend fun fetchUsers(): List<User>
}