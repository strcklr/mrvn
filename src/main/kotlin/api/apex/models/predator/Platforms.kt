package api.apex.models.predator

import com.google.gson.annotations.SerializedName

data class Platforms(
    @SerializedName("PC")
    val pc: Platform,
    @SerializedName("PS4")
    val ps4: Platform,
    @SerializedName("X1")
    val xbox1: Platform,
    @SerializedName("SWITCH")
    val switch: Platform
)
