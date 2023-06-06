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

internal object MobListener4 {

    @Ghost
    @SubscribeEvent
    fun onMobDeathEvent(event: MythicMobDeathEvent) {
        // 唤起事件，并更新掉落物
        val activeMob = runCatching {
            event.mob
        }.getOrElse {
            MythicMobs.inst().mobManager.getMythicMobInstance(event.entity) ?: return
        }
        event.drops = MobDeathEvent(Mob4(activeMob), event.killer, event.drops).fire().drop
    }

    @Ghost
    @SubscribeEvent
    fun onMobSpawnEvent(event: MythicMobSpawnEvent) {
        // 在部分 MythicMobs 版本无法在 MythicMobSpawnEvent 中获取 mob 参数
        val activeMob = runCatching {
            event.mob
        }.getOrElse {
            MythicMobs.inst().mobManager.getMythicMobInstance(event.entity) ?: return
        }
        val mob4 = Mob4(activeMob)
        // 唤起事件
        val e = MobSpawnEvent(mob4, mob4.type, runCatching { event.mobLevel }.getOrElse { 0.0 }).fire()
        // 更新等级
        runCatching { event.mobLevel = e.level }
    }

    @Ghost
    @SubscribeEvent
    fun onMythicReloadEvent(event: MythicReloadedEvent) {
        MythicReloadEvent().fire()
    }
}