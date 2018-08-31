package maxdistructo.discord.bots.botfather.background.coreadditions

import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.PrivateChannel
import net.dv8tion.jda.core.requests.restaction.MessageAction

//Storage location for common overrides.

object Overrides {
    fun Messages.sendDM(member : Member, message : String) : Message{
            var pm: PrivateChannel? = null
            pm = member.user.openPrivateChannel().complete()
            lateinit var builder: MessageAction
            assert(pm != null)
            builder = pm!!.sendMessage(message)
            return builder.complete(true)
        }
}