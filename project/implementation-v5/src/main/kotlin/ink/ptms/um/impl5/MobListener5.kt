package ink.ptms.um.impl5

import ink.ptms.um.event.MobDeathEvent
import ink.ptms.um.event.MobSpawnEvent
import ink.ptms.um.event.MythicReloadEvent
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent
import io.lumine.mythic.bukkit.events.MythicMobSpawnEvent
import io.lumine.mythic.bukkit.events.MythicReloadedEvent
import taboolib.common.platform.Ghost
import taboolib.common.platform.event.SubscribeEvent

internal object MobListener5 {

    @Ghost
    @SubscribeEvent
    fun onMobDeathEvent(event: MythicMobDeathEvent) {
        event.drops = MobDeathEvent(Mob5(event.mob), event.killer, event.drops).fire().drop
    }

    @Ghost
    @SubscribeEvent
    fun onMobSpawnEvent(event: MythicMobSpawnEvent) {
        event.mobLevel = MobSpawnEvent(Mob5(event.mob), MobType5(event.mob.type), event.mobLevel).fire().level
    }

    @Ghost
    @SubscribeEvent
    fun onMythicReloadEvent(event: MythicReloadedEvent) {
        MythicReloadEvent().fire()
    }
}