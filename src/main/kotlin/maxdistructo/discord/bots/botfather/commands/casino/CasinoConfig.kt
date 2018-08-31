package maxdistructo.discord.bots.botfather.commands.casino

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.bots.botfather.commands.casino.obj.CasinoPlayer
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.Utils.s
import net.dv8tion.jda.core.entities.*
import org.json.JSONObject
import java.io.File

object CasinoConfig {
    fun joinCasino(message : Message) : JSONObject{
        updateData(message, message.member, CasinoPlayer.getDefaultJSON())
        return CasinoPlayer.getDefaultJSON()
    }
    fun readCasino(message : Message, member : Member) : JSONObject{
        val file = File(s + "/config/casino/" + member.guild.idLong + ".txt")
        if(!file.exists()){
            file.createNewFile()
        }
        val json = Utils.readJSONFromFile("/config/casino/" + member.guild.idLong + ".txt")
        return try{
            json.getJSONObject("" + member.user.idLong)
        }
        catch(e : Exception){
            joinCasino(message)
        }
    }
    fun updateData(message : Message, player: CasinoPlayer){
        updateData(message, player.getMember(message.guild), player.playerInfo)
    }
    fun updateData(message : Message, member : Member, playerJson : JSONObject){
        BaseBot.logger.debug("Updating data for " + member.effectiveName)
        val file = File(s + "/config/casino/" + message.guild.idLong + ".txt")
        if(!file.exists()){
            file.createNewFile()
        }
        val json = Utils.readJSONFromFile("/config/casino/" + message.guild.idLong + ".txt")
        json.remove("" + member.user.idLong)
        json.put("" + member.user.idLong, playerJson)
        Utils.writeJSONToFile("/config/casino/" + message.guild.idLong + ".txt", json)
    }
}