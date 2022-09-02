package ink.ptms.um

import org.bukkit.inventory.ItemStack
import org.yaml.snakeyaml.Yaml
import taboolib.library.configuration.ConfigurationSection
import taboolib.module.configuration.Configuration

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
    val displayName: String?

    /**
     * 物品的配置文件
     */
    val config: ConfigurationSection

    /**
     * 生成物品
     */
    fun generateItemStack(amount: Int): ItemStack
}