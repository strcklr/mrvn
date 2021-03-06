package extensions.chat

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.chatCommand
import com.kotlindiscord.kord.extensions.utils.respond

class TestExtension : Extension() {
    override val name = "test"

    override suspend fun setup() {
        chatCommand {
            name = "mrvnTest"
            description = "Get slapped!"

            action {
                message.respond(
                    "*slaps you with a large, smelly trout!*"
                )
            }
        }
    }
}
