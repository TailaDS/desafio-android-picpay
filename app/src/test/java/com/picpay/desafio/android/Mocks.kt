package com.picpay.desafio.android

import com.picpay.desafio.android.model.User

object Mocks {
    val usersList = listOf(
        User(
            id = 1,
            img = "someImg",
            name = "someName",
            username = "someUsername"
        ),
        User(
            id = 2,
            img = "anyImg",
            name = "anyName",
            username = "anyUsername"
        )
    )
}