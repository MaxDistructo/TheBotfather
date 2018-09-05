package maxdistructo.discord.bots.botfather.commands.cah.init

interface ICAHCard{

val color : Enum<CardColor>
val text : String //The listed text on the Card (%s will be used for each blank and formatted output)
var blankValues : List<String> //A list containing the user provided values for each blank.

fun getFormattedOutput() : String{
  if(blankValues.size == 1){
    return String.format(text, blankValues[0])
  }
  else if(blankValues.size == 2){
    return String.format(text, blankValues[0], blankValues[1])
  }
  else{
    return String.format(text, blankValues[0], blankValues[1], blankValues[2]
  }
}

}
