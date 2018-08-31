package maxdistructo.discord.bots.botfather.commands.mafia.obj

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.bots.botfather.commands.mafia.init.IGame
import net.dv8tion.jda.core.entities.TextChannel
import org.json.JSONObject

class Game(input: JSONObject) : IGame {
    private var info: JSONObject = input
    override val adminChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("admin_chat"))
    override val day: Boolean
        get() = info.getBoolean("day")
    override val deadChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("dead_chat"))
    override val jailedChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("jailed_chat"))
    override val jailorChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("jailor_chat"))
    override val mafiaChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("mafia_chat"))
    override val mediumChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("medium_chat"))
    override val spyChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("spy_chat"))
    override val dayChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("day_chat"))
    override val vampChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("vamp_chat"))
    override val vamphunterChannel: TextChannel
        get() = BaseBot.client.getTextChannelById(info.getLong("vamphunter_chat"))
    override val dayNum: Int
        get() = info.getInt("daynum")
}