package maxdistructo.discord.bots.botfather.commands.cah.obj

/**
* @class WhiteCard
* Used to represent a WhiteCard from Cards Against Humanity
*/

class WhiteCard : ICAHCard{

override val color = CardColor.WHITE
override val text : String
override var blankValues : List<String>

constructor(textIn : String, vararg values : String){
  text = textIn
  blankValues = values
} 

constructor(textIn : String){
  text = textIn
  blankValues = listOf() //This will be used more often but if a card needs to be copied, the other method would be used.
}

}
