package maxdistructo.discord.bots.botfather.commands.mafia.methods

import kotlinx.coroutines.experimental.launch
import maxdistructo.discord.bots.botfather.commands.mafia.MafiaCommand
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.core.jda.Roles
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.Utils.s
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Message
import java.io.File

object Resetter {

    class Command : MafiaCommand(){
        override val commandName: String
            get() = "reset"
        override val helpMessage: String
            get() = "mafia reset - Resets the mafia game."
        override val requiresMod: Boolean
            get() = true

        override fun init(message: Message, args: List<String>): String {
            reset(message)
            return ""
        }
    }

    private fun reset(message : Message){
        Messages.sendMessage(message.channel, "Reset has begun. Please wait.")
        launch {
            println("Resetting Overrides")
            resetOverrides(message)
            println("Resetting Roles")
            resetRoles(message)
            println("Resetting PlayerDat file")
            val file = File(s + "/config/mafia/" + message.guild.idLong + "_playerdat.txt")
            file.delete() //Clears the Player Data file
            println("Resetting Channels")
            resetChannels(message)
        }
        Messages.sendMessage(message.channel, "Mafia Reset has been queued. Please wait for all channels to finish being cleared.")
    }
    private fun resetRoles(message : Message){
        val inRole = Roles.getRole(message, "Mafia Folks")
        val outRole = Roles.getRole(message, "Spectator(Mafia)")
        val aliveRole = Roles.getRole(message, "Alive(Mafia)")
        val deadRole = Roles.getRole(message, "Dead(Mafia)")
        for(user in message.guild.members){
            if(user != null) {
                val userRoles = user.roles
                if (userRoles.containsAll(listOf(inRole, outRole))) {
                    message.guild.controller.removeRolesFromMember(user, outRole).submit()
                }
                if (userRoles.contains(aliveRole)) {
                    message.guild.controller.removeRolesFromMember(user, aliveRole).submit()
                }
                if (userRoles.contains(deadRole)) {
                    message.guild.controller.removeRolesFromMember(user, deadRole).submit()
                }
            }
        }
    }
    private fun resetOverrides(message : Message){
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        val channels = listOf(game.dayChannel, game.deadChannel, game.mafiaChannel, game.mediumChannel,game.spyChannel, game.jailedChannel, game.jailorChannel, game.vamphunterChannel, game.vampChannel)
        for(player in MafiaConfig.getPlayers(message, "Mafia Folks")){
            for(channel in channels){
                try {
                    channel.getPermissionOverride(message.guild.getMemberById(player))!!.delete().submit()
                }
                catch(e : Exception){} //To ensure if there is no override that the code still goes on.
            }
        }
    }
    private fun resetChannels(message : Message) {
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        val channels = listOf(game.dayChannel, game.deadChannel, game.mafiaChannel, game.mediumChannel, game.spyChannel, game.jailedChannel, game.jailorChannel, game.vamphunterChannel, game.vampChannel)
        for (channel in channels) {
            for (message2 in channel.iterableHistory) {
                if (message2 != null) {
                    if (!message2.isPinned) {
                        message2.delete().submit()
                    }
                }
            }
        }
    }
}