package maxdistructo.discord.bots.botfather.background

import ch.qos.logback.classic.Level
import kotlinx.coroutines.experimental.launch
import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.core.jda.Config
import maxdistructo.discord.core.jda.command.ICommand
import maxdistructo.discord.core.jda.command.SimpleListener
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import org.slf4j.LoggerFactory
import java.util.*

class Listener : SimpleListener("Botfather.Main") {
    override var adminCommands: List<ICommand> = listOf()
    override var commandRegistry: LinkedList<ICommand> = LinkedList()
    override var commandsArray: List<ICommand> = listOf()
    override var modCommands: List<ICommand> = listOf()
    var logger = LoggerFactory.getLogger(this.name) as ch.qos.logback.classic.Logger

    override fun guildMessage(event: MessageReceivedEvent) {
        logger.level = Level.DEBUG
        logger.debug("Recieved Guild Message")
        val message = event.message
        if (message.contentRaw != "") {
            launch {
                val messageContent = message.contentRaw.split(" ")
                val perms = Perms(message.guild)
                when {
                    perms.checkAdmin(message) -> for (command in adminCommands) {
                        if (messageContent[0] == Config.readPrefix() + command.commandName) {
                            logger.debug("Running command " + command.commandName)
                            if (!command.hasOutput) {
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
                        if (messageContent[0] == Config.readPrefix() + command.commandName) {
                            if (!command.hasOutput) {
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
                        if (messageContent[0] == Config.readPrefix() + command.commandName) {
                            if (!command.hasOutput) {
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

    override fun privateMessage(event: MessageReceivedEvent) {
        logger.level = Level.INFO
        BaseBot.logger.debug("Recieved Private Message")
    }
}