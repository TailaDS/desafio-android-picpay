package com.picpay.desafio.android

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.remote.PicPayService
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PicPayServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: PicPayService

    private val gson: Gson =  GsonBuilder().create()
    private val mocks = Mocks

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PicPayService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getUsersTest() = runBlocking {
        // given
        val fakeResponse = gson.toJson(mocks.usersList)

        mockWebServer.enqueue(MockResponse().setBody(fakeResponse).setResponseCode(200))

        // when
        val users = service.getUsers()

        // then
        assertEquals(users, mocks.usersList)
    }
}
