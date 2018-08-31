package maxdistructo.discord.bots.botfather.commands.mafia.obj

import maxdistructo.discord.bots.botfather.commands.mafia.init.IDeath

enum class Deaths(role : Enum<Roles>) : IDeath {

    MAFIA(Roles.MAFIOSO){
        override val deathType: String
            get() = "was shot by a Member of the Mafia."
    },
    MAFIA2(Roles.GODFATHER){
        override val deathType: String
            get() = " was shot by a Member of the Mafia."
    },
    BODYGUARD(Roles.BODYGUARD){
        override val deathType: String
            get() = " was killed by a Bodyguard."
    },
    VAMP_HUNTER(Roles.VAMP_HUNTER){
        override val deathType: String
            get() = " was staked by a Vampire Hunter."
    },
    VETERAN(Roles.VETERAN){
        override val deathType: String
            get() = " was killed by a Veteran."
    },
    VIGILANTE(Roles.VIGILANTE){
        override val deathType: String
            get() = " was shot by a Vigilante."
    },
    SK(Roles.SERIAL_KILLER){
        override val deathType: String
            get() = " was stabbed by a Serial Killer"
    },
    WW(Roles.WEREWOLF){
        override val deathType: String
            get() = " was mauled by a Werewolf"
    },
    ARSO(Roles.ARSONIST){
        override val deathType: String
            get() = " was burnt to a crisp by an Arsonist"
    },
    JEST(Roles.JESTER){
        override val deathType: String
            get() = " was haunted by the Jester they lynched."
    },
    SELF(Roles.SELF){
        override val deathType: String
            get() = " has committed Suicide."
    },
    GUILT(Roles.GUILT){
        override val deathType: String
            get() = " shot themselves out of Guilt."
    },
    VAMPIRE(Roles.VAMPIRE){
        override val deathType: String
            get() = " was bitted by a Vampire."
    }


}