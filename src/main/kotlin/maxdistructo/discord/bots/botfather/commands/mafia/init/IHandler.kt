package maxdistructo.discord.bots.botfather.commands.mafia.init

import net.dv8tion.jda.core.entities.Message


interface IHandler  {
    val name : String
    fun update(message : Message)
    fun reset(message : Message)
}