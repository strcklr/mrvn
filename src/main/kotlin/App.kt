import com.kotlindiscord.kord.extensions.ExtensibleBot
import extensions.chat.TestExtension
import extensions.slash.SlapCommand
import extensions.slash.apex.ApexPlayerStats

val TOKEN = System.getenv("MRVN_TOKEN") ?: ""

suspend fun main() {
    val bot = ExtensibleBot(TOKEN) {
        chatCommands {
            defaultPrefix = "!"
            enabled = true
        }
        extensions {
            add(::TestExtension)
            add(::SlapCommand)
            add(::ApexPlayerStats)
        }
    }

    bot.start()
}
