package maxdistructo.discord.bots.botfather.commands.mafia

import maxdistructo.discord.bots.botfather.commands.mafia.obj.Game
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.message.Webhook
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class Relays : ListenerAdapter() {
    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        val message = event.message
        val game = Game(Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt"))
        val perms = Perms(message.guild)
        if(!perms.checkMod(message) && !message.author.isBot) {
            if (message.channel.idLong == game.deadChannel.idLong) {
                Webhook.send(game.mediumChannel, message.member.effectiveName, message.member.user.avatarUrl, message.contentRaw)
            }
            if (message.channel.idLong == game.mediumChannel.idLong) {
                Webhook.send(game.deadChannel, "Medium", "https://i.imgur.com/WBTx4Kx.png", message.contentRaw)
            }
            if (message.channel.idLong == game.jailedChannel.idLong) {
                Webhook.send(game.jailorChannel, message.member.effectiveName, message.member.user.avatarUrl, message.contentRaw)
            }
            if (message.channel.idLong == game.jailorChannel.idLong) {
                Webhook.send(game.jailedChannel, "Jailor", "https://vignette.wikia.nocookie.net/town-of-salem/images/7/7e/Jailor.png/revision/latest/scale-to-width-down/150?cb=20151021224315", message.contentRaw)
            }
            if (message.channel.idLong == game.mafiaChannel.idLong) {
                Webhook.send(game.spyChannel, "Mafia", "https://vignette.wikia.nocookie.net/town-of-salem/images/7/70/DarkRevenant.png/revision/latest/scale-to-width-down/87?cb=20140701002425", message.contentRaw)
            }
            if (message.channel.idLong == game.vampChannel.idLong) {
                Webhook.send(game.vamphunterChannel, "Vampire", "https://vignette.wikia.nocookie.net/town-of-salem/images/4/4e/Vampire.png/revision/latest/scale-to-width-down/150?cb=20151101133009", message.contentRaw)
            }
        }
    }
}