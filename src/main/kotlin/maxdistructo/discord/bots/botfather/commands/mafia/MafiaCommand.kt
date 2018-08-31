package maxdistructo.discord.bots.botfather.commands.mafia

import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.command.ICommandType

open class MafiaCommand : BaseCommand() {
    override val commandType: Enum<ICommandType>
        get() = ICommandType.GAME
    override val isSubcommand = Pair(true, "mafia") //Method is used to tell Listener that it is a subcommand to the Mafia command.
    open val roleRestriction : String = "none"
    override val hasOutput: Boolean
        get() = false
}