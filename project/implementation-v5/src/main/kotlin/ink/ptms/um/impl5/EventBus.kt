package ink.ptms.um.impl5

import ink.ptms.um.event.MobDeathEvent
import ink.ptms.um.event.MobSpawnEvent
import ink.ptms.um.event.MythicReloadEvent
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent
import io.lumine.mythic.bukkit.events.MythicReloadedEvent
import taboolib.common.platform.Ghost
import taboolib.common.platform.event.SubscribeEvent

object EventBus {

    @Ghost
    @SubscribeEvent
    fun onMobDeathEvent(event: MythicMobDeathEvent) {
        val bus = MobDeathEvent(
            Cache.mob.getOrPut(event.mob.uniqueId) { Mob5(event.mob) },
            event.killer,
            event.drops
        ).fire()
        event.drops = bus.drop
    }

    @Ghost
    @SubscribeEvent
    fun onMobSpawnEvent(event: MythicMobSpawnEvent) {
        val bus = MobSpawnEvent(
            Cache.mob.getOrPut(event.mob.uniqueId) { Mob5(event.mob) },
            Cache.mobType.getOrPut(event.mobType.internalName) { MobType5(event.mobType) },
            event.mobLevel
        ).fire()
        event.mobLevel = bus.level
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