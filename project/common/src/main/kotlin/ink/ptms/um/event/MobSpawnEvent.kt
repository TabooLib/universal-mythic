package ink.ptms.um.event

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import taboolib.platform.type.BukkitProxyEvent

class MobSpawnEvent(val mob: Mob?, val mobType: MobType, var level: Double) : BukkitProxyEvent() {

    fun fire(): MobSpawnEvent {
        call()
        return this
    }
}