package maxdistructo.discord.bots.botfather.commands.mafia.edition2

import maxdistructo.discord.bots.botfather.commands.mafia.methods.MafiaConfig
import maxdistructo.discord.core.jda.Utils
import net.dv8tion.jda.core.entities.*
import org.json.JSONArray

class Player : IPlayer {
    override val role: IRole
    override val dead: Boolean
    override val attack: Int
    override val defence: Int
    override val id: Long
    override val extra: JSONArray

    constructor(message : Message, member : Member){
        id = member.user.idLong
        attack = MafiaConfig.getPlayerDetails(message, member.user.idLong)[4] as Int
        defence = MafiaConfig.getPlayerDetails(message, member.user.idLong)[5] as Int
        dead = MafiaConfig.getPlayerDetails(message, member.user.idLong)[3] as Boolean
        role = Roles.valueOf(MafiaConfig.getPlayerDetails(message, member.user.idLong)[2] as String)
        extra = getExtra(message.guild.idLong, member.user.idLong)
    }
    constructor(id : Long, attack : Int, defence : Int, dead : Boolean, role : IRole, extra : JSONArray){
        this.id = id
        this.attack = attack
        this.defence = defence
        this.dead = dead
        this.role = role
        this.extra = extra
    }
    constructor(){
        id = 0
        role = Roles.DEFAULT
        attack = role.attack
        defence = role.defence
        dead = false
        extra = JSONArray()
    }

    private fun getExtra(guildId : Long, memberId : Long) : JSONArray{
        val root1 = Utils.readJSONFromFile("/config/mafia/" + guildId + "_playerdat.txt")
        val root = root1.getJSONObject("" + memberId)
        return root.getJSONArray("extra")
    }
}