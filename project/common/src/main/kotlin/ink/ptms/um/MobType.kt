package ink.ptms.um

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import taboolib.library.configuration.ConfigurationSection

/**
 * universal-mythic
 * ink.ptms.um.MobType
 *
 * @author 坏黑
 * @since 2022/7/12 13:40
 */
interface MobType {

    /**
     * 怪物序号，对应 internalName 字段
     */
    val id: String

    /**
     * 展示名称
     */
    val displayName: String

    /**
     * 实体类型
     */
    val entityType: String

    /**
     * 配置
     */
    val config: ConfigurationSection

    /**
     * 生成到指定位置
     */
    fun spawn(location: Location, level: Double): Mob
}