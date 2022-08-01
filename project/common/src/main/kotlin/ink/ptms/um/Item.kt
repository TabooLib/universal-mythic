package ink.ptms.um

import org.bukkit.configuration.ConfigurationSection
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
     * 物品ID
     */
    val internalName: String

    /**
     * 物品数量
     */
    val amount: Int

    /**
     * 物品名
     */
    val displayName: String

    /**
     * 生成物品
     */
    fun generateItemStack(amount: Int): ItemStack

    /**
     * 物品的配置文件
     */
    fun getConfig(): ConfigurationSection

}