package api.apex.models.stats

data class Global(
    val name: String,
    val uid: String,
    val level: Int,
    val rank: PlayerRank,
)
