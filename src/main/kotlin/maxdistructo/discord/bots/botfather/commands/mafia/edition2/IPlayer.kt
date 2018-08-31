package maxdistructo.discord.bots.botfather.commands.mafia.edition2

import org.json.JSONArray


interface IPlayer {

        val role: IRole

        val dead: Boolean

        val attack: Int

        val defence: Int

        val id : Long

        val extra : JSONArray
}