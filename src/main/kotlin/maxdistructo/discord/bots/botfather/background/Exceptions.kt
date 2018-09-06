package maxdistructo.discord.bots.botfather.background

object Exceptions{

  open class BasicException(message : String?, functionName : String) : Exception(){
    private val error : String = message ?: "Unknown Error Message from $functionName"
  }

  private val errorVal: String? = ""

  class CahException(errorMessage : String?) : BasicException(errorVal, "Cards Against Humanity"){
    private var errorVal : String = ""
    init{
      errorVal = errorMessage ?: "Unknown CAH Module Exception"
    }
  }

}
