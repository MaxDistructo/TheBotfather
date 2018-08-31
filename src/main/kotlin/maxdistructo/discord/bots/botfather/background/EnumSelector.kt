package maxdistructo.discord.bots.botfather.background

import maxdistructo.discord.bots.botfather.commands.mafia.action.ActionMessage

object EnumSelector {
    fun mafiaAction(name : String) : Enum<ActionMessage.Actions>{
        return when(name.toLowerCase()){
            "dnr" -> ActionMessage.Actions.DNR
            "healed" -> ActionMessage.Actions.HEALED
            "washealed" -> ActionMessage.Actions.HEAL
            "converted" -> ActionMessage.Actions.CONVERTED
            "attacked" -> ActionMessage.Actions.ATTACKED
            "transed" -> ActionMessage.Actions.TRANSPORTED
            "transported" -> ActionMessage.Actions.TRANSPORTED
            "remembered" -> ActionMessage.Actions.REMEMBERED
            "roleblocked" -> ActionMessage.Actions.RBED
            "rbed" -> ActionMessage.Actions.RBED
            else -> ActionMessage.Actions.NULL
        }
    }
}