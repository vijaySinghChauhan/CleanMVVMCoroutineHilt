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
            .baseUrl(server.url(""))
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
            assertThat(request.path).isEqualTo("/posts")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
      runBlocking {
          enqueueMockResponse("listresponse.json")
          val responseBody = service.getList().body()
          val articlesList = responseBody!!
          assertThat(articlesList.size).isEqualTo(100)
      }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("listresponse.json")
            val responseBody = service.getList().body()
            val articlesList = responseBody!!
            val article = articlesList[0]
            assertThat(article.title).isEqualTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")
            assertThat(article.url).isEqualTo("https://picsum.photos/200/300")
            assertThat(article.body).isEqualTo("quia et suscipit  nsuscipit recusandae consequuntur expedita et cum  nreprehenderit molestiae ut ut quas totam  nnostrum rerum est autem sunt rem eveniet architecto")
            assertThat(article.userId).isEqualTo(1)
            assertThat(article.id).isEqualTo(1)
        }
    }

    @After
    fun tearDown() {
       server.shutdown()
    }
}