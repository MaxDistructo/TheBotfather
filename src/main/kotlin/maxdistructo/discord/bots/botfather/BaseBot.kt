package maxdistructo.discord.bots.botfather

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import maxdistructo.discord.bots.botfather.background.*
import maxdistructo.discord.bots.botfather.commands.MafiaCommands
import maxdistructo.discord.bots.botfather.commands.mafia.MafiaListener
import maxdistructo.discord.core.jda.Config
import maxdistructo.discord.core.jda.command.IBaseListener
import maxdistructo.discord.core.jda.impl.Bot

import maxdistructo.discord.core.jda.obj.IBot
import maxdistructo.discord.core.jda.obj.ICommandRegistry
import maxdistructo.discord.core.jda.obj.IDiscordBot
import net.dv8tion.jda.core.JDA
import java.util.*

object BaseBot : IDiscordBot {
    override lateinit var bot: IBot
    override lateinit var client: JDA
    override lateinit var logger: Logger
    override var registry: LinkedList<Pair<ICommandRegistry, IBaseListener>> = LinkedList()
    lateinit var listener : IBaseListener
    var activeTrivia : MutableList<Int> = mutableListOf()
    var nextTrivia = 0

    @JvmStatic
    fun main(args : Array<String>){
        initObj()
        bot = Bot()
        bot.setToken(Config.readToken())
        for(value in registry) {
            value.first.registerCommands(value.second)
            if(value.second.name == "Botfather.Main"){
                listener = value.second
            }
            bot.addListener(value.second)
        }
        bot.init()
        bot.registerListeners()
        client = bot.client
        logger = bot.logger
        if(!Config.checkDebug()){
            logger.level = Level.INFO
        }
        bot.client.addEventListener(maxdistructo.discord.bots.botfather.background.logging.Logger())
    }

    private fun initObj(){
        this.registerCommandRegistry(CommandRegistry, Listener())
        this.registerCommandRegistry(MafiaCommands, MafiaListener())
    }
}