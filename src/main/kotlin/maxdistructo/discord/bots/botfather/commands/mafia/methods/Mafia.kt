package maxdistructo.discord.bots.botfather.commands.mafia.methods

import maxdistructo.discord.bots.botfather.commands.mafia.MafiaListener
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Details
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Player
import maxdistructo.discord.core.jda.Roles
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.Utils.s
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import org.json.JSONObject
import java.io.File

object Mafia {

    fun onMafiaJoin(message: Message) {
        //TODO Read player file(Create if not existence), add new user ID to file, write file back to disk. Will return String saying player name has been successfully added to game.
    }

    fun onGameJoinCommand(message: Message) {
        val role = Roles.getRole(message, "Mafia Folks")!!
        Roles.applyRole(message, message.member, role.idLong)
        Messages.sendMessage(message.channel, message.author.asMention + ", you have been added to the Mafia game.")
        message.delete()
    }

    fun onGameLeaveCommand(message: Message) {
        val role = Roles.getRole(message, "Mafia Folks")!!
        message.guild.controller.removeRolesFromMember(message.member, role)
        Messages.sendMessage(message.channel, message.member.asMention + ", you have been added to the Mafia game.")
        message.delete()
    }

    fun onGameStart(message: Message) {
        println("Starting Game")
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        val adminChannel = game.adminChannel
        val dayChannel = game.dayChannel
        //MafiaListener.registerHandler(GraveyardHandler.Handler(Messages.sendMessage(dayChannel, "Graveyard: ")))
        //MafiaListener.registerHandler(RolelistHandler.Handler(Messages.sendMessage(dayChannel, "RoleList: ")))
        //MafiaListener.registerHandler(VoteCountHandler.Handler(Messages.sendMessage(game.dayChannel, "Votes: ")))
        //MafiaListener.resetHandlers(message)
        println("Resetting Player Dat")
        File(s + "/config/mafia/" + message.guild.idLong + "_playerdat.txt").delete()
        println("Generating roles for players")
        val roles = assignRoles(message)
        val roleArray = roles.second
        println("Created player dat")
        MafiaConfig.writeGame(message, roles.first)
        var ii = 0
        for(i in MafiaConfig.getPlayers(message, "Mafia Folks")){
            Messages.sendDM(message.jda.getUserById(i), RoleCards.onRoleCardAsk(message, roleArray[ii]!!, message.guild.getMemberById(i)), false)
            ii++
        }
        Messages.sendMessage(dayChannel, message.guild.getRolesByName("Mafia Folks", false)[0].asMention + " The Mafia game has started! \n Day 1 has begun!", true)
        Messages.sendMessage(adminChannel, message.member.effectiveName + "#" + message.author.discriminator + " has started the Mafia game.", false)
        unjail(message)

        //MafiaListener.updateHandlers(message)
    }

    fun onGameToggle(message: Message) {
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        //runActions();
        if (game.day) {
            println("Changing to Night")
            toggleToNight(message)
        } else {
            println("Changing to Day")
            MafiaListener.fixDirty(message)
            println("Fixed Dirty Values")
            toggleToDay(message)
        }
    }
    private fun toggleToDay(message : Message){
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        val players = MafiaConfig.getPlayers(message, "Mafia Folks")
        Messages.sendMessage(game.adminChannel, "Starting change over to day mode. Please wait.")
        //runActions(message)
        resetChannelOverrides(message, players)
        Toggle.fixAlivePlayerPermsDay(message, game)
        Toggle.fixDeadPerms(message, game)
        Toggle.setDayConfig(message, game)
        val daynumber = game.dayNum + 1
        var votes = message.guild.getMembersWithRoles(Roles.getRole(message, "Mafia Folks")!!).size
        if(votes % 2 > 0){
         votes++
        }
        Toggle.updateTopic(message, game)
        Messages.sendMessage(game.dayChannel, message.guild.getRolesByName("Alive(Mafia)", false)[0].asMention + " Day $daynumber has started. " + votes + " votes needed to vote someone up.")
        Messages.sendMessage(game.adminChannel, "Successfully converted over to day mode.")
    }
    private fun toggleToNight(message : Message){
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        val players = MafiaConfig.getPlayers(message, "Mafia Folks")
        Messages.sendMessage(game.adminChannel, "Starting change over to night mode. Please wait.")
        resetChannelOverrides(message, players)
        Toggle.fixAlivePlayerPermsNight(message, game)
        Toggle.fixDeadPerms(message, game)
        Toggle.setDayConfig(message, game)
        Toggle.updateTopic(message, game)
        Messages.sendMessage(game.adminChannel, "Successfully converted over to night mode.")
        Messages.sendMessage(game.dayChannel, message.guild.getRolesByName("Alive(Mafia)", false)[0].asMention +" Night " + game.dayNum + " is now active.")
    }

