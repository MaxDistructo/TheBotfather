package maxdistructo.discord.bots.botfather.commands.mafia.edition2

import maxdistructo.discord.bots.botfather.commands.mafia.init.IAction
import net.dv8tion.jda.core.entities.Member

interface IAbility {
    val name : String
    fun use(member : Member, target1 : Long, target2 : Long) : String{
       return ("Ability $name's targets are $target1 and $target2 in guild " + member.guild.idLong)
    }
    fun use(member : Member, action : IAction) : String{
        return ("Ability " + action.player + "'s targets are " + action.target + " and " + action.target2 + "in guild " + member.guild.idLong)
    }
    val actionName : String //Must match the name specified in the IAction connected to this IAbility
}