package maxdistructo.discord.bots.botfather.commands.mafia.obj

import maxdistructo.discord.bots.botfather.commands.mafia.edition2.Roles
import maxdistructo.discord.core.jda.Utils.s
import org.jetbrains.annotations.NotNull
import java.awt.Font
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

object DeathNoteMaker {

    @NotNull
    val image = ImageIO.read(URL("https://vignette.wikia.nocookie.net/town-of-salem/images/6/62/Death_Note.png/revision/latest/scale-to-width-down/180?cb=20150802183826"))!!
    val g = image.graphics

    private fun createNote(text : String){
        g.font = Font("Lucida Sans", Font.PLAIN, 18)
        g.drawString(text, 100,100)
        g.dispose()
        ImageIO.write(image, ".png", File(s + "deathnote.png"))
    }

    fun getFor(role : Enum<Roles>){

    }

}