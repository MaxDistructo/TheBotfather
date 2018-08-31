package maxdistructo.discord.bots.botfather.background.logging

import maxdistructo.discord.bots.botfather.BaseBot
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.text.DateFormatter

class Logger : ListenerAdapter() {
    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent){
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        BaseBot.logger.info(formatter.format(currentTime) + " [" + event.guild.name + "] " + "#" + event.message.channel.name + " " + event.message.author.name + "#" + event.message.author.discriminator + ": " + event.message.contentDisplay)
    }

    override fun onPrivateMessageReceived(event: PrivateMessageReceivedEvent) {
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ISO_LOCAL_TIME
        BaseBot.logger.info(formatter.format(currentTime) +" [PM]" + " " + event.message.author.name + "#" + event.message.author.discriminator + ": " + event.message.contentDisplay)
    }

}