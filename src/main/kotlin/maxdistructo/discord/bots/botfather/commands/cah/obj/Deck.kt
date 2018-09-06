package maxdistructo.discord.bots.botfather.commands.cah.obj

import maxdistructo.discord.bots.botfather.commands.cah.init.ICAHCard
import maxdistructo.discord.bots.botfather.commands.cah.init.ICardHolder
import java.util.*

class Deck : ICardHolder {
     override val MAX_NUM = -1
    var guildID : Long  = 0
    var channelID : Long = 0
override val name = "Deck of $guildID:$channelID"
override val cards : LinkedList<ICAHCard> = LinkedList()

fun drawCard() : ICAHCard{
  cards.shuffle() //Placeholder for actual shuffle function
  val selectedCard = Random().nextInt((cards.size) - 0) +  0
    return cards[selectedCard]
}

constructor(guild : Long, channel : Long){
  guildID = guild
  channelID = channel
}

}
