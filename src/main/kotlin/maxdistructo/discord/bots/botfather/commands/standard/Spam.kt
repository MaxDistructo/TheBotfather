package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.bots.botfather.background.PrivUtils
import maxdistructo.discord.core.jda.Perms
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.Member

class Spam : BaseCommand() {

    override val commandName: String
        get() = "spam"
    override val helpMessage: String
        get() = "spam <@User> NumberOfTimes - Spams a user in their DMs."
    override val requiresMod: Boolean
        get() = true
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        return onSpamCommand(PrivUtils.listToArray(args) as Array<Any>, message, Utils.getMentionedUser(message)!!)
    }

    fun onSpamCommand(args: Array<Any>, message: Message, mentioned: Member): String {
        //Spams a user in their DMs Command: prefix + spam <@User> NumberOfTimes
        val author = message.member
        var spamPlayer = mentioned
        val spamNum: Int
        if (args.size == 3) {
            spamNum = Integer.valueOf(args[2].toString())
        } else {
            return "You did not enter enough arguments to run this command."
        }
        if (mentioned.user === BaseBot.client.selfUser && !Perms.checkMod(message)) {
            spamPlayer = author
        }
        var i = 0
        while (i < spamNum) {
            Messages.sendDM(spamPlayer.user, "YOU GOT SPAMMED SON!", false)
            i++
        }
        return "Player $spamPlayer will be spammed $spamNum times."

    }
}