    fun assignRoles(message: Message): Pair<JSONObject, List<String?>> {
        val root = Utils.readJSONFromFile("/config/mafia/roles.dat")
        val jArrayRoles = root.getJSONArray("rolelist")
        val translator = root.getJSONObject("rolelist-translate")
        val roleData = root.getJSONObject("roleData")
        val players = MafiaConfig.getPlayers(message, "Mafia Folks")
        val roleList = MafiaConfig.shuffleJSONArray(jArrayRoles) as MutableList
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        var i = 0
        for (role in roleList) {
            val roleDatJSON = translator.getJSONArray(role)
            val roleDat = MafiaConfig.shuffleJSONArray(roleDatJSON)
            roleList[i] = roleDat[0]
            i++
        }
        val out = JSONObject()
        var ii = 0
        val sb = StringBuilder()
        sb.append("The role list contains: \n")
        for (player in players) {
            out.put("" + player, roleData.getJSONObject(roleList[ii]))
            //Messages.sendDM(message.jda.getUserById(player), RoleCards.onRoleCardAsk(message, roleList[ii].toString(), message.jda.getUserById(player)))
            sb.append(message.jda.getUserById(player).name + ": " + roleList[ii] + "\n")
            ii++
        }
        Messages.sendMessage(game.adminChannel, sb.toString())
        return Pair(out, roleList)
    }

    fun killPlayer(message: Message, playerID: Long): String {
        val player = message.guild.getMemberById(playerID)
        val playerDetails = MafiaConfig.getPlayerDetails(message, playerID)
        if (playerDetails[3] as Boolean) {
            return "Player is already dead. Can not kill player " + player.effectiveName + " because they are already dead. "
        }
        val root = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
        val playerInfo = root.getJSONObject("" + playerID)
        playerInfo.remove("dead")
        playerInfo.put("dead", true)
        root.remove("" + playerID)
        root.put("" + playerID, playerInfo)
        MafiaConfig.writeGame(message, root)
        val dead = Roles.getRole(message, "Dead(Mafia)")
        val alive = Roles.getRole(message, "Alive(Mafia)")
        message.guild.controller.addRolesToMember(player, dead).complete(true)
        message.guild.controller.removeRolesFromMember(player, alive).complete(true)
        return "Successfully killed player " + player.effectiveName
    }

    fun jailPlayer(message: Message, user: Member) {
        val playerDetails = MafiaConfig.getPlayerDetails(message, user.user.idLong)
        val root = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt")
        root.remove("jailed")
        if (playerDetails[2].toString() == MafiaConfig.getPlayerDetails(message)[2].toString()) {
            println("Jailor's can not jail themselves!")
            root.put("jailed", 0L)
        } else {
            root.put("jailed", user.user.idLong)
        }
        MafiaConfig.writeGameDat(message, root)
    }

    fun unjail(message: Message) {
        val root = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt")
        root.remove("jailed")
        root.put("jailed", 0)
        MafiaConfig.writeGameDat(message, root)
    }

