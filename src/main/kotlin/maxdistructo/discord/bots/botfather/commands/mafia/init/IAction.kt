package maxdistructo.discord.bots.botfather.commands.mafia.init

import org.json.JSONObject

interface IAction {
    val player: Long
    val target: Long
    val target2: Long
    val action: String
    val extra : Any
    val name : String
    fun toJSON(): JSONObject {
        val obj = JSONObject()
        obj.put("target", target)
        obj.put("target2", target2)
        obj.put("action", action)
        obj.put("extra", extra)
        return obj
    }
}