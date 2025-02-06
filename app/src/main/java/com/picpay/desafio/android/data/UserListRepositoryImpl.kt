package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.remote.RetrofitUtils
import com.picpay.desafio.android.model.User

class UserListRepositoryImpl(
    private val retrofit: RetrofitUtils
) : UserListRepository {
    override suspend fun fetchUsers() : List<User> {
        return retrofit.service.getUsers().body()?.toList() ?: emptyList()
    }
}
