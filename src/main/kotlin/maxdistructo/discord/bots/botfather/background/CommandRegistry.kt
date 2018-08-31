package maxdistructo.discord.bots.botfather.background

import maxdistructo.discord.bots.botfather.commands.Base
import maxdistructo.discord.bots.botfather.commands.Casino
import maxdistructo.discord.bots.botfather.commands.standard.*
import maxdistructo.discord.core.jda.command.IBaseListener
import maxdistructo.discord.core.jda.obj.ICommandRegistry

object CommandRegistry : ICommandRegistry {
    override fun registerCommands(listener: IBaseListener) {
        val payday = Casino.Payday()
        val clear = Base.Clear()
        val check = Check()
        val debug = Debug()
        val fortune = Fortune()
        val help = Help()
        val horoscope = Horoscope()
        val info = Info()
        val insult = Insult()
        val ping = Ping()
        val say = Say()
        val shutdown = Shutdown()
        val spam = Spam()
        val webhook = WebhookSay()
        val stab = PlayerFun.Stab()
        val slap = PlayerFun.Slap()
        val tnt = PlayerFun.Tnt()
        val kiss = PlayerFun.Kiss()
        val hug = PlayerFun.Hug()
        val poke = PlayerFun.Poke()
        val respect = PlayerFun.Respect()
        val banHammer = PlayerFun.BanHammer()
        val shoot = PlayerFun.Shoot()
        val xp = PlayerFun.Xp()
        val punch = PlayerFun.Punch()
        listener.registerCommand(ping, say, payday, clear, check, debug, fortune, help, horoscope, info, insult, shutdown, spam, webhook, stab, slap, tnt, kiss, hug, poke, respect, banHammer, shoot, xp, punch)
    }
}