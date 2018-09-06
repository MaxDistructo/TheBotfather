package maxdistructo.discord.bots.botfather

object Converter{

  fun convertFile(file : File){
    val lines = file.readAllLines()
    val classLists = listOf<List<String>>()
    val store : Boolean = false
    var storageList : List<String> = listOf()
    for(line in lines){ //Loop through all lines in the file
      if(!store){
        if(line.contains("class"){ //If line is a class, check it further for more details
          val splitLine = line.split(" ")
          if(splitLine.contains("BaseCommand()")){ //The class line is split as we are looking for it to extend BaseCommand()
            store = true
          }
        }
      }
    }
  }

}
