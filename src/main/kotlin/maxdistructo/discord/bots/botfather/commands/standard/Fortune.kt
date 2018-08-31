package maxdistructo.discord.bots.botfather.commands.standard

import com.mashape.unirest.http.Unirest
import maxdistructo.discord.core.jda.command.BaseCommand
import maxdistructo.discord.core.jda.message.Messages
import org.json.JSONObject
import net.dv8tion.jda.core.entities.Message

class Fortune : BaseCommand() {

    override val commandName: String
        get() = "fortune"
    override val helpMessage: String
        get() = "fortune - Gets your fortune"
    override val hasOutput: Boolean
        get() = false

    override fun init(message: Message, args: List<String>): String {
        return onFortuneCommand(message)
    }

    private fun onFortuneCommand(message: Message): String {
        var fortune: JSONObject? = null
        try {
            fortune = Unirest.get("https://helloacm.com/api/fortune").asJson().body.`object`
        } catch (e: Exception) {
            Messages.throwError(e, message)
        }

        return if (fortune != null) {
            "Your fortune is " + fortune.get("fortune")
        } else "Your fortune seems to be out of reach. (API Error)"
    }
}
