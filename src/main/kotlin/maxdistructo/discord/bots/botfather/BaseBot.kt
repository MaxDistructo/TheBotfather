package maxdistructo.discord.bots.botfather

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import com.jagrosh.jdautilities.examples.command.AboutCommand
import com.jagrosh.jdautilities.examples.command.PingCommand
import maxdistructo.discord.bots.botfather.background.Listener
import maxdistructo.discord.bots.botfather.commands.Base
import maxdistructo.discord.bots.botfather.commands.MafiaCommands
import maxdistructo.discord.bots.botfather.commands.standard.*
import maxdistructo.discord.core.jda.Config
import maxdistructo.discord.core.jda.impl.Bot

import net.dv8tion.jda.core.JDA
import java.awt.Color
import java.io.File
import java.nio.charset.Charset

object BaseBot{
    lateinit var bot : Bot
    lateinit var client: JDA
    lateinit var logger: Logger
    var activeTrivia : MutableList<Int> = mutableListOf()
    var nextTrivia = 0

    @JvmStatic
    fun main(args : Array<String>){
        bot = Bot(Config.readToken())
        bot.registerListener(maxdistructo.discord.bots.botfather.background.logging.Logger())
        bot.registerCommands(PingCommand(), MafiaCommands.MafiaBaseCommand(), AboutCommand(Color.BLUE, "MaxDistructo's Bot", arrayOf("Mafia Games", "Horoscope", "Player Fun Commands")))
        bot.init()
       //bot.commandAPI.listener = Listener()
        client = bot.client
        logger = bot.logger
        if(!Config.checkDebug()){
            logger.level = Level.INFO
        }
    }

}

