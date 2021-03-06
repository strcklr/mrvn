import com.kotlindiscord.kord.extensions.ExtensibleBot
import extensions.chat.TestExtension
import extensions.slash.SlapCommand
import extensions.slash.apex.ApexPlayerStats
import extensions.slash.apex.ApexPredatorRPCheck

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
            add(::ApexPredatorRPCheck)
        }
    }

    bot.start()
}
