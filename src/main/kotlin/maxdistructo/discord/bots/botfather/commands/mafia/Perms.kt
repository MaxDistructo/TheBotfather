package maxdistructo.discord.bots.botfather.commands.mafia

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.bots.botfather.commands.mafia.methods.MafiaConfig
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.obj.IPerms
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message

class Perms(guild : Guild) : IPerms {
    override var admins: List<Long> = listOf()
    override var mods: List<Long> = listOf()
    init{
        admins = MafiaConfig.getAdminArray("/config/mafia/" + guild.idLong + "_perms.txt")
        mods = MafiaConfig.getModArray("/config/mafia/" + guild.idLong + "_perms.txt")
    }
    fun checkMod(message : Message): Boolean {
        return mods.contains(message.author.idLong) || admins.contains(message.author.idLong)
    }
    fun checkAdmin(message : Message) : Boolean{
        return mods.contains(message.author.idLong)
    }
    companion object {
        fun checkMafiaChannels(message: Message): Boolean {
            val root = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt")
            val channels = arrayListOf(root.getLong("admin_chat"), root.getLong("day_chat"), root.getLong("mafia_chat"), root.getLong("medium_chat"), root.getLong("spy_chat"), root.getLong("dead_chat"), root.getLong("jailor_chat"), root.getLong("jailed_chat"), 422457248724549632L, 450415346671812608L, 294551461772394497L)
            return channels.contains(message.channel.idLong) || BaseBot.client.getCategoriesByName("Mafia", false)[0].textChannels.contains(message.textChannel)
        }

        fun checkSpectator(message: Message) : Boolean{
            return message.member.roles.contains(message.guild.getRolesByName("Spectator(Mafia)", false)[0])
        }

        fun denyMediumChat(message: Message, userByID: Member?) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.mediumChannel.createPermissionOverride(userByID).setDeny(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }

        fun denyJailedChat(message: Message, userByID: Member?) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.jailedChannel.createPermissionOverride(userByID).setDeny(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }

        fun denyJailorChat(message: Message, userByID: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            try {
                game.jailorChannel.createPermissionOverride(userByID).setDeny(Permission.MESSAGE_WRITE).submit(true)
            }
            catch(e : IllegalStateException){ //IllegalStateException caused by user already having a permission.
                game.jailedChannel.getPermissionOverride(userByID).delete().complete()
                game.jailorChannel.createPermissionOverride(userByID).setDeny(Permission.MESSAGE_WRITE).submit(true)
            }
        }

        fun denyDayChat(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.dayChannel.createPermissionOverride(user).setDeny(Permission.MESSAGE_WRITE).submit(true)
        }

        fun denyMafChat(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.mafiaChannel.createPermissionOverride(user).setDeny(Permission.MESSAGE_WRITE).submit(true)
        }

        fun denyNonMaf(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.mafiaChannel.createPermissionOverride(user).setDeny(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }

        fun allowMediumChat(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.mediumChannel.createPermissionOverride(user).setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }

        fun allowJailorChat(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.jailorChannel.createPermissionOverride(user).setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }

        fun allowJailedChat(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.jailedChannel.createPermissionOverride(user).setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }

        fun allowSpyChat(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.spyChannel.createPermissionOverride(user).setAllow(Permission.MESSAGE_READ).submit(true)}

        fun denySpyChat(message : Message, user : Member){
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.spyChannel.createPermissionOverride(user).setDeny(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }
        fun allowDeadChat(message: Message, user: Member) {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            game.deadChannel.createPermissionOverride(user).setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
        }
    }
}
