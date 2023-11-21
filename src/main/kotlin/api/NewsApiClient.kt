package api

import constants.Constants.API_KEY
import data.model.News
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

object NewsApiClient {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getTopHeadlines(): News {
        val url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=$API_KEY"
        return client.get(url).body()
    }

    suspend fun getSearchNews(searchedText: String): News {
        val url = "https://newsapi.org/v2/everything?q=$searchedText&apiKey=$API_KEY"
        return client.get(url).body()
    }
}