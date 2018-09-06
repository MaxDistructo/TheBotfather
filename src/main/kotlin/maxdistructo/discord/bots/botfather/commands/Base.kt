package maxdistructo.discord.bots.botfather.commands

import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.command.DefaultCommand
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.TextChannel

object Base {
    class Ping : BaseCommand(){
        override val commandName: String
            get() = "ping"
        override val helpMessage: String
            get() = "ping - Simple ping/pong command"
        override fun init(message: Message, args: List<String>): String {
            return "Pong!"
        }
        override val hasOutput: Boolean
            get() = true
    }
    class Say : BaseCommand(){
        override val commandName: String
            get() = "say"
        override val helpMessage: String
            get() = "say <MessageToSay> - Says what you tell it to say"
        override val hasOutput: Boolean
            get() = true

        override fun init(message: Message, args: List<String>): String {
            val mentionedChannel : TextChannel? = if(message.mentionedChannels.size > 0) {
                message.mentionedChannels[0]
            } else{
                null
            }

            var anotherChannel = false
            if (mentionedChannel != null) {
                anotherChannel = true
            }

            return if (anotherChannel) {
                if (!message.attachments.isEmpty()) {
                    Utils.makeNewString(args, 2) + message.attachments[0].url
                } else {
                    Utils.makeNewString(args, 2)
                }
            } else {
                if (!message.attachments.isEmpty()) {
                    Utils.makeNewString(args, 1) + message.attachments[0].url
                } else { //This is technically not required though I want to make sure it does what it is supposed to do.
                    Utils.makeNewString(args, 1)
                }
            }
        }
    }

    class Clear : DefaultCommand.AdminCommand("clear"){
        override val isEventCommand: Boolean
            get() = false
        override val helpMessage: String
            get() = "clear - Clears a channel of non-pinned messages"
        override val requiresMod: Boolean
            get() = true

        override fun init(message: Message, args: List<String>): String {
            for(i in message.channel.iterableHistory.complete(true)){
                if(!i.isPinned){
                    i.delete().submit()
                }
            }
            return "Clearing Non-Pinned Messages"
        }
    }
}