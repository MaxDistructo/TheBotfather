package maxdistructo.discord.bots.botfather.commands.cah.init

interface ICardHolder{

val MAX_NUM : Int //Used as a static value to know how many cards the holder can have at maximum.
val cards : LinkedMutableList<ICAHCard> //Actual list of the cards in the holder
val name : String

  fun addCard(card : ICAHCard){
    cards.add(card)
  }
  
  fun removeCard(card : ICAHCard){
    val removeCard : ICAHCard? = null
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
