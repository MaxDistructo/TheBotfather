package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.command.ICommandType
import net.dv8tion.jda.core.entities.Message

class Stats : BaseCommand() {
    override val commandName: String
        get() = "stats"
    override val commandType: Enum<ICommandType>
        get() = ICommandType.NORMAL
    override val hasOutput: Boolean
        get() = false
    override val isSubcommand: Pair<Boolean, String>
        get() = Pair(false,"")

    override fun init(message: Message, args: List<String>): String {

        return ""
    }
}