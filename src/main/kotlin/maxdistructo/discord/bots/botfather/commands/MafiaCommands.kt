package maxdistructo.discord.bots.botfather.commands

import maxdistructo.discord.bots.botfather.background.EnumSelector
import maxdistructo.discord.bots.botfather.background.PrivUtils
import maxdistructo.discord.bots.botfather.commands.mafia.MafiaCommand
import maxdistructo.discord.bots.botfather.commands.mafia.action.ActionMessage
import maxdistructo.discord.bots.botfather.commands.mafia.methods.*
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Player
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Roles
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.command.IBaseListener
import maxdistructo.discord.core.jda.command.ICommandType
import maxdistructo.discord.core.jda.message.Messages
import maxdistructo.discord.core.jda.message.Webhook
import maxdistructo.discord.core.jda.obj.ICommandRegistry
import net.dv8tion.jda.core.entities.Message


object MafiaCommands : ICommandRegistry {

    class Do : MafiaCommand(){
        override val commandName: String
            get() = "do"
        override val helpMessage: String
            get() = "mafia do <User> - Uses your night action on the provided user"
        override fun init(message: Message, args: List<String>): String {
            UserDo.message(message, PrivUtils.listToArray(args) as Array<Any>)
            return ""
        }
    }
    class Join : MafiaCommand(){
        override val commandName: String
            get() = "join"
        override val helpMessage: String
            get() = "mafia join - Adds yourself to the next mafia game"
        override fun init(message: Message, args: List<String>): String {
            Mafia.onGameJoinCommand(message)
            return ""
        }
    }
    class Continue : MafiaCommand(){
        override val commandName: String
            get() = "continue"
        override val helpMessage: String
            get() = "mafia continue - Moves the game forward to the next night/day"
        override val requiresMod: Boolean
            get() = true
        override fun init(message: Message, args: List<String>): String {
            Mafia.onGameToggle(message)
            return ""
        }
    }
    class Start : MafiaCommand(){
        override val commandName: String
            get() = "start"
        override val helpMessage: String
            get() = "mafia start - Starts the mafia game with all joined players"
        override val requiresMod: Boolean
            get() = true
        override fun init(message: Message, args: List<String>): String {
            Mafia.onGameStart(message)
            return ""
        }
    }
    class Info : MafiaCommand(){
        override val commandName: String
            get() = "info"
        override val helpMessage: String
            get() = "mafia info - Gets your info."
        override fun init(message: Message, args: List<String>): String {
            val details = Player(message, message.member)
             Messages.sendDM(message.author, Messages.simpleEmbed(message.member, "Mafia Details", "Alignment: " + details.allignment + "\n" + "Role: " + details.role + "\n" + "Attack: " + details.attack + "\nDefence: " + details.defence, message))
            return ""
        }
    }
    class ModInfo : MafiaCommand(){
        override val commandName: String
            get() = "getInfo"
        override val helpMessage: String
            get() = "mafia getInfo <@User> - Gets info on the specified user"
        override val requiresMod: Boolean
            get() = true
        override fun init(message: Message, args: List<String>): String {
            val details = MafiaConfig.getPlayerDetails(message, Mafia.getUserFromInput(message, args[2])!!.user.idLong)
            Messages.sendDM(message.author, Messages.simpleEmbed(Mafia.getUserFromInput(message, args[2])!!, "Mafia Details","Alignment: " + details[0] + "\nClass: " + details[1] + "\nRole: " + details[2] + "\nIs Dead: " + details[3] + "\nAttack Power: " + details[4] + "\nDefence Power: " + details[5], message))
            return ""
        }
    }
    class RoleCard : MafiaCommand(){
        override val commandName: String
            get() = "rolecard"
        override val helpMessage: String
            get() = "mafia rolecard <RoleName> - Shows the role card for the specified role"
        override fun init(message: Message, args: List<String>): String {
            Messages.sendMessage(message.textChannel, RoleCards.onRoleCardAsk(message, args[2], message.member))
            return ""
        }
    }
    class SetRole : MafiaCommand(){
        override val commandName: String
            get() = "setrole"
        override val helpMessage: String
            get() = "mafia setrole <User> <RoleName> - Sets the role of the provided user to the specified role"
        override val requiresMod: Boolean
            get() = true
        override fun init(message: Message, args: List<String>): String {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            Mafia.setRole(message, Utils.getUserFromInput(message, args[2])!!, args[3])
            Messages.sendMessage(game.adminChannel, "The role of " + Mafia.getUserFromInput(message, args[2])!!.effectiveName + " has been set to " + MafiaConfig.getPlayerDetails(message, Utils.getUserFromInput(message, args[2])!!.user.idLong)[2])
            Messages.sendDM(Utils.getUserFromInput(message, args[2])!!.user, "Your role has been set to " + args[3] + " due to either Unique role or specific role change.")
            return ""
        }
    }

