package maxdistructo.discord.bots.botfather.commands.cah.obj

class Hand : ICardHolder{

override val cards : LinkedList<ICAHCard> //In case other versions implement a different version other than the default card (White Cards)
override val name : String = "Hand of $userID"
val userID : Long

constructor(id : Long){
  userID = id
  cards = LinkedList()
}

  fun newRound(deck : Deck){ //Adds a number of cards from the deck until the handsize matches what the size needs to be
    if(cards.size != 10){
      while(cards.size < 10){
        this.addCard(deck.drawCard)
      }
    }
  }
  fun showCards() : String{
    val builder = StringBuilder()
    val i = 0
    for(card in cards){
      builder.append("Card $i:" + card.text.replace("%s", "_") + "\n")
    }
    return builder.toString()
  }
  fun playCard(cardNum : Int){
    this.removeCard(cards[(cardNum - 1)])
  }

}
