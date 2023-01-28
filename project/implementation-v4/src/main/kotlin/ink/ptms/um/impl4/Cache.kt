package ink.ptms.um.impl4

import taboolib.common.platform.Schedule
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object Cache {

    val mob = ConcurrentHashMap<UUID, Mob4>()

    val mobType = ConcurrentHashMap<String, MobType4>()

    val mobConfiguration = ConcurrentHashMap<String, Mob4Configuration>()

    val item = ConcurrentHashMap<String, Item4>()

    val itemConfiguration = ConcurrentHashMap<String, Mob4Configuration>()

    @Schedule(async = true, period = 12000)
    fun autoClear() {
        mob.toMap().forEach { (t, u) ->
            if (u.source.isDead) {
                mob.remove(t)
            }
        }
    }
}