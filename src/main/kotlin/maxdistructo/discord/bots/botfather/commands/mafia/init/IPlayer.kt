package maxdistructo.discord.bots.botfather.commands.mafia.init

import maxdistructo.discord.bots.botfather.commands.mafia.obj.Roles

interface IPlayer {
    val role: Enum<Roles>

    val dead: Boolean

    val attack: Int

    val defence: Int

    val id : Long
}
