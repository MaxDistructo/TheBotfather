package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.bots.botfather.background.PrivUtils
import maxdistructo.discord.core.jda.Perms
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.command.BaseCommand
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.Role
import net.dv8tion.jda.core.entities.Member


class Info : BaseCommand() {

    override val commandName: String
        get() = "info"
    override val helpMessage: String
        get() = "info - Gets your info in this server. Mods can mention a user to get information on another user"
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        return onInfoCommand(PrivUtils.listToArray(args), message, Utils.getMentionedUser(message))
    }

    fun onInfoCommand(args: Array<String>, message: Message, mentioned: Member?): String {
        val author = message.member
        val guild = message.guild
        val guildName = guild.name
        val nick = author.nickname
        val roles = author.roles
        val rolesArray = roles.toTypedArray()
        var roleNames = ""
        var i = 1
        while (i < rolesArray.size) {
            if (i == rolesArray.size - 1) {
                val roleCheck = rolesArray[i] as Role
                roleNames = roleNames + roleCheck.name + " "
            } else {
                val roleCheck = rolesArray[i] as Role
                roleNames = roleNames + roleCheck.name + ", "
            }
            i++
        }
        if (mentioned == null) {
            return "You are known as: $nick. \nYou are in Discord Server: $guildName\nYour roles in this server are: $roleNames"
        } else if (Perms.checkMod(message)) {
            val nickChecked = mentioned.nickname
            val rolesChecked = mentioned.roles
            val rolesCheckedArray = rolesChecked.toTypedArray()
            var roleNamesChecked = ""
            i = 1
            while (i < rolesCheckedArray.size) {
                if (i == rolesCheckedArray.size - 1) {
                    val roleCheck = rolesCheckedArray[i] as Role
                    roleNamesChecked = roleNamesChecked + roleCheck.name + " "
                } else {
                    val roleCheck = rolesCheckedArray[i] as Role
                    roleNamesChecked = roleNamesChecked + roleCheck.name + ", "
                }
                i++
            }

            return "\nMember " + mentioned.user.name + " is also known as " + nickChecked + "\nThey have the roles " + roleNamesChecked + "\nIn Discord Server: " + guildName

        }
        return "Command has errored. Please enter a valid command."
    }
}
