package maxdistructo.discord.bots.botfather.commands.mafia

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import maxdistructo.discord.bots.botfather.BaseBot
import java.util.*

object MafiaListener {

    val commands = LinkedList<Command>()

    fun mafiaCommandEvent(event : CommandEvent){ //Command Event is passed through for ease of coding.
        val e = event.event
        val message = e.message
        val args = message.contentDisplay.split(" ")
        if(args[0] == BaseBot.bot.commandAPI.prefix + "mafia"){ //Double Check that the right command triggered this code
            var isError = true
            for(command in commands){
                if(command.name == args[1].toLowerCase()){
                    isError = false
                    command.run(event)
                }
            }
            if(isError) {
                event.replyInDm("You have entered an invalid mafia command. Please check your spelling and try again.")
                event.message.delete()
            }
            else{
                event.message.delete()
            }
        }
    }

    fun registerCommand(com : Command)
    {
        commands.add(com)
    }

    fun registerCommands(vararg coms : Command)
    {
        for(com in coms){
            commands.add(com)
        }
    }

}