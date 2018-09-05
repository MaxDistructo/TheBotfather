package maxdistructo.discord.bots.botfather.commands.cah.obj

class Deck : ICardHolder{

val guildID : Long
val channelID : Long
override val name = "Deck of $guildID:$channelID"
override val MAX_SIZE = -1 //Means the deck can hold infinite cards
override val cards : LinkedList<ICAHCard> = LinkedList()

fun drawCard() : ICAHCard{
  cards.shuffle //Placeholder for actual shuffle function
  val selectedCard = Random().nextInt((cards.size()) - 0) +  0
}

constructor(guild : Long, channel : Long){
  guildID = guild
  channelID = channel
}

}
