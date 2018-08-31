package maxdistructo.discord.bots.botfather.commands.mafia.init

import maxdistructo.discord.bots.botfather.commands.mafia.edition2.IAbility

interface IRole {
    val name : String
    val classification : String
    val shortClass : String
    val alignment : String
    val summary : String
    val description : String
    val thumbnail : String
    val icon : String
    val attack : Int
    val defence : Int
    val ability : IAbility
}