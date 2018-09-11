package maxdistructo.discord.bots.botfather.commands.mafia.obj

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.bots.botfather.commands.mafia.methods.MafiaConfig
import maxdistructo.discord.bots.botfather.commands.mafia.init.IPlayer
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.User

class Player : IPlayer {
    private var details: Array<Any>? = null
    private var privPlayerID: Long? = 0

    override val role: Enum<Roles>
        get() = getEnum(details!![2] as String)
    val allignment: String
        get() = details!![0] as String

    override val dead: Boolean
        get() = details!![3] as Boolean

    override val attack: Int
        get() = details!![4] as Int

    override val defence: Int
        get() = details!![5] as Int

    override val id : Long
        get() = privPlayerID as Long

    val extra : Any
        get() = details!![6]

    val user : User
        get () = BaseBot.client.getUserById(privPlayerID!!)

    val roleString: String
        get() = role.name

    constructor() {
        details = null
        privPlayerID = null
    }

    constructor(detailsin: Array<Any>) {
        details = detailsin
        privPlayerID = null
    }

    constructor(message: Message, playerID: Long) {
        details = MafiaConfig.getPlayerDetails(message, playerID)
        privPlayerID = playerID
    }

    constructor(message: Message, player: Member) {
        details = MafiaConfig.getPlayerDetails(message, player.user.idLong)
        privPlayerID = player.user.idLong
    }

    fun getEnum(s : String) : Enum<Roles>{
        for(value in Roles.values()){
            if(value.name == s){
                return value
            }
        }
        return Roles.SELF
    }
}
