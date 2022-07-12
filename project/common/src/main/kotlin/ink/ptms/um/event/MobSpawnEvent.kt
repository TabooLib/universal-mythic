package ink.ptms.um.event

import ink.ptms.um.Mob
import taboolib.platform.type.BukkitProxyEvent

class MobSpawnEvent(
    val mob: Mob,
    var level: Double
) : BukkitProxyEvent()