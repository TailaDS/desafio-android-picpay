package com.picpay.desafio.android.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo != null && networkInfo.isConnected
    }
}

fun provideCache(context: Context): Cache {
    val cacheSize = 10L * 1024 * 1024
    return Cache(File(context.cacheDir, "http_cache"), cacheSize)
}

fun provideOkHttpClient(cache: Cache, context: Context): OkHttpClient {
    val offlineCacheInterceptor = Interceptor { chain ->
        var request = chain.request()

        if (!isNetworkAvailable(context)) {
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=86400")
                .build()
        }
        chain.proceed(request)
    }

    val cacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        response.newBuilder()
            .header("Cache-Control", "public, max-age=3600")
            .build()
    }

    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(offlineCacheInterceptor)
        .addNetworkInterceptor(cacheInterceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}