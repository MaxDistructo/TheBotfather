package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.core.jda.command.BaseCommand
import net.dv8tion.jda.core.entities.Message

class Ping : BaseCommand() {
    override val commandName: String
        get() = "ping"
    override val helpMessage: String
        get() = "ping - Gets latency between the bot and Discord"
    override val requiresAdmin: Boolean
        get() = false
    override val requiresMod: Boolean
        get() = false
    override val hasOutput: Boolean
        get() = false
    override fun init(message: Message, args: List<String>): String {
        return onPingCommand(message)
    }

    private fun onPingCommand(message: Message): String {
        return "Pong! Time taken: " + message.jda.ping + " milliseconds"
    }

}
