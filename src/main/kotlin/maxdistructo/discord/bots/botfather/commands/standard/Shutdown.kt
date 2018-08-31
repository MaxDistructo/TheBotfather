package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.core.jda.command.DefaultCommand
import net.dv8tion.jda.core.entities.Message

class Shutdown : DefaultCommand.AdminCommand("shutdown"){
    override val requiresOwner: Boolean
        get() = true
    override val requiresAdmin: Boolean //To ensure even if registered in non Admin Listener, it can not be run by every person on the server.
        get() = true
    override val helpMessage: String
        get() = "@shutdown - Shuts the bot down."
    override val commandName: String
        get() = "shutdown"
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        return onShutdownCommand(message)
    }

    fun onShutdownCommand(message: Message): String {
            message.channel.sendMessage("Shutting Down").complete(true)
            System.exit(0)
            return ""
    }
}
