package maxdistructo.discord.bots.botfather.commands.standard

import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.Utils.s
import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.message.Messages
import maxdistructo.discord.core.jda.message.Webhook
import net.dv8tion.jda.core.entities.Icon
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.webhook.WebhookClient
import net.dv8tion.jda.webhook.WebhookClientBuilder
import net.dv8tion.jda.webhook.WebhookMessageBuilder
import java.io.File
import java.net.URL
import javax.xml.soap.Text
import java.nio.file.Paths
import java.nio.file.Files
import org.apache.commons.io.FilenameUtils
import java.io.FileOutputStream
import java.io.BufferedOutputStream
import java.io.BufferedInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.net.MalformedURLException





class WebhookSay : BaseCommand() {
    override val commandName: String
        get() = "webhook"
    override val helpMessage: String
        get() = "webhook @User <message> - Says the following message as the user"
    override val requiresMod: Boolean
        get() = true
    override val hasOutput : Boolean
        get() = true

    override fun init(message: Message, args: List<String>): String {
        onWebhookSay(args, message, Utils.getMentionedChannel(message) as TextChannel?)
        return ""
    }

    private fun onWebhookSay(args: List<Any>, message : Message, mentionedChannel: TextChannel?){
        val user = Utils.getUserFromInput(message, args[1])
        var anotherChannel = false
        val output : String
        if(mentionedChannel != null){
            anotherChannel = true
        }
        if(anotherChannel){
            output = Utils.makeNewString(args, 3)
            Webhook.send(mentionedChannel!!, user!!.effectiveName, user.user.avatarUrl, output )
        }
        else{
            output = Utils.makeNewString(args, 2)
            Webhook.send(message.textChannel, user!!.effectiveName, URL(user.user.avatarUrl), output)
        }
    }

    fun Webhook.send(channel : TextChannel, name : String, avatar : URL, message : String){
        val webhook = defaultWebhook(channel)
        try {
            val connection = avatar.openConnection()
            val userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"
            connection.setRequestProperty("User-Agent", userAgent)
            webhook.manager.setName(name).setAvatar(Icon.from(connection.getInputStream())).complete(true)
        }
        catch(e : Exception){
            Messages.throwError(e)
        }
        val builder : WebhookClientBuilder = webhook.newClient()
        val client : WebhookClient = builder.build()
        val messageBuilder = WebhookMessageBuilder()
        messageBuilder.setContent(message)
        client.send(messageBuilder.build())
        client.close()
    }

}