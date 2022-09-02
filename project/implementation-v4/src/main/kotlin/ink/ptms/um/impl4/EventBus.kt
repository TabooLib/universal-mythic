package ink.ptms.um.impl4

import ink.ptms.um.Mythic
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
        val bus = MobDeathEvent(Mob4(event.mob), event.killer, event.drops).fire()
        event.drops = bus.drop
    }

    @Ghost
    @SubscribeEvent
    fun onMobSpawnEvent(event: MythicMobSpawnEvent) {
        val bus = try {
            MobSpawnEvent(Mob4(event.mob), MobType4(event.mobType), event.mobLevel).fire()
        } catch (ex: NoSuchMethodError) {
            // 在较低的 MythicMobs 版本下无法在 MythicMobSpawnEvent 中获取 mob 参数
            val mob = MythicMobs.inst().mobManager.getMythicMobInstance(event.entity)
            MobSpawnEvent(if (mob != null) Mob4(mob) else null, MobType4(event.mobType), event.mobLevel).fire()
        }
        event.mobLevel = bus.level
    }

    @Ghost
    @SubscribeEvent
    fun onMythicReloadEvent(event: MythicReloadedEvent) {
        MythicReloadEvent().fire()
    }
}