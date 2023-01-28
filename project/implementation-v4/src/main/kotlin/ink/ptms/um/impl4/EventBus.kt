package ink.ptms.um.impl4

import ink.ptms.um.event.MobDeathEvent
import ink.ptms.um.event.MobSpawnEvent
import ink.ptms.um.event.MythicReloadEvent
import io.lumine.xikage.mythicmobs.MythicMobs
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicReloadedEvent
import taboolib.common.platform.Ghost
import taboolib.common.platform.event.SubscribeEvent

object EventBus {

    @Ghost
    @SubscribeEvent
    fun onMobDeathEvent(event: MythicMobDeathEvent) {
        val bus = MobDeathEvent(
            Cache.mob.getOrPut(event.mob.uniqueId) { Mob4(event.mob) },
            event.killer,
            event.drops
        ).fire()
        event.drops = bus.drop
    }

    @Ghost
    @SubscribeEvent
    fun onMobSpawnEvent(event: MythicMobSpawnEvent) {
        // 在较低的 MythicMobs 版本下无法在 MythicMobSpawnEvent 中获取 mob 参数
        val mob = kotlin.runCatching { Cache.mob.getOrPut(event.mob.uniqueId) { Mob4(event.mob) } }
            .getOrElse {
                MythicMobs.inst().mobManager.getMythicMobInstance(event.entity)?.let {
                    Cache.mob.getOrPut(event.mob.uniqueId) { Mob4(it) }
                }
            }
        val level = kotlin.runCatching { event.mobLevel }.getOrElse { 0.0 }
        val bus = MobSpawnEvent(
                mob,
                Cache.mobType.getOrPut(event.mobType.internalName) { MobType4(event.mobType) },
                level
            ).fire()
        kotlin.runCatching { event.mobLevel = bus.level }
    }

    @Ghost
    @SubscribeEvent
    fun onMythicReloadEvent(event: MythicReloadedEvent) {
        Cache.mob.clear()
        Cache.mobConfiguration.clear()
        Cache.mobType.clear()
        Cache.item.clear()
        Cache.itemConfiguration.clear()
        MythicReloadEvent().fire()
    }
}