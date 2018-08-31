package maxdistructo.discord.bots.botfather.commands.mafia.edition2

import net.dv8tion.jda.core.entities.MessageEmbed
import org.json.JSONArray
import org.json.JSONObject

interface IRole {
    val name : String
    val attack : Int
    val defence : Int
    val rolecard : MessageEmbed
    val alignment : String
    val classification : String
    val ability : IAbility
    val shortClass : String

    fun toJSON() : JSONObject{
        val json = JSONObject()
        json.put("name", name)
        json.put("attack",attack)
        json.put("defence", defence)
        json.put("alignment", alignment)
        json.put("role", name)
        json.put("class", shortClass)
        json.put("extra", JSONArray())
        return json
    }
}