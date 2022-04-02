package api.apex.models

class PlayerStats {
    val global: Global? = null
}

class Global {
    val name: String? = null
    val level: Int? = null
    val rank: PlayerRank? = null
}

class PlayerRank {
    val rankScore: Int = 0
    val rankName: String = "Bronze"
    val rankDiv: Int = 4
    val rankImage: String = ""
    val rankedSeason: String = ""
}