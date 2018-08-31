package maxdistructo.discord.bots.botfather.commands.standard


import maxdistructo.discord.bots.botfather.background.PrivUtils
import maxdistructo.discord.core.jda.Perms
import maxdistructo.discord.core.jda.command.BaseCommand
import net.dv8tion.jda.core.entities.Message

class Debug : BaseCommand() {
    override val commandName: String
        get() = "debug"
    override val helpMessage: String
        get() = "debug - Debug information"
    override val requiresAdmin: Boolean
        get() = true
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
       return onDebugCommand(PrivUtils.listToArray(args), message)
    }

    fun onDebugCommand(args: Array<String>, message: Message): String {
        val author = message.author
        val channel = message.channel
        val guild = message.guild
        val owner = guild.owner
        val roles = guild.roles


        return if (Perms.checkMod(message)) {
            "You are: $author. \nYour channel is: $channel. \nYour Owner is: $owner. \nYour server's roles are: $roles"
        } else {
            "You have insufficient perms to use this command"
        }

    }
}
