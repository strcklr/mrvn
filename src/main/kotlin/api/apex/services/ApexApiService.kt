package api.apex.services

import api.apex.models.predator.ApexPredatorStats
import api.apex.models.stats.PlayerStats
import com.google.gson.Gson
import com.kotlindiscord.kord.extensions.utils.env
import mu.KotlinLogging
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response
import util.APEX_HOST
import util.MRVNOkHttpClient
import java.io.IOException

val APEX_API_TOKEN = env("APEX_API_KEY")
private val logger = KotlinLogging.logger {}

class ApexApiService {
    private val version = "5"
    private val client = MRVNOkHttpClient.instance

    companion object {
        val instance = ApexApiService()
    }

    private fun sendRequest(request: Request): Response? {
        return try {
            val response = client.newCall(request).execute()
            logger.info { "Response status: ${response.code} ${response.message}" }
            response
        } catch (e: Exception) {
            when (e) {
                is IOException -> logger.error { "Connection error: ${e.message}" }
                is IllegalStateException -> logger.error { "Call already executed: ${e.message}" }
                else -> logger.error { "An unexpected error occurred: ${e.message}, stackTrace ${e.stackTrace}" }
            }
            null
        }
    }

    private inline fun <reified T> parseResponse(response: Response, javaClass: Class<T>): T? {
        return try {
            Gson().fromJson(response.body?.string(), javaClass)
        } catch (e: Exception) {
            logger.info { "An error occurred: $e" }
            null
        }
    }

    fun getPlayerStats(player: String, platform: String = "PC"): PlayerStats? =
        parseResponse(sendRequest(Request.Builder()
            .url(HttpUrl.Builder()
                .scheme("https")
                .host(APEX_HOST)
                .addPathSegment("bridge")
                .addQueryParameter("version", version)
                .addQueryParameter("player", player)
                .addQueryParameter("platform", platform)
                .build())
            .header("Authorization", APEX_API_TOKEN)
            .method("GET", null)
            .build()
        )!!, PlayerStats::class.java)

    fun getPredators(): ApexPredatorStats? =
        parseResponse(sendRequest(Request.Builder()
            .url(HttpUrl.Builder()
                .scheme("https")
                .host(APEX_HOST)
                .addPathSegment("predator")
                .build())
            .header("Authorization", APEX_API_TOKEN)
            .method("GET", null)
            .build()
        )!!, ApexPredatorStats::class.java)
}
