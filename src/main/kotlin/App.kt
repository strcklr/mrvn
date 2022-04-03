import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.Snowflake
import extensions.chat.TestExtension
import extensions.slash.SlapCommand
import extensions.slash.apex.ApexPlayerStats

val TEST_SERVER_ID = Snowflake(
    env("TEST_SERVER")
)

val TOKEN = env("MRVN_TOKEN")

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
