package ink.ptms.um.impl5

import taboolib.common.platform.Schedule
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object Cache {

    val mob = ConcurrentHashMap<UUID, Mob5>()

    val mobType = ConcurrentHashMap<String, MobType5>()

    val mobConfiguration = ConcurrentHashMap<String, Mob5Configuration>()

    val item = ConcurrentHashMap<String, Item5>()

    val itemConfiguration = ConcurrentHashMap<String, Mob5Configuration>()

    @Schedule(async = true, period = 12000)
    fun autoClear() {
        mob.toMap().forEach { (t, u) ->
            if (u.source.isDead) {
                mob.remove(t)
            }
        }
    }
}