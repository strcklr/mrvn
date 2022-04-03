package extensions.slash

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.impl.defaultingString
import com.kotlindiscord.kord.extensions.commands.converters.impl.user
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond

class SlapCommand: Extension() {
    override val name = "slap"

    override suspend fun setup() {
        publicSlashCommand(::SlapSlashArgs) {  // Public slash commands have public responses
            name = "slap"
            description = "Ask the bot to slap another user"

            action {
                val kord = this@SlapCommand.kord

                val realTarget = if (arguments.target.id == kord.selfId) {
                    member
                } else {
                    arguments.target
                }

                respond {
                    content = "*slaps ${realTarget?.mention} with their ${arguments.weapon}*"
                }
            }
        }
    }

    inner class SlapSlashArgs : Arguments() {
        val target by user {
            name = "target"
            description = "Person you want to slap"
        }

        // Coalesced strings are not currently supported by slash commands
        val weapon by defaultingString {
            name = "weapon"
            description = "What you want to slap with"

            defaultValue = "large, smelly trout"
        }
    }
}

