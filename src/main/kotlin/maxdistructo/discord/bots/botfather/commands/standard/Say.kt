package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.bots.botfather.background.PrivUtils
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.command.BaseCommand
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.entities.Message

class Say : BaseCommand(){

    override val commandName: String
        get() = "say"
    override val helpMessage: String
        get() = "say <message> - Simple talk back command"
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        return try{
            onSayCommand(args, message, message.mentionedChannels[0])
        }
        catch (e : Exception) {
            "Command Error: $commandName"
        }
    }
    private fun onSayCommand(args: List<String>, message: Message, mentionedChannel: TextChannel?): String {

        var anotherChannel = false
        var attachments: List<Message.Attachment>? = null
        attachments = message.attachments
        if (mentionedChannel != null) {
            anotherChannel = true
        }

        return if (anotherChannel) {
            if (!attachments.isEmpty()) {
                Utils.makeNewString(args, 2) + attachments[0].url
            } else {
                Utils.makeNewString(args, 2)
            }
        } else {
            if (!attachments.isEmpty()) {
                Utils.makeNewString(args, 1) + attachments[0].url
            } else { //This is technically not required though I want to make sure it does what it is supposed to do.
                Utils.makeNewString(args, 1)
            }
        }
    }

}

