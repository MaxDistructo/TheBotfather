package maxdistructo.discord.bots.botfather.commands.mafia.methods

import maxdistructo.discord.bots.botfather.commands.mafia.Perms
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Player
import maxdistructo.discord.core.jda.Roles
import maxdistructo.discord.core.jda.Utils
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Message

object Toggle {
    fun fixAlivePlayerPermsDay(message : Message, game : Game){
        val dayChannel = game.dayChannel
        val perms = Perms(message.guild)
        for(player in MafiaConfig.getPlayers(message ,"Mafia Folks")) {
            if (!perms.checkMod(player)) {
                try {
                    dayChannel.getPermissionOverride(message.guild.getMemberById(player)).delete().complete(true)
                }
                catch(e : NullPointerException){}
                try {
                    dayChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).submit(true)
                }
                catch(e : IllegalStateException){}
                val playerInfo = Player(message, player)
                if (playerInfo.allignment == "mafia") {//check if mafia
                    Perms.denyMafChat(message, message.guild.getMemberById(player))
                } else {
                    Perms.denyNonMaf(message, message.guild.getMemberById(player))
                }
                if (playerInfo.roleString == "jailor") {
                    game.jailorChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ).setDeny(Permission.MESSAGE_WRITE).submit(true)
                } else {
                    Perms.denyJailorChat(message, message.guild.getMemberById(player))
                }
                if (MafiaConfig.getJailed(message) == player) {
                    Perms.denyJailedChat(message, message.guild.getMemberById(player))
                    Mafia.unjail(message)
                }
                if (playerInfo.roleString == "vampire") {
                    game.vampChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ).setDeny(Permission.MESSAGE_WRITE).submit(true)
                }
                if (playerInfo.roleString == "vampire_hunter") {
                    game.vamphunterChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ).setDeny(Permission.MESSAGE_WRITE).submit(true)
                }
                if (playerInfo.roleString == "spy" || playerInfo.roleString == "blackmailer") {
                    Perms.allowSpyChat(message, message.guild.getMemberById(player))
                }
                else{
                    Perms.denySpyChat(message, message.guild.getMemberById(player))
                }
                Perms.denyMediumChat(message, message.guild.getMemberById(player))
            }
        }
    }
    fun fixAlivePlayerPermsNight(message : Message, game : Game) {
        val perms = Perms(message.guild)
        for(player in MafiaConfig.getPlayers(message, "Mafia Folks")) {
            if (!perms.checkMod(player)) {
                val playerInfo = MafiaConfig.getPlayerDetails(message, player)
                if (playerInfo[0].toString() == "mafia") {//check if mafia
                    game.mafiaChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_WRITE, Permission.MESSAGE_READ).complete(true)
                }
                println("Ran Mafia Toggle")
                if (playerInfo[2].toString() == "jailor") {
                    Perms.allowJailorChat(message, message.guild.getMemberById(player))
                } else {
                    Perms.denyJailorChat(message, message.guild.getMemberById(player))
                }
                println("Ran Jailor Toggle")
                if (MafiaConfig.getJailed(message) == player) {
                    val history = game.jailedChannel.history.retrievedHistory
                    for (hist in history) {
                        if (!hist.isPinned) {
                            message.delete()
                        }
                    }
                    Perms.allowJailedChat(message, message.guild.getMemberById(player))
                } else {
                    Perms.denyJailedChat(message, message.guild.getMemberById(player))
                }
                println("Ran Jailed Toggle")
                if (playerInfo[2].toString() == "medium") {
                    Perms.allowMediumChat(message, message.guild.getMemberById(player))
                } else {
                    Perms.denyMediumChat(message, message.guild.getMemberById(player))
                }
                println("Ran Medium Toggle")
                if (playerInfo[2].toString() == "spy" || playerInfo[2].toString() == "blackmailer") {
                    Perms.allowSpyChat(message, message.guild.getMemberById(player))
                }
                else{
                    Perms.denySpyChat(message, message.guild.getMemberById(player))
                }
                println("Ran Spy Toggle")
                if (playerInfo[2].toString() == "vampire") {
                    game.vampChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).complete(true)
                }
                if (playerInfo[2].toString() == "vampire_hunter") {
                    game.vamphunterChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ, Permission.MESSAGE_WRITE).complete(true)
                }
                Perms.denyDayChat(message, message.guild.getMemberById(player))
                println("Ran Night Toggle")

            }
        }
    }
    fun fixDeadPerms(message : Message, game : Game){
        for(player in MafiaConfig.getPlayers(message ,"Mafia Folks")) {
            if (message.guild.getMemberById(player).roles.contains(message.guild.getRolesByName("Dead(Mafia)", false)[0])) {
                val user = Player(message, player)
                try {
                    game.dayChannel.getPermissionOverride(message.guild.getMember(user.user)).delete().complete(true)
                }
                catch(e : NullPointerException){} //If user does not have a day channel override, ignore the null pointer error.
                Perms.denyDayChat(message, message.guild.getMemberById(player))
                try {
                    game.mafiaChannel.getPermissionOverride(message.guild.getMember(user.user)).delete().complete(true)
                }
                catch(e : NullPointerException){}
                if (user.allignment == "mafia") {
                    Perms.denyMafChat(message, message.guild.getMemberById(player))
                } else {
                    Perms.denyNonMaf(message, message.guild.getMemberById(player))
                }
                try {
                    game.deadChannel.getPermissionOverride(message.guild.getMemberById(player)).delete().complete(true)
                }
                catch(e : NullPointerException) {}
                Perms.allowDeadChat(message, message.guild.getMemberById(player))
            }
        }
    }
    fun setDayConfig(message : Message, game : Game){
        val root = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt")
        if(root.getBoolean("day")) {
            root.remove("day")
            root.put("day", false)
            MafiaConfig.writeGameDat(message, root)
        }
        else{
            root.remove("day")
            root.put("day", true)
            root.remove("daynum")
            root.put("daynum", game.dayNum + 1)
            MafiaConfig.writeGameDat(message, root)
        }
    }

    fun updateTopic(message : Message, game : Game){
        val daynumber = game.dayNum
        game.dayChannel.manager.setTopic("Day " + daynumber + " - " +  message.guild.getMembersWithRoles(message.guild.getRolesByName("Alive(Mafia)", false)[0]) + " alive, "  + message.guild.getMembersWithRoles(message.guild.getRolesByName("Dead(Mafia)", false)[0]) + " dead").complete(true)
    }

    fun fixRoles(message : Message, game : Game){
        val players = MafiaConfig.getPlayers(message, "Mafia Folks")
        for (player in players) {
            val aliveRole = Roles.getRole(message, "Mafia(Alive)")!!
            val deadRole = Roles.getRole(message, "Mafia(Dead)")!!
            message.guild.controller.removeRolesFromMember(message.guild.getMemberById(player), deadRole).complete(true)
            message.guild.controller.addRolesToMember(message.guild.getMemberById(player), aliveRole).complete(true)
            val playerInfo = MafiaConfig.getPlayerDetails(message)
            if (playerInfo[2].toString() == "spy" || playerInfo[2].toString() == "blackmailer") {
                Perms.allowSpyChat(message, message.guild.getMemberById(player))
            } else if (playerInfo[2].toString() == "medium") {
                Perms.allowMediumChat(message, message.guild.getMemberById(player))
            }
            else if(playerInfo[2].toString() == "vampire"){
                game.vampChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ).setDeny(Permission.MESSAGE_WRITE).complete(true)
            }
            else if(playerInfo[2].toString() == "vampire_hunter"){
                game.vamphunterChannel.createPermissionOverride(message.guild.getMemberById(player)).setAllow(Permission.MESSAGE_READ).setDeny(Permission.MESSAGE_WRITE).complete(true)
            }
        }
    }
}