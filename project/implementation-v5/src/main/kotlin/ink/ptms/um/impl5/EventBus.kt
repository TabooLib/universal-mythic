package ink.ptms.um.impl5

import ink.ptms.um.Mythic
import ink.ptms.um.event.MobDeathEvent
import ink.ptms.um.event.MobSpawnEvent
import ink.ptms.um.event.MythicReloadEvent
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent
import io.lumine.mythic.bukkit.events.MythicReloadedEvent
import taboolib.common.platform.event.SubscribeEvent

object EventBus {

    @SubscribeEvent
    fun onMobDeathEvent(event: MythicMobDeathEvent) {
        val bus = MobDeathEvent(Mob5(event.mob), event.killer, event.drops).fire()
        event.drops = bus.drop
    }

    @SubscribeEvent
    fun onMobSpawnEvent(event: MythicMobSpawnEvent) {
        val bus = MobSpawnEvent(Mob5(event.mob), event.mobLevel).fire()
        event.mobLevel = bus.level
    }

    @SubscribeEvent
    fun onMythicReloadEvent(event: MythicReloadedEvent) {
        MythicReloadEvent(Mythic.API).fire()
    }

}