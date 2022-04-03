package api.apex.models.predator

import com.google.gson.annotations.SerializedName

data class Platform(
    val foundRank: Int,
    @SerializedName("val")
    val value: Int?,
    val uid: String,
    val updateTimestamp: Long,
    val totalMastersAndPreds: Int
)

fun Platform.toStats(): String =
    "**Predator RP:** __$value RP__\n" +
            "**# of Players:** __$totalMastersAndPreds __"
