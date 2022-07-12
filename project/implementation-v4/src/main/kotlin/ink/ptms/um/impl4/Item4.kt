package ink.ptms.um.impl4

import ink.ptms.um.Item
import io.lumine.xikage.mythicmobs.items.MythicItem
import org.bukkit.inventory.ItemStack

class Item4(obj: Any) : Item {

    val source = obj as MythicItem

    override fun generateItemStack(amount: Int): ItemStack {
        return source.generateItemStack(amount).toBukkit()
    }

}