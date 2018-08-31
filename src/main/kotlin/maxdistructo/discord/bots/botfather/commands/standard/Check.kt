package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.bots.botfather.background.PrivUtils
import maxdistructo.discord.core.jda.Perms
import maxdistructo.discord.core.jda.command.BaseCommand
import net.dv8tion.jda.core.entities.Message

class Check : BaseCommand(){
    override val commandName: String
        get() = "check"
    override val helpMessage: String
        get() = "check - Checks permissions for yourself."
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        return onCheckCommand(PrivUtils.listToArray(args), message)
    }
    // @Command(aliases = {"/check"}, description = "Checks if you have perms for a role", usage = "/check")
    fun onCheckCommand(args: Array<String>, message: Message): String {
        return " Is Mod: " + Perms.checkMod(message) + "\n Is Admin: " + Perms.checkAdmin(message) + "\n Is Owner: " + Perms.checkOwner(message) + "\n Is Games Channel: " + Perms.checkGames(message)
    }
}
