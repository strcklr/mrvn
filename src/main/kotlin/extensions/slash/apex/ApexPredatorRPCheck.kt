package extensions.slash.apex

import api.apex.models.predator.toStats
import api.apex.services.ApexApiService
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import dev.kord.rest.builder.message.EmbedBuilder
import dev.kord.rest.builder.message.create.embed

class ApexPredatorRPCheck : Extension() {
    override val name = "predator"
    private val apexApiService = ApexApiService.instance

    override suspend fun setup() {
        publicSlashCommand() { // Public slash commands have public responses
            name = "predator"
            description = "Show RP needed for Apex Predator"

            action {
                val predatorStats = apexApiService.getPredators()?.battleRoyal

                respond {
                    embed {
                        predatorStats?.let {
                            title = ":bar_chart: Master & Apex Predator Stats :bangbang:"
                            fields = mutableListOf(
                                EmbedBuilder.Field().apply {
                                    inline = true
                                    name = ":computer: **PC**"
                                    value = predatorStats.pc.toStats()
                                },
                                EmbedBuilder.Field().apply {
                                    inline = true
                                    name = ":video_game: **PS4**"
                                    value = predatorStats.ps4.toStats()
                                },
                                EmbedBuilder.Field().apply {
                                    inline = true
                                    name = ":joystick: **Xbox One**"
                                    value = predatorStats.xbox1.toStats()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
