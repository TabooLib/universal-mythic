package ink.ptms.um.event

import ink.ptms.um.item.DropMeta
import org.bukkit.inventory.ItemStack
import taboolib.platform.type.BukkitProxyEvent
import java.util.function.Function

/**
 * universal-mythic
 * ink.ptms.um.event.MobDropLoadEvent
 *
 * @author 坏黑
 * @since 2023/6/11 22:17
 */
class MobDropLoadEvent(val dropName: String): BukkitProxyEvent() {

    val itemDrops = arrayListOf<Function<DropMeta, ItemStack>>()

    fun registerItem(func: Function<DropMeta, ItemStack>) {
        itemDrops += func
    }

    fun fire(): MobDropLoadEvent {
        call()
        return this
    }
}