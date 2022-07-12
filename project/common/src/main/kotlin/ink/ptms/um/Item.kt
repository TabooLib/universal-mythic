package ink.ptms.um

import org.bukkit.inventory.ItemStack

/**
 * universal-mythic
 * ink.ptms.um.Item
 *
 * @author 坏黑
 * @since 2022/7/12 13:40
 */
interface Item {

    /**
     * 生成物品
     */
    fun generateItemStack(amount: Int): ItemStack
}