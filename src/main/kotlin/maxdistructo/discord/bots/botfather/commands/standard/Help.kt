package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.core.jda.Config
import maxdistructo.discord.core.jda.Perms
import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Message

class Help : BaseCommand(){

    override val commandName: String
        get() = "help"
    override val helpMessage: String
        get() = "help - Resends this message"
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        val builder = StringBuilder()
        when{
            Perms.checkAdmin(message) ->{
                var i = 0
                for(command in BaseBot.listener.adminCommands){
                    if(i != 0) {
                        builder.append(Config.readPrefix() + command.helpMessage)
                        builder.append("\n")
                    }
                    i++
                }
            }
            Perms.checkMod(message) ->{
                var i = 0
                for (command in BaseBot.listener.modCommands) {
                    if(i != 0) {
                        builder.append(Config.readPrefix() + command.helpMessage)
                        builder.append("\n")
                    }
                    i++
                }

            }
            else ->{
                var i = 0
                for(command in BaseBot.listener.commandsArray){
                    if(i != 0) {
                        builder.append(Config.readPrefix() + command.helpMessage)
                        builder.append("\n")
                    }
                    i++
                }
            }
        }
        Messages.sendDM(message.author, builder.toString())
        return ""
    }
}
