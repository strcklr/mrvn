package api.apex.models

class PlayerStats {
    val global: Global? = null
}

class Global {
    val name: String? = null
    val uid: String? = null
    val level: Int? = null
    val rank: PlayerRank? = null
}

class PlayerRank {
    val rankScore: Int = 0
    val rankName: String = "Bronze"
    val rankDiv: Int = 4
    val rankImg: String = ""
    val rankedSeason: String = ""
}