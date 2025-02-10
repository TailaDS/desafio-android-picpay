package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.UserListRepositoryImpl
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.remote.provideCache
import com.picpay.desafio.android.data.remote.provideOkHttpClient
import com.picpay.desafio.android.data.remote.provideRetrofit
import com.picpay.desafio.android.domain.UserListUseCaseImpl
import com.picpay.desafio.android.presentation.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single { provideCache(get()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get()) }

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
