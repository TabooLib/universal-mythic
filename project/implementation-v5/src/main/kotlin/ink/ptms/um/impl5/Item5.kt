package ink.ptms.um.impl5

import ink.ptms.um.Item
import io.lumine.mythic.core.items.MythicItem
import org.bukkit.inventory.ItemStack

class Item5(obj: Any) : Item {

    val source = obj as MythicItem

    override fun generateItemStack(amount: Int): ItemStack {
        return source.generateItemStack(amount).toBukkit()
    }

}