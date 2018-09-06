package maxdistructo.discord.bots.botfather.commands.cah.init

import maxdistructo.discord.bots.botfather.background.Exceptions
import java.util.*

interface ICardHolder{

val MAX_NUM : Int //Used as a static value to know how many cards the holder can have at maximum.
val cards : LinkedList<ICAHCard> //Actual list of the cards in the holder
val name : String

  fun addCard(card : ICAHCard){
    cards.add(card)
  }
  
  fun removeCard(card : ICAHCard){
    var removeCard : ICAHCard? = null
    for(value in cards){
      if(value == card){
        removeCard = value
      }
    }
    if(removeCard != null){
      cards.remove(removeCard)
    }
    else{
      throw Exceptions.CahException("Unable to find card in CardHolder $name")
    }
  }

}
