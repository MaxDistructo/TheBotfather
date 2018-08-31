package maxdistructo.discord.bots.botfather.background

import maxdistructo.discord.core.jda.obj.IPerms
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.Message

class Perms(guild : Guild) : IPerms{
    override var admins: List<Long> = listOf(374517920505790464L)
    override var mods: List<Long> = listOf()
    init{
        admins += LocalConfig.admins(guild)
        mods += LocalConfig.mods(guild)
    }
    fun checkMod(message : Message): Boolean {
        println("Checking Mod")
        return mods.contains(message.author.idLong) || admins.contains(message.author.idLong)
    }
    fun checkAdmin(message : Message) : Boolean{
        println("Checking Admin")
        return admins.contains(message.author.idLong)
    }
}