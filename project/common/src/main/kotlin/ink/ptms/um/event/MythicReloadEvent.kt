package ink.ptms.um.event

import ink.ptms.um.Mythic
import taboolib.platform.type.BukkitProxyEvent

class MythicReloadEvent(val mythic: Mythic) : BukkitProxyEvent() {

    fun fire(): MythicReloadEvent {
        call()
        return this
    }
}