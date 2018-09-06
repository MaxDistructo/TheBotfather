package maxdistructo.discord.bots.botfather.commands.cah.obj

import maxdistructo.discord.bots.botfather.commands.cah.init.ICAHCard

class BlackCard : ICAHCard {

override val color = CardColor.BLACK
override val text : String
override var blankValues : List<String>

constructor(textIn : String, vararg values : String){
  text = textIn
  blankValues = values.toList()
} 

constructor(textIn : String){
  text = textIn
  blankValues = listOf() //This will be used more often but if a card needs to be copied, the other method would be used.
}

}
