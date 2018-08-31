package maxdistructo.discord.bots.botfather.commands.standard

import com.mashape.unirest.http.Unirest
import com.mashape.unirest.http.exceptions.UnirestException
import maxdistructo.discord.bots.botfather.background.PrivUtils
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.command.BaseCommand
import org.json.JSONObject
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.User
import java.util.concurrent.ThreadLocalRandom

class Insult : BaseCommand() {
    override val commandName: String
        get() = "insult"
    override val helpMessage: String
        get() = "insult <@User> - Insults the provided user."
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        return onInsultCommand(PrivUtils.listToArray(args), message, Utils.getMentionedUser(message)!!)
    }

    private fun onInsultCommand(args: Array<String>, message: Message, mentioned: Member): String {
        val author = message.member
        val guild = message.guild
        val name = mentioned.effectiveName
        val from = author.effectiveName
        val insults = arrayOf("anyway/$name/$from", "asshole/$from", "bag/$from", "ballmer/$name/$name/$from", "blackadder/$name/$from")
        val randomNum = ThreadLocalRandom.current().nextInt(0, insults.size)
        if (message.jda.selfUser as User === mentioned.user) {
            return author.asMention + "How original. No one else had thought of trying to get the bot to insult itself. I applaud your creativity. \"yawn\" Perhaps this is why you don't have friends. You don't add anything new to any conversation. You are more of a bot than me, predictable answers, and absolutely dull to have an actual conversation with."
        } else {
            var insult: JSONObject? = null
            try {
                insult = Unirest.get("http://foaas.herokuapp.com/" + insults[randomNum]).header("Accept", "application/json").asJson().body.`object`
            } catch (e: UnirestException) {
                e.printStackTrace()
            }

            println(insult)
            return if (insult != null) {
                insult.getString("message")
            } else {
                "This bot is so stupid, your stupidity is even worse. (API Error)"
            }
        }

    }
}
