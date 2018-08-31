package maxdistructo.discord.bots.botfather.commands.mafia

import ch.qos.logback.classic.Level
import kotlinx.coroutines.experimental.launch
import maxdistructo.discord.bots.botfather.commands.mafia.edition2.Roles
import maxdistructo.discord.bots.botfather.commands.mafia.methods.MafiaConfig
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Details
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.core.jda.Config
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.command.ICommand
import maxdistructo.discord.core.jda.command.SimpleListener
import maxdistructo.discord.core.jda.message.Messages
import maxdistructo.discord.core.jda.message.Webhook
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import org.slf4j.LoggerFactory
import java.util.*

class MafiaListener : SimpleListener("Botfather.Mafia") {
    override var adminCommands: List<ICommand> = listOf()
    override var commandRegistry: LinkedList<ICommand> = LinkedList()
    override var commandsArray: List<ICommand> = listOf()
    override var modCommands: List<ICommand> = listOf()
    var logger = LoggerFactory.getLogger(this.name) as ch.qos.logback.classic.Logger
    lateinit var perms : Perms

    override fun guildMessage(event: MessageReceivedEvent) {
        logger.level = Level.DEBUG //Set to debug if you wish to have debug messages outputted
        logger.debug("Recieved Guild Message")
        val message = event.message
        if (message.contentRaw != "") {
            val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
            logger.debug("Message is not empty")
            if(message.channel.idLong == game.deadChannel.idLong){Webhook.send(game.mediumChannel, message.member.effectiveName, message.member.user.avatarUrl, message.contentRaw)}
            if(message.channel.idLong == game.mediumChannel.idLong){Webhook.send(game.deadChannel, "Medium", "https://i.imgur.com/WBTx4Kx.png", message.contentRaw)}
            if(message.channel.idLong == game.jailedChannel.idLong){Webhook.send(game.jailorChannel, message.member.effectiveName, message.member.user.avatarUrl, message.contentRaw)}
            if(message.channel.idLong == game.jailorChannel.idLong){Webhook.send(game.jailedChannel, "Jailor", "https://vignette.wikia.nocookie.net/town-of-salem/images/7/7e/Jailor.png/revision/latest/scale-to-width-down/150?cb=20151021224315", message.contentRaw)}
            if(message.channel.idLong == game.mafiaChannel.idLong){Webhook.send(game.spyChannel, "Mafia", "https://vignette.wikia.nocookie.net/town-of-salem/images/7/70/DarkRevenant.png/revision/latest/scale-to-width-down/87?cb=20140701002425", message.contentRaw)}
            if(message.channel.idLong == game.vampChannel.idLong){Webhook.send(game.vamphunterChannel, "Vampire", "https://vignette.wikia.nocookie.net/town-of-salem/images/4/4e/Vampire.png/revision/latest/scale-to-width-down/150?cb=20151101133009", message.contentRaw)}

            val messageContent = message.contentRaw.split(" ")
            perms = Perms(message.guild)
            if (messageContent[0] == Config.readPrefix() + "mafia") {
                launch {
                    when {
                        perms.checkAdmin(message) -> for (command in adminCommands) {
                            if (messageContent[1] == command.commandName) {
                                logger.debug("Command " + command.commandName + " is now running")
                                if (command.hasOutput) {
                                    Messages.sendMessage(message.channel, command.init(message, messageContent))
                                    message.delete().submit()
                                    break
                                } else {
                                    command.init(message, messageContent)
                                    message.delete().submit()
                                    break
                                }
                            } else {
                                logger.debug("Command " + command.commandName + " is invalid for the message")
                            }
                        }
                        perms.checkMod(message) -> for (command in modCommands) {
                            if (messageContent[1] == command.commandName) {
                                if (command.hasOutput) {
                                    Messages.sendMessage(message.channel, command.init(message, messageContent))
                                    message.delete().submit()
                                    break
                                } else {
                                    command.init(message, messageContent)
                                    message.delete().submit()
                                    break
                                }
                            } else {
                                logger.debug("Command " + command.commandName + " is invalid for the message")
                            }
                        }
                        else -> for (command in commandsArray) {
                            if (messageContent[1] == command.commandName) {
                                if (command.hasOutput) {
                                    Messages.sendMessage(message.channel, command.init(message, messageContent))
                                    message.delete().submit()
                                    break
                                } else {
                                    command.init(message, messageContent)
                                    message.delete().submit()
                                    break
                                }
                            } else {
                                logger.debug("Command " + command.commandName + " is invalid for the message")
                            }
                        }
                    }
                }
            }

        }
    }
    override fun privateMessage(event: MessageReceivedEvent) {
        logger.level = Level.INFO
        logger.debug("Received Private Message")
    }

    companion object {
        var dirtyValues : Array<Triple<Member, Enum<Details>, Any>?> = arrayOf()
        fun addDirtyValue(triple : Triple<Member,Enum<Details>,Any>){
            dirtyValues += triple
        }
        fun fixDirty(message : Message){
            var i = 0
            for(value in dirtyValues){
                MafiaConfig.editDetails(message, value!!.first, value.second, value.third)
                dirtyValues[i] = null
                i++
            }
        }
    }
}