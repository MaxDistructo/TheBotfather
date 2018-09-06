package maxdistructo.discord.bots.botfather.commands.cah.obj

import maxdistructo.discord.bots.botfather.commands.cah.init.ICAHCard
import maxdistructo.discord.bots.botfather.commands.cah.init.ICardHolder
import java.util.*

class Hand : ICardHolder {
  var userID : Long = 0L
override val cards : LinkedList<ICAHCard> //In case other versions implement a different version other than the default card (White Cards)
override val name : String = "Hand of $userID"
override val MAX_NUM = 10

constructor(id : Long){
  userID = id
  cards = LinkedList()
}

  fun newRound(deck : Deck){ //Adds a number of cards from the deck until the handsize matches what the size needs to be
    if(cards.size != MAX_NUM){
      while(cards.size < MAX_NUM){
        this.addCard(deck.drawCard())
      }
    }
      while(cards.size > MAX_NUM){
        this.removeCard(cards.last) //Last Value in the List of Cards
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
