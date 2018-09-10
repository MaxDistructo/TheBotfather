package maxdistructo.discord.bots.botfather.commands.mafia.methods

import maxdistructo.discord.bots.botfather.commands.mafia.Perms
import maxdistructo.discord.bots.botfather.commands.mafia.obj.Details
import maxdistructo.discord.core.jda.Roles
import maxdistructo.discord.core.jda.Utils
import maxdistructo.discord.core.jda.Utils.s
import maxdistructo.discord.core.jda.message.Messages
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.User
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Paths

object MafiaConfig {
    fun getPlayers(message: Message): LongArray {
        val root1 = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_players.txt")
        val jsonArray = root1.getJSONArray("players")
        val strArray = LongArray(jsonArray.length())
        for (i in 0 until jsonArray.length()) {
            strArray[i] = jsonArray.getLong(i)
        }
        return strArray
    }

    fun getPlayers(message: Message, role: String): LongArray {
        val usersList = message.guild.getMembersWithRoles(Roles.getRole(message, role))
        val removeList = mutableListOf<Member>()
        val perms = Perms(message.guild)
        for (user in usersList) {
            if (perms.checkMod(user.user.idLong)) {
                removeList.add(user)
                if(user.roles.contains(Roles.getRole(message, "Alive(Mafia)")) || user.roles.contains(Roles.getRole(message, "Dead(Mafia)"))){
                    removeList.remove(user)
                }
            }
        }
        if(usersList.contains(message.guild.getMemberById((message.jda.selfUser as User).idLong))){
            removeList.add(message.guild.getMemberById((message.jda.selfUser as User).idLong))
        }
        usersList.removeAll(removeList)
        val players = LongArray(usersList.size)
        var i = 0
        for (user in usersList) {
            players[i] = user.user.idLong
            i++
        }
        return players
    }

    fun getPlayerDetails(message: Message): Array<Any> {
        val perms = Perms(message.guild)
        return if (!perms.checkMod(message)) {
            val root1 = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
            val root = root1.getJSONObject("" + message.author.idLong)
            val list = arrayListOf<Any>(root.getString("alignment"), root.getString("class"), root.getString("role"), message.member.roles.contains(Roles.getRole(message, "Dead(Mafia)")), root.getInt("attack"), root.getInt("defense"), root.get("extra"))
            list.toArray()
        } else if(perms.checkMod(message) && message.member.roles.contains(message.guild.getRolesByName("Alive(Mafia)", false)[0]) || message.member.roles.contains(message.guild.getRolesByName("Dead(Mafia)", false)[0])){
            val root1 = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
            val root = root1.getJSONObject("" + message.author.idLong)
            val list = arrayListOf<Any>(root.getString("alignment"), root.getString("class"), root.getString("role"), message.member.roles.contains(Roles.getRole(message, "Dead(Mafia)")), root.getInt("attack"), root.getInt("defense"), root.get("extra"))
            list.toArray()
        }
        else {
            val list = arrayListOf<Any>("admin", "admin", "admin", false, 3, 3)
            list.toArray()
        }
    }

    fun getPlayerDetails(message: Message, playerID: Long): Array<Any> {
        val perms = Perms(message.guild)
        return if (perms.checkMod(playerID)) {
            val root1 = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
            val root = root1.getJSONObject("" + playerID)
            val list = arrayListOf<Any>(root.getString("alignment"), root.getString("class"), root.getString("role"), message.guild.getMemberById(playerID).roles.contains(Roles.getRole(message, "Dead(Mafia)")), root.getInt("attack"), root.getInt("defense"), root.get("extra"))
            list.toArray()
            // } else if(perms.checkMod(playerID) && message.guild.getMemberById(playerID).roles.contains(message.guild.getRolesByName("Alive(Mafia)", false)[0]) || message.guild.getMemberById(playerID).roles.contains(message.guild.getRolesByName("Dead(Mafia)", false)[0])){
            //   val root1 = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
            // val root = root1.getJSONObject("" + message.author.idLong)
            //val list = arrayListOf<Any>(root.getString("alignment"), root.getString("class"), root.getString("role"), message.member.roles.contains(Roles.getRole(message, "Dead(Mafia)")), root.getInt("attack"), root.getInt("defense"), root.get("extra"))
            //return list.toArray()
        } else {
            val list = arrayListOf<Any>("admin", "admin", "admin", false, 3, 3,"")
            list.toArray()
        }
    }

