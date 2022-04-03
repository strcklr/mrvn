package extensions.slash.apex

import api.apex.models.findRank
import api.apex.services.ApexApiService
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.converters.impl.stringChoice
import com.kotlindiscord.kord.extensions.commands.converters.impl.string
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond

class ApexPlayerStats: Extension() {
    private val apexApiService = ApexApiService()
    override val name = "stats"

    override suspend fun setup() {
        publicSlashCommand(::SlapSlashArgs) {  // Public slash commands have public responses
            name = "stats"
            description = "Retrieve Apex stats for a player"

            action {
                val stats = apexApiService.getPlayerStats(arguments.player, arguments.platform)

                respond {
                    content = if (stats?.global?.rank != null) {
                        val rank = stats.global.rank.findRank()
                        val rpToNextLevel = rank.getRPNeededForLevelUp(stats.global.rank.rankScore)
                        val rankUpMessage = if(rpToNextLevel > 0)  {
                            val quip = when(rank.getRPNeededForLevelUp(stats.global.rank.rankScore)) {
                                in 0..150 -> "So close!!"
                                in 151..400 -> "Getting there!"
                                in 401..1000 -> "Long ways to go..."
                                else -> "You're never gonna get there!"
                            }
                            "They need ${rpToNextLevel}RP for promotion to ${rank.nextRank.name}! $quip"
                        } else "A true master! Good luck on Apex Predator!"
                        "${stats.global.name} is in ${stats.global.rank.rankName} ${stats.global.rank.rankDiv} at ${stats.global.rank.rankScore}RP! $rankUpMessage"
                    } else {
                        "Wasn't able to get stats for that user. Bummer :/"
                    }
                }
            }
        }
    }


    inner class SlapSlashArgs : Arguments() {
        val player by string {
            name = "player"
            description = "Apex username of player"
        }

        val platform by stringChoice {
            name = "platform"
            description = "platform the player is on"

            choices = mutableMapOf(
                "PC" to "PC",
                "Xbox" to "Xbox",
                "Playstation" to "Playstation"
            )
        }
    }
}