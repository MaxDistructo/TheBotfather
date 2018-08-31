package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.core.jda.command.BaseCommand
import net.dv8tion.jda.core.entities.Message

class Game : BaseCommand() {

    override val commandName: String
        get() = "Game"
    override val requiresMod: Boolean
        get() = false
    override val requiresAdmin: Boolean
        get() = false
    override val helpMessage : String
        get() = ""
    override val hasOutput: Boolean
        get() = false

    override fun init(message : Message, args : List<String>) : String {
        return "Command Error: $commandName"
    }
}