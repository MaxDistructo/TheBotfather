package maxdistructo.discord.bots.botfather.commands.cah.init

import maxdistructo.discord.bots.botfather.commands.cah.obj.CardColor

interface ICAHCard{

val color : Enum<CardColor>
val text : String //The listed text on the Card (%s will be used for each blank and formatted output)
var blankValues : List<String> //A list containing the user provided values for each blank.

fun getFormattedOutput() : String{
  return when {
    blankValues.size == 1 -> String.format(text, blankValues[0])
    blankValues.size == 2 -> String.format(text, blankValues[0], blankValues[1])
    else -> String.format(text, blankValues[0], blankValues[1], blankValues[2])
  }
}

}
