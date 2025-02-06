package com.picpay.desafio.android.data

import com.picpay.desafio.android.model.User

interface UserListRepository {
    suspend fun fetchUsers(): List<User>
}