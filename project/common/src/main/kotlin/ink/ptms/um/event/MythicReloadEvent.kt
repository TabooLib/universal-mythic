package ink.ptms.um.event

import taboolib.platform.type.BukkitProxyEvent

class MythicReloadEvent : BukkitProxyEvent() {

    fun fire(): MythicReloadEvent {
        call()
        return this
    }
}