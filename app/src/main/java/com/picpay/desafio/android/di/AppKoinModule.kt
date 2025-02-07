package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.UserListRepositoryImpl
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.UserListUseCaseImpl
import com.picpay.desafio.android.presentation.UserListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { GsonBuilder().create() }

    single { OkHttpClient.Builder().build() }

    single { Retrofit.Builder()
        .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
        .client(get<OkHttpClient>())
        .addConverterFactory(GsonConverterFactory.create(get()))
        .build()

    }

    single {
        val retrofit: Retrofit = get()
        retrofit.create(PicPayService::class.java)
    }

}

val appKoinModule = module {

    single { UserListRepositoryImpl(get()) }

    single { UserListUseCaseImpl(get()) }

    viewModel { UserListViewModel(get()) }

}