    class KillCommand : MafiaCommand(){
        override val commandName: String
            get() = "kill"
        override val helpMessage: String
            get() = "mafia kill <User> <killer|-2kill killer1 killer2|-3kill killer1 killer2 killer3|-clean> - Kills the provided user in the specified way"
        override val requiresMod: Boolean
            get() = true

        override fun init(message: Message, args: List<String>): String {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            Mafia.killPlayer(message, Utils.getUserFromInput(message, args[2])!!.user.idLong)
            Webhook.send(game.dayChannel, "Graveyard", "https://cdn.discordapp.com/emojis/294160585179004928.png", Kill.message(message, PrivUtils.listToArray(args) as Array<Any>))
            message.delete()
            return ""
        }
    }

    class JailPlayer : MafiaCommand(){
        override val commandName: String
            get() = "jail"
        override val helpMessage: String
            get() = "mafia jail <User> - Jails the provided user"
        override val roleRestriction: String
            get() = "jailor"

        override fun init(message: Message, args: List<String>): String {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            if (Utils.getUserFromInput(message, args[2])!! === message.member) {
                Messages.sendDM(message.author, "You can not jail yourself!")
            } else {
                Mafia.jailPlayer(message, Utils.getUserFromInput(message, args[2])!!)
                Messages.sendDM(message.author, "You will be Jailing " + Utils.getMentionedUser(message))
                Messages.sendMessage(game.adminChannel, "The jailor has jailed " + Utils.getUserFromInput(message, args[2])!!.effectiveName)
            }
            message.delete()
            return ""
        }
    }

    class Vote : MafiaCommand(){
        override val commandName: String
            get() = "vote"
        override val helpMessage: String
            get() = "mafia vote <User> - Votes the specified user up to the stand."

        override fun init(message: Message, args: List<String>): String {
            val player = Player(MafiaConfig.getPlayerDetails(message))
            if (player.role == Roles.MAYOR ) {
                Messages.sendMessage(message.channel, message.member.toString() + " has voted for " + Utils.getMentionedUser(message)!!.asMention)
            }
            else {
                Messages.sendMessage(message.channel, message.member.toString() + " has voted for " + Utils.getMentionedUser(message)!!.asMention)
            }
            message.delete()
            return ""
        }
    }

    class AdminMessage : MafiaCommand(){ //!mafia message @user message
        override val commandName: String
            get() = "message"
        override val requiresMod: Boolean
            get() = true
        override val helpMessage: String
            get() = "mafia message <User> <message> - Sends an official message to the provided user through the bot. (Only use for non provided messages)"

        override fun init(message: Message, args: List<String>): String {
            Messages.sendDM(Utils.getUserFromInput(message, args[2])!!.user,Utils.makeNewString(args, 3))
            return ""
        }
    }

    class Action : MafiaCommand(){
        override val commandName: String
            get() = "action"
        override val helpMessage: String
            get() = "mafia action <@user> <action>"
        override val requiresMod: Boolean
            get() = true

        override fun init(message: Message, args: List<String>): String { //!mafia action @user action
            ActionMessage.getMessage(EnumSelector.mafiaAction(args[3]), Player(message, Utils.getUserFromInput(message, args[2])!!))
            return ""
        }
    }

    class Generate : MafiaCommand(){
        override val commandName: String
            get() = "generate"
        override val helpMessage: String
            get() = "mafia generate - Generates a mafia playerdat file"
        override val requiresMod: Boolean
            get() = true
        override fun init(message : Message , args : List<String>) : String{
            val out = Mafia.assignRoles(message)
            MafiaConfig.writeGame(message, out.first)
            return ""
        }
    }

    class Leave : MafiaCommand(){
        override val commandName: String
            get() = "leave"
        override val helpMessage: String
            get() = "mafia leave - Removes you from the mafia games"

        override fun init(message: Message, args: List<String>): String {
            Mafia.onGameLeaveCommand(message)
            return ""
        }
    }

    class Fixer : MafiaCommand(){
        override val commandName: String
            get() = "fix"
        override val helpMessage: String
            get() = "mafia fix - Fixes role and channel permissions for all users in the game."

        override fun init(message: Message, args: List<String>): String {
            Mafia.permFixer(message)
            Messages.sendMessage(message.textChannel, "Fixing permissions and roles for all players")
            return ""
        }
    }

    override fun registerCommands(listener : IBaseListener){
        val userDo = Do()
        val join = Join()
        val gameContinue = Continue()
        val start = Start()
        val info = Info()
        val modInfo = ModInfo()
        val roleCard = RoleCard()
        val setRole = SetRole()
        val killCommand = KillCommand()
        val jailPlayer = JailPlayer()
        val vote = Vote()
        val message = AdminMessage()
        val action = Action()
        val generate = Generate()
        val reset = Resetter.Command()
        val leave = Leave()
        val fixer = Fixer()
        listener.registerCommand(userDo, join, info, modInfo, roleCard, setRole, killCommand, jailPlayer, vote, message, action, generate, reset, leave, gameContinue, start, fixer)
    }

}