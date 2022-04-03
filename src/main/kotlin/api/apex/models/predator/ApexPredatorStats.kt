package api.apex.models.predator

import com.google.gson.annotations.SerializedName

data class ApexPredatorStats(
    @SerializedName("RP")
    val battleRoyal: Platforms,
    @SerializedName("AP")
    val arenas: Platforms
)
