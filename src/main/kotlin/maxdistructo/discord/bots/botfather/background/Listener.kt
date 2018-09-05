package maxdistructo.discord.bots.botfather.background

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.command.CommandListener
import maxdistructo.discord.core.jda.message.Messages

class Listener : CommandListener {

    override fun onCommand(event: CommandEvent, command: Command?) {
        command?.run(event)
    }

    override fun onCompletedCommand(event: CommandEvent, command: Command?) { //When command is done, delete the message that triggered it
        event.message.delete()
    }

    override fun onCommandException(event: CommandEvent, command: Command?, throwable: Throwable) {
        Messages.sendDM(event.message.author, "You have entered an invalid command. Please check how it is run using !help.")
    }
}