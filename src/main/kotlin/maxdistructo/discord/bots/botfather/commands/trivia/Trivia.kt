package maxdistructo.discord.bots.botfather.commands.trivia

import maxdistructo.discord.bots.botfather.BaseBot
import maxdistructo.discord.core.jda.Utils.s
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.io.File

object Trivia {

    fun readTrivia(listName: String): Pair<List<String>, List<String>> { //Returns a pair of questions and answers in 2 separate lists
        val triviaFile = File("$s/config/trivia/lists/$listName.txt").readLines()
        var questions = listOf<String>()
        var answers = listOf<String>()
        for (line in triviaFile) {
            val splitLine = line.split("`")
            questions += splitLine[0]
            answers += splitLine[1]
        }
        return Pair(questions, answers)
    }

    class Listener//Sets all the values needed to get this individual listener to only work in the one channel and be deactivatable
    (guildIDIn: Long, channelIDIn: Long, questionsIn: List<String>, answersIn: List<String>) : ListenerAdapter() {
        private val channelID: Long = channelIDIn
        private val guildID: Long = guildIDIn
        private val triviaInstance: Int
        private val questions: List<String> = questionsIn
        private val answers: List<String> = answersIn
        private var currentQuestion: Int
        private var scoreList: MutableList<Pair<Long, Int>>
        private var timeout = 0
        private var questionNumber = 0
        private var answer = ""
        private var waitingForAnswer = false

        init {
            BaseBot.activeTrivia.add(BaseBot.nextTrivia)
            triviaInstance = BaseBot.nextTrivia
            BaseBot.nextTrivia++
            currentQuestion = 0
            scoreList = mutableListOf()
        }

        override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
            val message = event.message


            if (message.guild.idLong == guildID && message.channel.idLong == channelID && BaseBot.activeTrivia.contains(triviaInstance)) { // If the guild and channel match and the listener is in active use, keep working through the code.
                if (waitingForAnswer) { // If the bot is looking for answers, it will go into this block for the answer checking
                    if (timeout <= 0) { //Timesout the question and procedes onto the next question. Lessthan or equal to is incase of timeout loop going one more time before geting shutoff
                        waitingForAnswer = false //Creates next question state
                        Messages.sendMessage(message.textChannel, "Nobody got the correct answer! The correct answer was $answer") //Gives the users feedback on the solution
                    }
                    if (message.contentRaw.toLowerCase() == answer && !message.author.isBot) { //Checks that the answer is valid and it was submitted at a valid time
                        waitingForAnswer = false //As this is true, it puts the code into a read off next question state.
                        Messages.sendMessage(message.textChannel, "Point to " + message.author.name)
                        scoreList = addPoint(scoreList, message.author.idLong)
                        Thread.sleep(3000L) //To give players a pause between questions
                        nextQuestion(message)
                    } else { // The answer was invalid so it posts a message saying it was wrong.
                        if (!message.author.isBot) {
                            Messages.sendMessage(message.textChannel, "Wrong Answer!")
                        }
                    }
                } else {
                    val isDone = checkDone(scoreList) //Returns the winning player if true in the pair
                    if (isDone.first) {
                        Messages.sendMessage(message.textChannel, "Player " + event.message.guild.getMemberById(isDone.second).asMention + " has won!")
                        BaseBot.activeTrivia.remove(triviaInstance) //Removes the value of this trivia listener instance
                        timeout = 0
                    } else {
                        nextQuestion(message)
                    }
                }
            }
        }

            fun startCountdown() {
                timeout = 3000
                while (timeout > 0) {
                    timeout--
                }
            }

            fun nextQuestion(message : Message) {
                currentQuestion++
                questionNumber++
                waitingForAnswer = true
                if (currentQuestion == (questions.size - 1)) { //To stop out of bounds error
                    currentQuestion = 0
                }
                answer = answers[currentQuestion]
                timeout = 0 //To stop previous clock
                startCountdown()
                Messages.sendMessage(message.textChannel, "Question number: $questionNumber - " + questions[currentQuestion])
            }

            fun checkDone(scoreList: MutableList<Pair<Long, Int>>): Pair<Boolean, Long> { // Will loop through all values of list checking if a player has won
                for (value in scoreList) {
                    if (value.second == 10) {
                        return Pair(true, value.first)
                    }
                }
                return Pair(false, 0L)
            }

            fun addPoint(scoreList: MutableList<Pair<Long, Int>>, player: Long): MutableList<Pair<Long, Int>> { //Adds a point to a user's score total
                var canEnd = false
                val newScoreList = scoreList //Copy of ScoreList to return after modifications
                for (value in scoreList) {
                    if (value.first == player) {
                        newScoreList.remove(Pair(player, value.second))
                        newScoreList.add(Pair(player, (value.second + 1))) //Adds 1 to the current value.second
                        canEnd = true
                        break
                    }
                }
                return if (canEnd) {
                    newScoreList
                } else { //Player didn't have a score value already in the list so we now need to add them
                    newScoreList.add(Pair(player, 1)) //Adds the new Pair for this player's score value
                    newScoreList
                }
            }




}
}
