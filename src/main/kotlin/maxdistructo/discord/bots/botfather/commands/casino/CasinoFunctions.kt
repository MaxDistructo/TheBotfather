package maxdistructo.discord.bots.botfather.commands.casino

import maxdistructo.discord.bots.botfather.commands.casino.obj.CasinoPlayer
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.PrivateChannel
import net.dv8tion.jda.core.requests.restaction.MessageAction
import java.time.LocalDateTime

object CasinoFunctions {
    fun givePayday(player : CasinoPlayer) : String{
        updateRank(player)
        if(player.canGetPayday()){
            var payday = 0
            when(player.rank){
                "none" ->{
                    player.chips = player.chips + 100
                    payday = 100
                    player.payday = LocalDateTime.now().dayOfYear
                }
                "A" ->{
                    player.chips += 100
                    payday = 100
                    player.payday = LocalDateTime.now().dayOfYear
                }
                "B" ->{
                    player.chips += 150
                    payday = 150
                    player.payday = LocalDateTime.now().dayOfYear
                }
                "C" -> {
                    player.chips += 150
                    payday = 150
                    player.payday = LocalDateTime.now().dayOfYear
                }
                "D" -> {
                    player.chips += 200
                    payday = 200
                    player.payday = LocalDateTime.now().dayOfYear
                }
                "E" ->{
                    player.chips += 200
                    payday = 200
                    player.payday = LocalDateTime.now().dayOfYear
                }
            }
            return "You have recieved your $payday payday"
        }
        else{
            return "You have already received your payday for today."
        }
    }

    fun updateRank(player : CasinoPlayer){
        val initialRank = player.rank
        val updatedRank: String = when(player.chips){
            in 0..999 -> "none"
            in 1000..2499 -> "A"
            in 2500..3999 -> "B"
            in 4000..5999 -> "C"
            in 6000..8499 -> "D"
            else -> "E"
        }
        if(initialRank != updatedRank){
            player.rank = updatedRank
            Messages.sendDM(player.getMember(), "Your casino rank for server " + player.getMember().guild.name + " has been changed to " + player.rank + "!")
        }
    }

    fun Messages.sendDM(member : Member, message : String) : Message {
        var pm: PrivateChannel? = null
        pm = member.user.openPrivateChannel().complete()
        lateinit var builder: MessageAction
        assert(pm != null)
        builder = pm!!.sendMessage(message)
        return builder.complete(true)
    }
}

