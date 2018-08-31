package maxdistructo.discord.bots.botfather.commands.casino.obj

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.bots.botfather.commands.casino.CasinoConfig
import maxdistructo.discord.bots.botfather.commands.casino.CasinoFunctions
import maxdistructo.discord.core.jda.obj.IGamePlayer
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import org.json.JSONObject
import java.time.LocalDateTime
import kotlin.properties.Delegates

class CasinoPlayer : IGamePlayer {
    override var id: Long = 0L
    var jda: JDA?
    private var privMessage : Message? = null
    private var firstInfo : JSONObject = JSONObject()
    private var firstChips : Int = 0
    private var firstPayday : Int = 0
    private var firstRank : String = ""
    var rank : String by Delegates.observable(firstRank) {_, _, new -> onValueUpdate(id, "rank", new)}
    override var playerInfo: JSONObject by Delegates.observable(firstInfo) { _, _, _ -> CasinoConfig.updateData(privMessage!!, this)}
    var chips: Int by Delegates.observable(firstChips) { _, _, new -> onValueUpdate(id, "chips", new) }
    var payday: Int by Delegates.observable(firstPayday) {_, _, new -> onValueUpdate(id, "payday", new)}

    constructor() {
        id = 0L
        firstInfo = JSONObject()
        jda = null
        privMessage = null
        firstChips = 0
        firstPayday = 0
        firstRank = "none"
    }

    constructor(message: Message, member: Member) {
        id = member.user.idLong
        firstInfo = CasinoConfig.readCasino(message, member)
        jda = message.jda
        privMessage = message
        firstChips = try {firstInfo.getInt("chips")} catch (e : Exception){e.printStackTrace(); 100}
        firstPayday = try {firstInfo.getInt("payday")} catch (e : Exception){0}
        firstRank = try {firstInfo.getString("rank")} catch (e : Exception){"none"}
    }

    fun getMember(message: Message): Member {
        return getMember(message.guild)
    }

    fun getMember(guild: Guild): Member {
        return guild.getMemberById(id)
    }

    fun getMember() : Member{
        return privMessage!!.guild.getMemberById(id)
    }

    fun getUser() {
        jda!!.getUserById(id)
    }

    fun canGetPayday() : Boolean{
        return if(payday == 365 && LocalDateTime.now().dayOfYear == 1) {//December 31st - January 1st catch
            true
        }
        else if(payday == 366 && LocalDateTime.now().dayOfYear == 1){ //Catch variant for leap year
            true
        }
        else payday < LocalDateTime.now().dayOfYear
    }

    private fun onValueUpdate(user : Long, name : String, updated : Any){
        BaseBot.logger.debug("Updated $user's $name to $updated")
        CasinoFunctions.updateRank(this)
        val json = JSONObject()
        json.put("payday", payday)
        json.put("chips", chips)
        json.put("rank", rank)
        BaseBot.logger.debug("PlayerInfo JSON writing")
        playerInfo = json
    }

    companion object {
        fun getDefaultJSON() : JSONObject{
            val json = JSONObject()
            json.put("payday", 0)
            json.put("chips", 100)
            json.put("rank", "none")
            return json
        }
    }
}
