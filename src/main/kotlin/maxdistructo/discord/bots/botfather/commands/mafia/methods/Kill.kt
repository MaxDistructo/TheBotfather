package maxdistructo.discord.bots.botfather.commands.mafia.methods

import maxdistructo.discord.bots.botfather.commands.mafia.obj.Player
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Roles
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message


object Kill {
    fun message(message1: Message, messageContent: Array<Any>): String {
        val message = StringBuilder() // 0 = Command, 1 = Subcommand, 2 = User 3 = Param 1
        val json1 = Utils.readJSONFromFile("/config/mafia/roles.dat")
        val single = json1.getJSONObject("single_kill")
        val multi = json1.getJSONObject("multi_kill")
        var mentioned: Member? = null
        try {
            mentioned = Utils.getUserFromInput(message1, messageContent[2])!!
        } catch (e: Exception) {
            Messages.throwError(e)
        }
        message.append(mentioned!!.asMention)
        when {
            messageContent[3] == "-2kill" -> {
                message.append(single.getString(messageContent[4].toString()))
                message.append(" ")
                message.append(multi.getString(messageContent[5].toString()))
            }
            messageContent[3] == "-3kill" -> {
                message.append(single.getString(messageContent[4].toString()))
                message.append(" ")
                message.append(multi.getString(messageContent[5].toString()))
                message.append(" ")
                message.append(multi.getString(messageContent[6].toString()))
            }
            else ->
                message.append(single.getString(messageContent[3].toString()))
        }
        if (!messageContent.contains("-clean")) {
            message.append(" ")
            val player = Player(MafiaConfig.getPlayerDetails(message1, mentioned.user.idLong))
            if(player.role == Roles.DISGUISER) {
                message.append("Their role was __**" + Player(MafiaConfig.getPlayerDetails(message1, Utils.convertToLong(MafiaConfig.getPlayerDetails(message1, mentioned.user.idLong)[6])!!)).role.name.toUpperCase() + "**__.")
            }
            else{
                message.append("Their role was __**" + player.role.name.toUpperCase() + "**__.")
            }
        } else {
            message.append(" ")
            message.append("Their role was Cleaned")
        }

        return message.toString()
    }
}