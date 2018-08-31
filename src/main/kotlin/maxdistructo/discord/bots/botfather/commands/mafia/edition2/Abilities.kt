package maxdistructo.discord.bots.botfather.commands.mafia.edition2


enum class Abilities : IAbility {
    INVEST_GENERAL{
        override val actionName: String
            get() = "investigate"
    },
    WATCHER{
        override val actionName: String
            get() = "lookout"
    },
    INTERROGATE{
        override val actionName: String
            get() = "interrogate"
    },
    SPY{
        override val actionName: String
            get() = "spy"
    },
    ROLEBLOCKER{
        override val actionName: String
            get() = "roleblock"
    },
    EXTRA_VOTES{
        override val actionName: String
            get() = "morevotes"
    },
    DEAD_COMMS{
        override val actionName: String
            get() = "seance"
    },
    REVIVAL{
        override val actionName: String
            get() = "revive"
    },
    TRANSPORTER{
        override val actionName: String
            get() = "transport"
    },
    PROTECTOR{
        override val actionName: String
            get() = "protect"
    },
    HEALER{
        override val actionName: String
            get() = "heal"
    },
    VAMP_KILLING{
        override val actionName: String
            get() = "vamp_killer"
    },
    RAMPAGE{
        override val actionName: String
            get() = "rampage"
    },
    KILL{
        override val actionName: String
            get() = "kill"
    },
    DISGUISE{
        override val actionName: String
            get() = "disguise"
    },
    FRAME{
        override val actionName: String
            get() = "frame"
    },
    FAKE_RESULT{
        override val actionName: String
            get() = "forge"
    },
    REMOVE_ROLE{
        override val actionName: String
            get() = "clean"
    },
    REMOVE_CHAT{
        override val actionName: String
            get() = "blackmail"
    },
    INVEST_ABSOLUTE{
        override val actionName: String
            get() = "invest_absolute"
    },
    KILL_LIST{
        override val actionName: String
            get() = "kill"
    },
    NONE{
        override val actionName: String
            get() = "none"
    },
    CONTROL{
        override val actionName: String
            get() = "control"
    },
    REMEMBER{
        override val actionName: String
            get() = "remember"
    },
    PROTECT{
        override val actionName: String
            get() = "self_protect"
    },
    CONVERT{
        override val actionName: String
            get() = "convert"
    },
    JAIL{
        override val actionName: String
            get() = "jail"
    }
}