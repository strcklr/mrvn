package extensions.slash.apex

import api.apex.models.MASTER
import api.apex.models.findRank
import api.apex.services.ApexApiService
import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.application.slash.converters.impl.defaultingStringChoice
import com.kotlindiscord.kord.extensions.commands.converters.impl.string
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import dev.kord.rest.builder.message.EmbedBuilder
import dev.kord.rest.builder.message.create.embed

private const val PROFILE_URL = "https://apexlegendsstatus.com/profile/uid"

class ApexPlayerStats : Extension() {
    override val name = "stats"
    private val apexApiService = ApexApiService.instance

    override suspend fun setup() {
        publicSlashCommand(::PlayerStatsArgs) { // Public slash commands have public responses
            name = "stats"
            description = "Retrieve Apex stats for a player"

            action {
                val stats = apexApiService.getPlayerStats(arguments.player, arguments.platform)?.global
                val rank = stats?.rank

                respond {
                    if (stats != null && rank != null) {
                        val calculatedRank = rank.findRank()
                        var rpToNextLevel = calculatedRank.getRPNeededForLevelUp(rank.rankScore)
                        var rankAndDivision = "${rank.rankName} ${rank.rankDiv}"
                        if (rank.rankScore >= MASTER.value) {
                            rankAndDivision = rank.rankName
                            rpToNextLevel = 0
                        }
                        val rankUpMessage = if (rpToNextLevel > 0) {
                            when (calculatedRank.getRPNeededForLevelUp(rank.rankScore)) {
                                in 0 until 150 -> "So close!! :dash:"
                                in 150 until 400 -> "Getting there!"
                                in 400 until 1000 -> "Long ways to go..."
                                else -> "You're never gonna get there!"
                            }
                        } else "$rankAndDivision :clap:! *tryhard...*"
                        embed {
                            title = ":joystick: ${stats.name}'s Apex Legends stats"
                            description = rankUpMessage
                            url = "$PROFILE_URL/${arguments.platform}/${stats.uid}"
                            thumbnail {
                                url = rank.rankImg.replace("\\", "")
                            }
                            fields = mutableListOf(
                                EmbedBuilder.Field().apply {
                                    inline = true
                                    name = ":medal: Rank"
                                    value = rankAndDivision
                                },
                                EmbedBuilder.Field().apply {
                                    inline = true
                                    name = ":100: Current RP"
                                    value = "${rank.rankScore} RP"
                                },

                                EmbedBuilder.Field().apply {
                                    inline = true
                                    name = ":1234: RP Needed"
                                    value = "$rpToNextLevel RP"
                                }
                            )
                        }
                    } else {
                        embed {
                            title = "Player not found"
                            description = "Wasn't able to get stats for that user. Bummer :/"
                        }
                    }
                }
            }
        }
    }

    inner class PlayerStatsArgs : Arguments() {
        val player by string {
            name = "player"
            description = "Apex username of player"
        }

        val platform by defaultingStringChoice {
            name = "platform"
            description = "platform the player is on"

            choices = mutableMapOf(
                "PC" to "PC",
                "Xbox" to "Xbox",
                "Playstation" to "Playstation"
            )
            defaultValue = "PC"
        }
    }
}
