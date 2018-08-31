package maxdistructo.discord.bots.botfather.commands.mafia.action

import maxdistructo.discord.bots.botfather.commands.mafia.obj.Player
import maxdistructo.discord.core.jda.message.Messages

object ActionMessage {
    enum class Actions{
        HEAL,
        ATTACKED,
        TRANSPORTED,
        CONVERTED,
        IMMUNE,
        REMEMBERED,
        DNR,
        HEALED,
        RBED,
        NULL
    }
    fun getMessage(action : Enum<Actions>, user : Player){
        when(action){
            Actions.ATTACKED -> Messages.sendDM(user.user, "You were attacked last night!")
            Actions.REMEMBERED -> Messages.sendDM(user.user, "You have remembered who you were.")
            Actions.CONVERTED -> Messages.sendDM(user.user, "You have been converted to a Vampire!")
            Actions.IMMUNE -> Messages.sendDM(user.user, "Your target was immune to your attack!")
            Actions.DNR -> Messages.sendDM(user.user, "Your target did not require healing.")
            Actions.RBED -> Messages.sendDM(user.user, "You were roleblocked last night.")
            Actions.TRANSPORTED -> Messages.sendDM(user.user, "You were transported")
            Actions.HEAL -> Messages.sendDM(user.user, "You were attacked and healed")
            Actions.HEALED -> Messages.sendDM(user.user, "You have successfully healed your target.")
        }
    }
}