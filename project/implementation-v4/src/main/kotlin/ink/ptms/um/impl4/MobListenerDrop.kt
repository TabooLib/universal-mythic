package ink.ptms.um.impl4

import ink.ptms.um.event.MobDropLoadEvent
import ink.ptms.um.item.DropMeta
import ink.ptms.um.skill.SkillCaster
import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicDropLoadEvent
import io.lumine.xikage.mythicmobs.drops.Drop
import io.lumine.xikage.mythicmobs.drops.DropMetadata
import io.lumine.xikage.mythicmobs.drops.IItemDrop
import org.bukkit.entity.Entity
import taboolib.common.platform.Ghost
import taboolib.common.platform.event.SubscribeEvent

internal object MobListenerDrop {

    @Ghost
    @SubscribeEvent
    fun onDropLoadEvent(event: MythicDropLoadEvent) {
        val e = MobDropLoadEvent(event.dropName).fire()
        e.itemDrops.forEach { dropFunc ->
            event.register(object : Drop(event.dropName, event.config), IItemDrop {

                override fun getDrop(dropMeta: DropMetadata): AbstractItemStack {
                    return BukkitAdapter.adapt(dropFunc.apply(object : DropMeta {

                        override val dropper: SkillCaster?
                            get() = dropMeta.caster?.toUniversal()

                        override val cause: Entity?
                            get() = dropMeta.trigger?.bukkitEntity

                        override var amount: Float
                            get() = dropMeta.amount
                            set(value) {
                                dropMeta.amount = value
                            }

                        override var generations: Int
                            get() = dropMeta.generations
                            set(value) {
                                dropMeta.generations = value
                            }

                        override fun tick() {
                            dropMeta.tick()
                        }
                    }))
                }
            })
        }
    }
}