package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.model.User

class UserListRepositoryImpl(
    private val service: PicPayService
) : UserListRepository {
    override suspend fun fetchUsers() : List<User> {
        return try {
            service.getUsers().toList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
