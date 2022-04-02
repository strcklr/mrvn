package api.apex.models

data class Rank(val name: String, val value: Int)

val BRONZE_IV = Rank("Bronze 4",0)
val BRONZE_III = Rank("Bronze 3",300)
val BRONZE_II = Rank("Bronze 2",600)
val BRONZE_I = Rank("Bronze 1",900)
val SILVER_IV = Rank("Silver 4",1200)
val SILVER_III = Rank("Silver 3",1600)
val SILVER_II = Rank("Silver 2",2000)
val SILVER_I = Rank("Silver 1",2400)
val GOLD_IV = Rank("Gold 4",2800)
val GOLD_III = Rank("Gold 3",3300)
val GOLD_II = Rank("Gold 2",3800)
val GOLD_I = Rank("Gold 1",4300)
val PLATINUM_IV = Rank("Platinum 4",4800)
val PLATINUM_III = Rank("Platinum 3",5400)
val PLATINUM_II = Rank("Platinum 2",6000)
val PLATINUM_I = Rank("Platinum 1",6600)
val DIAMOND_IV = Rank("Diamond 4",7200)
val DIAMOND_III = Rank("Diamond 3",7900)
val DIAMOND_II = Rank("Diamond 2",8600)
val DIAMOND_I = Rank("Diamond 1",9300)
val MASTER = Rank("Master",10000)
val APEX_PREDATOR = Rank("Apex Predator", -1)

data class CalculatedRank(val currentRank: Rank, val nextRank: Rank) {
    fun getRPNeededForLevelUp(currentRp: Int) =
        nextRank.value - currentRp
}

fun PlayerRank.findRank(): CalculatedRank =
    when(this.rankScore) {
        in BRONZE_IV.value..BRONZE_III.value -> CalculatedRank(BRONZE_IV, BRONZE_III)
        in BRONZE_III.value..BRONZE_II.value -> CalculatedRank(BRONZE_III, BRONZE_II)
        in BRONZE_I.value..SILVER_IV.value -> CalculatedRank(BRONZE_I, SILVER_IV)
        in SILVER_IV.value..SILVER_III.value -> CalculatedRank(SILVER_IV, SILVER_III)
        in SILVER_III.value..SILVER_II.value -> CalculatedRank(SILVER_III, SILVER_II)
        in SILVER_II.value..SILVER_I.value -> CalculatedRank(SILVER_II, SILVER_I)
        in SILVER_I.value..GOLD_IV.value -> CalculatedRank(SILVER_I, GOLD_IV)
        in GOLD_IV.value..GOLD_III.value -> CalculatedRank(GOLD_IV, GOLD_III)
        in GOLD_III.value..GOLD_II.value -> CalculatedRank(GOLD_III, GOLD_II)
        in GOLD_II.value..GOLD_I.value -> CalculatedRank(GOLD_II, GOLD_I)
        in GOLD_I.value..PLATINUM_IV.value -> CalculatedRank(GOLD_I, PLATINUM_IV)
        in PLATINUM_IV.value..PLATINUM_III.value -> CalculatedRank(PLATINUM_IV, PLATINUM_III)
        in PLATINUM_III.value..PLATINUM_II.value -> CalculatedRank(PLATINUM_III, PLATINUM_II)
        in PLATINUM_II.value..PLATINUM_I.value -> CalculatedRank(PLATINUM_II, PLATINUM_I)
        in PLATINUM_I.value..DIAMOND_IV.value -> CalculatedRank(PLATINUM_I, DIAMOND_IV)
        in DIAMOND_IV.value..DIAMOND_III.value -> CalculatedRank(DIAMOND_IV, DIAMOND_III)
        in DIAMOND_III.value..DIAMOND_II.value -> CalculatedRank(DIAMOND_III, DIAMOND_II)
        in DIAMOND_II.value..DIAMOND_I.value -> CalculatedRank(DIAMOND_II, DIAMOND_I)
        in DIAMOND_I.value..MASTER.value -> CalculatedRank(DIAMOND_I, MASTER)
        else -> CalculatedRank(MASTER, APEX_PREDATOR)
}