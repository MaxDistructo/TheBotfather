package maxdistructo.discord.bots.botfather.commands.mafia.edition2

import maxdistructo.discord.bots.botfather.commands.mafia.methods.RoleCards
import net.dv8tion.jda.core.entities.MessageEmbed

enum class Roles(name : String) : IRole {
    INVEST("investigator") {
        override val ability: IAbility
            get() = Abilities.INVEST_GENERAL
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Investigative"
        override val shortClass: String
            get() = "TI"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)
    },
    LOOKOUT("lookout"){
        override val ability: IAbility
            get() = Abilities.WATCHER
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Investigative"
        override val shortClass: String
            get() = "TI"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    SHERIFF("sheriff"){
        override val ability: IAbility
            get() = Abilities.INTERROGATE
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Investigative"
        override val shortClass: String
            get() = "TI"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    SPY("spy"){
        override val ability: IAbility
            get() = Abilities.SPY
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Investigative"
        override val shortClass: String
            get() = "TI"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    ESCORT("escort"){
        override val ability: IAbility
            get() = Abilities.ROLEBLOCKER
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Support"
        override val shortClass: String
            get() = "TS"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    MAYOR("mayor"){
        override val ability: IAbility
            get() = Abilities.EXTRA_VOTES
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Support"
        override val shortClass: String
            get() = "TS"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    MEDIUM("medium"){
        override val ability: IAbility
            get() = Abilities.INVEST_GENERAL
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Support"
        override val shortClass: String
            get() = "TS"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    RETRI("retributionist"){
        override val ability: IAbility
            get() = Abilities.REVIVAL
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Support"
        override val shortClass: String
            get() = "TS"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    }, //Short for Retributionist
    TRANS("transporter"){
        override val ability: IAbility
            get() = Abilities.TRANSPORTER
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Support"
        override val shortClass: String
            get() = "TS"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    }, //Short for Transporter
    BODYGUARD("bodyguard"){
        override val ability: IAbility
            get() = Abilities.PROTECTOR
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Protective"
        override val shortClass: String
            get() = "TP"
        override val attack: Int
            get() = 2
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    DOCTOR("doctor"){
        override val ability: IAbility
            get() = Abilities.HEALER
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Protective"
        override val shortClass: String
            get() = "TP"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    VAMP_HUNTER("vampire_hunter"){
        override val ability: IAbility
            get() = Abilities.VAMP_KILLING
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Killing"
        override val shortClass: String
            get() = "TK"
        override val attack: Int
            get() = 1
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)
    },
    VETERAN("veteran"){
        override val ability: IAbility
            get() = Abilities.RAMPAGE
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Killing"
        override val shortClass: String
            get() = "TK"
        override val attack: Int
            get() = 2
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    VIGILANTE("vigilante"){
        override val ability: IAbility
            get() = Abilities.KILL
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Killing"
        override val shortClass: String
            get() = "TK"
        override val attack: Int
            get() = 1
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    JAILOR("jailor"){
        override val ability: IAbility
            get() = Abilities.KILL
        override val alignment: String
            get() = "Town"
        override val classification: String
            get() = "Town Killing"
        override val shortClass: String
            get() = "TK"
        override val attack: Int
            get() = 1
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    DISGUISER("disguiser"){
        override val ability: IAbility
            get() = Abilities.DISGUISE
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Deception"
        override val shortClass: String
            get() = "MD"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    FRAMER("framer"){
        override val ability: IAbility
            get() = Abilities.FRAME
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Deception"
        override val shortClass: String
            get() = "MD"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    FORGER("forger"){
        override val ability: IAbility
            get() = Abilities.FAKE_RESULT
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Deception"
        override val shortClass: String
            get() = "MD"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    JANITOR("janitor"){
        override val ability: IAbility
            get() = Abilities.REMOVE_ROLE
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Deception"
        override val shortClass: String
            get() = "MD"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    BLACKMAIL("blackmailer"){
        override val ability: IAbility
            get() = Abilities.REMOVE_CHAT
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Deception"
        override val shortClass: String
            get() = "MD"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    CONSIG("consigliere"){
        override val ability: IAbility
            get() = Abilities.INVEST_ABSOLUTE
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Support"
        override val shortClass: String
            get() = "MS"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    }, //Short for Consigliere
    CONSORT("consort"){
        override val ability: IAbility
            get() = Abilities.ROLEBLOCKER
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Support"
        override val shortClass: String
            get() = "MS"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    GODFATHER("godfather"){
        override val ability: IAbility
            get() = Abilities.KILL
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Killing"
        override val shortClass: String
            get() = "MK"
        override val attack: Int
            get() = 1
        override val defence: Int
            get() = 1
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    MAFIOSO("mafioso"){
        override val ability: IAbility
            get() = Abilities.KILL
        override val alignment: String
            get() = "Mafia"
        override val classification: String
            get() = "Mafia Killing"
        override val shortClass: String
            get() = "MK"
        override val attack: Int
            get() = 1
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    SERIAL_KILLER("serial_killer"){
        override val ability: IAbility
            get() = Abilities.KILL
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Killing"
        override val shortClass: String
            get() = "NK"
        override val attack: Int
            get() = 1
        override val defence: Int
            get() = 1
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    }, //Short for Serial Killer
    WEREWOLF("werewolf"){
        override val ability: IAbility
            get() = Abilities.RAMPAGE
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Killing"
        override val shortClass: String
            get() = "NK"
        override val attack: Int
            get() = 2
        override val defence: Int
            get() = 1
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    ARSONIST("arsonist"){
        override val ability: IAbility
            get() = Abilities.KILL_LIST
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Killing"
        override val shortClass: String
            get() = "NK"
        override val attack: Int
            get() = 3
        override val defence: Int
            get() = 1
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(name)

    },
    EXECUTIONER("executioner"){
        override val ability: IAbility
            get() = Abilities.NONE
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Evil"
        override val shortClass: String
            get() = "NE"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 1
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    JESTER("jester"){
        override val ability: IAbility
            get() = Abilities.KILL
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Evil"
        override val shortClass: String
            get() = "NE"
        override val attack: Int
            get() = 3
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    WITCH("witch"){
        override val ability: IAbility
            get() = Abilities.CONTROL
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Evil"
        override val shortClass: String
            get() = "NE"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)
    },
    AMNESIAC("amnesiac"){
        override val ability: IAbility
            get() = Abilities.REMEMBER
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Benign"
        override val shortClass: String
            get() = "NB"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    SURVIVOR("survivor"){
        override val ability: IAbility
            get() = Abilities.PROTECT
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Benign"
        override val shortClass: String
            get() = "NB"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    VAMPIRE("vampire"){
        override val ability: IAbility
            get() = Abilities.CONVERT
        override val alignment: String
            get() = "Neutral"
        override val classification: String
            get() = "Neutral Chaos"
        override val shortClass: String
            get() = "NC"
        override val attack: Int
            get() = 1
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)

    },
    DEFAULT("default"){
        override val ability: IAbility
            get() = Abilities.NONE
        override val alignment: String
            get() = "None"
        override val classification: String
            get() = "None None"
        override val shortClass: String
            get() = "NN"
        override val attack: Int
            get() = 0
        override val defence: Int
            get() = 0
        override val rolecard : MessageEmbed
            get() = RoleCards.onRoleCardAsk(this.name)
    }
}
