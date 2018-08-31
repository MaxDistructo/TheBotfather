package maxdistructo.discord.bots.botfather.commands

import maxdistructo.discord.bots.botfather.commands.casino.CasinoFunctions
import maxdistructo.discord.bots.botfather.commands.casino.obj.CasinoPlayer
import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.command.ICommandType
import net.dv8tion.jda.core.entities.Message

object Casino {
    class Payday : BaseCommand(){
        override val commandName: String
            get() = "payday"
        override val commandType: Enum<ICommandType>
            get() = ICommandType.GAME
        override val helpMessage: String
            get() = "casino payday - Gets your payday"
        override fun init(message : Message, args : List<String>) : String {
            return CasinoFunctions.givePayday(CasinoPlayer(message, message.member))
        }
    }
}