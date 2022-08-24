package com.vjchauhan.cleanmvvmcoroutinehilt.data.api


import com.google.common.truth.Truth.assertThat
import com.vjchauhan.cleanmvvmcoroutinehilt.data.network.APIService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServiceTest {

    private lateinit var service: APIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url("post"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    private fun enqueueMockResponse(
      fileName:String
    ){
      val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
      val source = inputStream.source().buffer()
      val mockResponse = MockResponse()
      mockResponse.setBody(source.readString(Charsets.UTF_8))
      server.enqueue(mockResponse)

    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("listresponse.json")
            val responseBody = service.getList().body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
      runBlocking {
          enqueueMockResponse("listresponse.json")
          val responseBody = service.getList().body()
          val articlesList = responseBody!!
          assertThat(articlesList.size).isEqualTo(20)
      }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("listresponse.json")
            val responseBody = service.getList().body()
            val articlesList = responseBody!!
            val article = articlesList[0]
            assertThat(article.title).isEqualTo("Vijay")
            assertThat(article.url).isEqualTo("https://www.cnbc.com/2021/01/04/samsung-galaxy-unpacked-2021.html")
            assertThat(article.body).isEqualTo("Desc")
        }
    }

    @After
    fun tearDown() {
       server.shutdown()
    }
}