    fun shuffleJSONArray(jsonArray: JSONArray): List<String?> {
        val list = mutableListOf<String?>()
        for (i in 0 until jsonArray.length()) {
            list.add(jsonArray.getString(i))
        }
        list.shuffle()
        return list
    }

    fun writeGame(message: Message, `object`: JSONObject) {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/config/mafia/" + message.guild.idLong + "_playerdat.txt")
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                Messages.throwError(e, message)
            }
        }
        try {
            FileWriter(s + "/config/mafia/" + message.guild.idLong + "_playerdat.txt").use { fileWriter ->
                fileWriter.write(`object`.toString())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun getJailed(message: Message): Long {
        val root = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_dat.txt")
        return root.getLong("jailed")
    }

    fun writeGameDat(message: Message, `object`: JSONObject) {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + "/config/mafia/" + message.guild.idLong + "_dat.txt")
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                Messages.throwError(e, message)
            }
        }
        try {
            FileWriter(s + "/config/mafia/" + message.guild.idLong + "_dat.txt").use { fileWriter ->
                fileWriter.write(`object`.toString())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun writeActions(message: Message, json: JSONObject) {
        val file = File(s + "/config/mafia/" + message.guild.idLong + "_actions.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        FileWriter(file).use { fileWriter ->
            fileWriter.write(json.toString())
        }
    }

    fun investResults(message: Message): JSONObject {
        val json = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_actions.txt")
        return json.getJSONObject("invest_results")
    }
    fun checkRevealed(message : Message) : Boolean{
        val perms = Perms(message.guild)
        if (perms.checkMod(message)) {
            val root1 = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
            return root1.getBoolean("extra")
        }
        return false
    }
    fun setExtra(message : Message, value : Any){
        val f = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
        val user = f.getJSONObject("" + message.author.idLong)
        f.remove("" + message.author.idLong)
        user.remove("extra")
        user.put("extra", value)
        f.put("" + message.author.idLong, user)
        writeGame(message, f)
    }
    fun setExtra(message : Message, user : Long, value : Any){
        val f = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
        val user2 = f.getJSONObject("" + user)
        f.remove("" + user)
        user2.remove("extra")
        user2.put("extra", value)
        f.put("" + user, user2)
        writeGame(message, f)
    }
    fun getExtra(message : Message) : Any{
        val details = getPlayerDetails(message)
        return details[6]
    }
    fun getExtra(message : Message, user : Long) : Any{
        val details = getPlayerDetails(message, user)
        return details[6]
    }

    fun editDetails(message : Message, user : Member, key : Enum<Details>, value : Any){
        val json = Utils.readJSONFromFile("/config/mafia/" + message.guild.idLong + "_playerdat.txt")
        val userJson = json.getJSONObject("" + user.user.idLong)
        json.remove("" + user.user.idLong)
        userJson.remove(key.name)
        userJson.put(key.name, value)
        json.put("" + user.user.idLong, userJson)
        writeGame(message, json)
    }

    fun getAdminArray(fileName: String): List<Long> {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + fileName)
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Messages.throwError(e)
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        val array = root.getJSONArray("Admins")
        val longArray = mutableListOf<Long>()
        var i = 0
        while (i < array.length()) {
            longArray.add(array.getLong(i))
            i++
        }
        return longArray
    }

    fun getModArray(fileName: String): List<Long> {
        val currentRelativePath = Paths.get("")
        val s = currentRelativePath.toAbsolutePath().toString()
        val file = File(s + fileName)
        val uri = file.toURI()
        var tokener: JSONTokener? = null
        try {
            tokener = JSONTokener(uri.toURL().openStream())
        } catch (e: IOException) {
            Messages.throwError(e)
            e.printStackTrace()
        }

        val root = JSONObject(tokener!!)
        val array = root.getJSONArray("Mods")
        val longArray = mutableListOf<Long>()
        var i = 0
        while (i < array.length()) {
            longArray.add(array.getLong(i))
            i++
        }
        return longArray
    }

}