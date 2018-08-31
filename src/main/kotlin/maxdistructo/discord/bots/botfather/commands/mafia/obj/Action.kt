package maxdistructo.discord.bots.botfather.commands.mafia.obj

import maxdistructo.discord.bots.botfather.commands.mafia.init.IAction
import org.json.JSONObject

open class Action : IAction {
    private var privTarget: Long
        get() = target
    private var privTarget2: Long
        get() = target2

    private var privAction: String = ""
        get() = action

    private var privPlayer: Long = 0
        get() = player

    private var privExtra: Any
        get() = extra

    override val name: String
        get() = "Base"

    override val target: Long
        get() = privTarget

    override val target2: Long
        get() = privTarget2

    override val action: String
        get() = privAction

    override val player: Long
        get() = privPlayer

    override val extra : Any
        get() = privExtra


    override fun toJSON(): JSONObject {
        val obj = JSONObject()
        obj.put("target", privTarget)
        obj.put("target2", privTarget2)
        obj.put("action", privAction)
        obj.put("extra", privExtra)
        return obj
    }

    constructor(extra: Any) {
        privTarget = 0
        privTarget2 = 0
        privExtra = extra
    }

    constructor(player: Long, target: Long, target2: Long, action: String, extra : Any) {
        privTarget = target
        privTarget2 = target2
        privAction = action
        privPlayer = player
        privExtra = extra
    }

    constructor(player: Long, root: JSONObject) {
        privTarget = root.getLong("target")
        privTarget2 = root.getLong("target2")
        privAction = root.getString("action")
        privPlayer = player
        privExtra = root.get("extra")
    }

}