package api.apex.services

import api.apex.models.PlayerStats
import com.google.gson.Gson
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.Snowflake
import io.ktor.util.*
import mu.KLogger
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import util.APEX_HOST

val APEX_API_TOKEN = Snowflake(
    env("APEX_API_KEY")
)

class ApexApiService() {
    private val version = "5"
    private val client = OkHttpClient()

    fun getPlayerStats(player: String, platform: String = "PC"): PlayerStats? {
        try {
            val response = client.newCall(Request.Builder()
                .url(HttpUrl.Builder()
                    .scheme("https")
                    .host(APEX_HOST)
                    .addPathSegment("bridge")
                    .addQueryParameter("version", version)
                    .addQueryParameter("player", player)
                    .addQueryParameter("platform", platform)
                    .build())
                .header("Authorization", "IgCQilX0qfBAnQvxnCg0")
                .method("GET", null)
                .build()
            ).execute()
            println("Response status: ${response.code}")
            return Gson().fromJson(response.body?.string(), PlayerStats::class.java)
        } catch (e: Exception) {
            println("An error occurred: $e")
            return null
        }
    }
}