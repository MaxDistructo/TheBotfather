package maxdistructo.discord.bots.botfather.background

object Exceptions{

  class BasicException(message : String?, functionName : String) : Exception(error){
    val error : String
    init{
      if(errorMessage == null){
        error = "Unkown Error Message from $functionName"
      }
      else{
        error = message
      }
    }
  }
  class CahException(errorMessage : String?) : BasicException(error){
    val error : String
    init{
      if(errorMessage == null){
        error = "Unknown CAH Module Exception"  
      }
      else{
        error = errorMessage
      }
    }
  }

}
