package maxdistructo.discord.bots.botfather

import java.io.File
import java.nio.charset.Charset

object Converter{

  fun convertFile(file : File){
    val lines = file.readLines(Charset.defaultCharset())
    var classLists = listOf<List<String>>()
    var store : Boolean = false
    var storageList : List<String> = listOf()
    var imports : List<String> = listOf()
    var `package` : String = ""
    for(line in lines){ //Loop through all lines in the file
      if(line.split(" ").contains("import")){ //If the line is an import line, add it to the list of imports that the class needed
        imports += line
      }
      if(line.split(" ").contains("package")){ //Store the package of the original class so that it can be tagged back onto the output clas
        `package` = line
      }
      if(!store){
        if(line.split(" ").contains("class")){ //If line is a class, check it further for more details
          val splitLine = line.split(" ")
          if(splitLine.contains("BaseCommand()")){ //The class line is split as we are looking for it to extend BaseCommand()
            store = true //Puts the code into storage mode until all lines from this class is collected for processing.
            storageList += line //Stores the class declaration for the future processing.
          }
        }
      }
     else{
      if(line.split(" ").contains("return \"\"")){ //If the line contains the return string, store the completed class string list enter back into searching mode
        storageList += "}" //End Class Bracket
        classLists.plus(storageList) //Store the class into the list of classes that have been read
        storageList = listOf() //Reset the class list store for the next one.
      }
         else{
          storageList += line
         }
     }
    }
         //All classes the extend BaseCommand() have been collected from the file and now they need to be processed into the new format.
         
         if(!imports.contains("")){ //import for Command and CommandEvent(If 1 is imported, most likely the other is as the Execute method requires the other.
          imports += "import com.jagrock" //Package for Command
          imports += "import com.jagrock" //Package for CommandEvent
         }
         
         var name : String = ""
         var roleRequirement : Boolean = false
         var helpMessage : String = ""
         
         for(value in classLists){ //Start all conversion. Can also be simply stated as collection of data from existing code.
           
         }
         
  }

}
