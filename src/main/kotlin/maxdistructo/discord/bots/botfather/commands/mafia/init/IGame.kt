package maxdistructo.discord.bots.botfather.commands.mafia.init

import net.dv8tion.jda.core.entities.TextChannel


interface IGame {
    val adminChannel: TextChannel
    val mediumChannel: TextChannel
    val deadChannel: TextChannel
    val mafiaChannel: TextChannel
    val day: Boolean
    val spyChannel: TextChannel
    val jailorChannel: TextChannel
    val jailedChannel: TextChannel
    val dayChannel: TextChannel
    val vampChannel : TextChannel
    val vamphunterChannel : TextChannel
    val dayNum : Int
}