    fun setRole(message: Message, user: Member, role: String) {
        val root = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
        val root1 = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + ".dat")
        val translator = root1.getJSONObject("rolelist-translate")
        val roleData = root1.getJSONObject("roleData")
        val roleAssign: String
        root.remove("" + user.user.idLong)
        val roleDatJSON = translator.getJSONArray(role)
        val roleDat = MafiaConfig.shuffleJSONArray(roleDatJSON)
        roleAssign = roleDat[0].toString()
        root.put("" + user.user.idLong, roleData.getJSONObject(roleAssign))
    }

    fun resetChannelOverrides(message: Message, players: LongArray) {
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        for (player in players) {
            val permissionChannels = listOf(game.mafiaChannel, game.jailorChannel, game.jailedChannel, game.mediumChannel, game.deadChannel,
                    game.spyChannel, game.vampChannel, game.vamphunterChannel)
            for(channel in permissionChannels){
                val userOverrides = channel.memberPermissionOverrides
                for(override in userOverrides){
                    override.delete().complete(true)
                }
            }
        }
    }

    fun getUserFromInput(message : Message, input : Any) : Member?{
        return getUserFromInput(message, input, 0)
    }

    private fun getUserFromInput(message : Message, input : Any, slotIn : Int) : Member? {
        //Code is identical to maxdistructo.discord.core.Utils.getUserFromInput
        //Variant is because users are being returned that are not a part of the mafia game.
        val attemptedUser: Member? = when {
            Utils.getMentionedUser(message) != null -> Utils.getMentionedUser(message)!!
            Utils.convertToLong(input) != null -> message.guild.getMemberById(Utils.convertToLong(input)!!)
            message.guild.getMembersByName (input.toString(), true).isNotEmpty() -> message.guild.getMembersByName(input.toString(), true)[slotIn]
            message.guild.getMembersByNickname(input.toString(), true).isNotEmpty() -> message.guild.getMembersByNickname(input.toString(), true)[slotIn]
            else -> userForLoop(message, input, slotIn)
        } ?: return null

        if (MafiaConfig.getPlayers(message, "Mafia Folks").contains(attemptedUser!!.user.idLong)) {
            return attemptedUser
        } else if (!MafiaConfig.getPlayers(message, "Mafia Folks").contains(attemptedUser.user.idLong)) {
            return getUserFromInput(message, input, (slotIn + 1))
        }
        return null
    }
    private fun userForLoop(message : Message, input : Any, slot : Int) : Member?{
        var i = 0
        println("Checking slot $slot")
        for(user in message.guild.members) {
            if (user.effectiveName.contains(input.toString())) {
                if(i == slot){
                    return user
                }
                else{
                    i++
                }
            }
            else if(user.user.name.contains(input.toString())){
                if(i == slot){
                    return user
                }
                else{
                    i++
                }
            }
        }
        return null
    }

    fun revive(message : Message, user : Long){
        MafiaConfig.editDetails(message, message.guild.getMemberById(user), Details.DEAD,false)
    }

    fun permFixer(message : Message){
        val role = Roles.getRole(message, "Alive(Mafia)")
        val deadRole = Roles.getRole(message, "Dead(Mafia)")
        for(player in MafiaConfig.getPlayers(message, "Mafia Folks")){
            val player2 = Player(message, player)
            if(!player2.dead) {
                message.guild.controller.addRolesToMember(message.guild.getMemberById(player), role).submit() //For all players, give the alive role.
            }
            else{
                message.guild.controller.addRolesToMember(message.guild.getMemberById(player), role).submit()
            }
        }
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        resetChannelOverrides(message, MafiaConfig.getPlayers(message, "Mafia Folks"))
        if(game.day){
            Toggle.fixAlivePlayerPermsDay(message, game)
            Toggle.fixDeadPerms(message, game)
        }
        else{
            Toggle.fixAlivePlayerPermsNight(message, game)
            Toggle.fixDeadPerms(message, game)
        }
    }
}
