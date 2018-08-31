package maxdistructo.discord.bots.botfather.background

import maxdistructo.discord.core.jda.Config
import net.dv8tion.jda.core.entities.Guild

object LocalConfig {
    fun admins(guild : Guild) : List<Long>{
        return Config.readServerAdminConfig(guild)
    }
    fun mods(guild : Guild) : List<Long>{
        return Config.readServerModConfig(guild)
    }
}