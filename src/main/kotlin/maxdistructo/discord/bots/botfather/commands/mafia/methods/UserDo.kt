package maxdistructo.discord.bots.botfather.commands.mafia.methods

import maxdistructo.discord.bots.botfather.commands.mafia.MafiaListener
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Details
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Player
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Message

object UserDo {

    fun message(message: Message, messageContent: Array<Any>) {
        val player = Player(message, message.member)
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        val mentioned = Mafia.getUserFromInput(message, messageContent[2])
        when (player.role.name.toLowerCase()) {
            "mafioso" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " has voted to kill " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You have voted to kill " + mentioned!!.effectiveName)
                message.delete()
            }
            "godfather" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " has decided to kill " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You have decided to kill " + mentioned!!.effectiveName)
                message.delete()
            }
            "serial_killer" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " is gonna go and stab " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You have decided to go and stab " + mentioned!!.effectiveName)
                message.delete()
            }
            "werewolf" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " is gonna go and visit " + mentioned!!.effectiveName)
                if(mentioned!!.effectiveName.toCharArray().last() != 's') {
                    Messages.sendDM(message.author, "You have decided to go and rampage at " + mentioned!!.effectiveName + "'s house")
                }
                else{
                    val charArray = mentioned!!.effectiveName.toCharArray().slice(0 until (mentioned!!.effectiveName.toCharArray().size - 1))
                    Messages.sendDM(message.author, "You have decided to go and rampage at " + charArray.toString() + "'s house")
                }
                message.delete()
            }
            "arsonist" -> {
                if (mentioned!! !== message.author) {
                    Messages.sendMessage(game.adminChannel, message.member.effectiveName + " is gonna douse " + mentioned!!.effectiveName)
                    Messages.sendDM(message.author, "You have decided to douse " + mentioned!!.effectiveName)
                } else {
                    Messages.sendMessage(game.adminChannel, message.member.effectiveName + " is gonna set all doused players on fire")
                    Messages.sendDM(message.author, "You have decided to set all targets on fire tonight")
                }
                message.delete()
            }
            "jester" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " is gonna haunt " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You have decided to haunt " + mentioned!!.effectiveName)
                message.delete()
            }
            "veteran" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " is going on alert tonight.")
                Messages.sendDM(message.author, "You have decided to go on alert tonight")
                MafiaConfig.setExtra(message, true)
                message.delete()
            }
            "vigilante" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " is going to shoot " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You have decided to shoot " + mentioned!!.effectiveName)
                message.delete()
            }
            "jailor" -> {
                if (mentioned!!.user.idLong == MafiaConfig.getJailed(message)) {
                    Messages.sendMessage(game.adminChannel, "The jailor is going to execute " + mentioned!!.effectiveName)
                    Messages.sendMessage(message.channel, "You have decided to execute " + mentioned!!.effectiveName)
                } else {
                    Messages.sendDM(message.author, "You can only shoot the person you have jailed!")
                }
                message.delete()
            }
            "vampire" -> {
                Messages.sendMessage(game.adminChannel, "The vampires are going to try and convert " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You will be biting " + mentioned!!.effectiveName)
                message.delete()
            }
            "vampire_hunter" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " will be checking and stabbing " + mentioned!!.effectiveName + " if they are a vampire")
                Messages.sendDM(message.author, "You will be checking " + mentioned!!.effectiveName)
                message.delete()
            }
            "lookout" -> {
                Messages.sendDM(message.author, "You will be watching " + mentioned!!.effectiveName + " tonight.")
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " will be watching " + mentioned!!.effectiveName + " tonight.")
                message.delete()
            }
            "investigator" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " would like to investigate " + mentioned!!.effectiveName + " tonight.")
                Messages.sendDM(message.author, "You are going to investigate " + mentioned!!.effectiveName)
                message.delete()
            }
            "sheriff" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + "would like to interrogate " + mentioned!!.effectiveName + " tonight.")
                Messages.sendDM(message.author, "You are going to be interrogating " + mentioned!! + " tonight.")
                message.delete()
            }
            "transporter" -> {
                val target = Utils.getUserFromInput(message, messageContent[2])
                val invest = Utils.getUserFromInput(message, messageContent[3])
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " would like to swap positions of " + invest!!.effectiveName + " & " + target!!.effectiveName)
                Messages.sendDM(message.author, "You will be transporting " + invest.effectiveName + " & " + target!!.effectiveName)
                message.delete()
            }
            "witch" -> {
                val target = Utils.getUserFromInput(message, messageContent[2])
                val invest = Utils.getUserFromInput(message, messageContent[3])
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " would like to control " + invest!!.effectiveName + " into using their ability onto " + target!!.effectiveName)
                Messages.sendDM(message.author, "You will be witching " + invest.effectiveName + " into " + target!!.effectiveName)
                message.delete()
            }
            "doctor" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " will be healing " + mentioned!!.effectiveName + " tonight.")
                Messages.sendDM(message.author, "You will be healing " + mentioned!!.effectiveName + " tonight.")
                message.delete()
            }
            "bodyguard" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " will be guarding " + mentioned!!.effectiveName + " tonight.")
                Messages.sendDM(message.author, "You will be guarding " + mentioned!!.effectiveName + " tonight.")
                message.delete()
            }
            "escort" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " would like to roleblock " + mentioned!! + " tonight.")
                Messages.sendDM(message.author, "You will be escorting " + mentioned!!.effectiveName + " tonight.")
                message.delete()
            }
            "consort" -> {
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " would like to roleblock " + mentioned!! + " tonight.")
                Messages.sendDM(message.author, "You will be escorting " + mentioned!!.effectiveName + " tonight.")
                message.delete()
            }
            "mayor" -> {
                Messages.sendMessage(message.channel, message.member.effectiveName + " has revealed themselves as the Mayor!")
                //Set the variable for mayor reveal to true
                MafiaConfig.setExtra(message, true)
                Messages.sendMessage(game.adminChannel, message.member.effectiveName + " has revealed as the mayor. Their votes now count as 3.")
                message.delete()
            }

            "medium" -> {
                if (!game.day && player.dead) {
                    Messages.sendMessage(game.adminChannel, message.member.effectiveName + " would like to talk to " + mentioned!!.effectiveName)
                    Messages.sendDM(message.author, "Your message has been sent to the Admin. Please wait for them to respond to your secance request")
                    message.delete()
                }
            }
            "retributionist" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " will be reviving " + mentioned!! + " tonight.")
                Messages.sendDM(message.author, "You will be reviving " + mentioned!!.effectiveName + " tonight.")
                message.delete()
            }
            "disguiser" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " is going to be disguised as " + mentioned!!.effectiveName + " in role.")
                MafiaConfig.setExtra(message, mentioned.user.idLong)
                MafiaListener.addDirtyValue(Triple(mentioned, Details.EXTRA, 0L))
                Messages.sendDM(message.author, "You will be disguising as " + mentioned!!.effectiveName)
                message.delete()
            }
            "forger" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " is gonna forge the role of " + mentioned!!.effectiveName + " to be Forger.")
                Messages.sendDM(message.author, "You will be forging the role of " + mentioned!!.effectiveName)
                message.delete()
            }
            "framer" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " is gonna frame " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You will be framing " + mentioned!!.effectiveName)
                message.delete()
            }
            "janitor" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " would like to clean the role of " + Utils.getMentionedUser(message)!!.effectiveName)
                Messages.sendDM(message.author, "You will be cleaning " + mentioned!!.effectiveName)
                message.delete()
            }
            "blackmailer" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + "would like to Blackmail " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You will be blackmailing " + mentioned!!.effectiveName)
                message.delete()
            }
            "consigerge" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " would like to know the role of " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You will receive the role of " + mentioned!!.effectiveName)
                message.delete()
            }
            "amnesiac" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " would like to remember the role of " + mentioned!!.effectiveName)
                Messages.sendDM(message.author, "You will remember the role of " + mentioned!!.effectiveName)
                message.delete()
            }
            "survivor" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " will be putting on a vest tonight.")
                Messages.sendDM(message.author, "You will be putting on a vest tonight.")
                message.delete()
            }
            "tracker" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " will be tracking " + mentioned!!.effectiveName + " tonight.")
                Messages.sendDM(message.author, "You will be tracking " + mentioned!!.effectiveName)
                message.delete()
            }
            "trapper" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " will be placing a trap at " + mentioned!!.effectiveName + "'s house tonight.")
                Messages.sendDM(message.author, "You will be placing a trap at " + mentioned!!.effectiveName + "'s house.")
                message.delete()
            }
            "court_wizard" -> {
                Messages.sendMessage(game.adminChannel, message.author.name + " will protect " + mentioned!!.effectiveName + " from Escorts, Consorts, Transporters, and Witches")
                Messages.sendDM(message.author, "You will be guarding " + mentioned!!.effectiveName)
                message.delete()
            }

        }


    }
}
