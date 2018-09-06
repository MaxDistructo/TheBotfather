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
    fun baseCommandToCommand(commands : List<BaseCommand>, files : List<File>){
int i = 0
for(value in commands){
val file = File(files[i])
val fileLines = file.toLines()
val name = toClassName(value.commandName)
val requiresMod = if(fileLines.contains("override val requiresMod"){true} else{false}
val requiresAdmin = value.requiresAdmin
val helpMessage = value.helpMessage


println("Your New Class: \n")
println("class $name : Command(){"
println("	init {"
println("		this.name = $name"
println("		this.help = $helpMessage"
if(requiresMod || requiresAdmin){
println("		this.requiredRole = /"Mafia Admin/""
}
println("		this.guildOnly = true"
println("	}"
println("override fun execute (event : CommandEvent){"
var lookingForStart : Boolean = true
for(line in file.toLines()){ //Replace with actual to lines code
	if(lookingForStart){
		if(line.contains("override fun init(message : Message, args : List<String>): String /{") //Starting line of the init function that needs to be copied except for the return method.
			lookingForStart = false
		}
	}
	else{
		if(!line.contains("return /"/""){
			println(line)
		}
		else{
			break
		}
	}

}
println("	}")
println("}")
println("\n")
println("\n")
i++
}
}

}
