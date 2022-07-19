package ink.ptms.um.impl4

import ink.ptms.um.Mythic
import ink.ptms.um.event.MobDeathEvent
import ink.ptms.um.event.MobSpawnEvent
import ink.ptms.um.event.MythicReloadEvent
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicReloadedEvent
import taboolib.common.platform.event.SubscribeEvent

object EventBus {

    @SubscribeEvent
    fun onMobDeathEvent(event: MythicMobDeathEvent) {
        val bus = MobDeathEvent(Mob4(event.mob), event.killer, event.drops).fire()
        event.drops = bus.drop
    }

    @SubscribeEvent
    fun onMobSpawnEvent(event: MythicMobSpawnEvent) {
        val bus = MobSpawnEvent(Mob4(event.mob), event.mobLevel).fire()
        event.mobLevel = bus.level
    }

    @SubscribeEvent
    fun onMythicReloadEvent(event: MythicReloadedEvent) {
        MythicReloadEvent(Mythic.API).fire()
    }

}