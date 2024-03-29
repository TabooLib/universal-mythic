package ink.ptms.um

import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import taboolib.library.configuration.ConfigurationSection

/**
 * universal-mythic
 * ink.ptms.um.Mob
 *
 * @author 坏黑
 * @since 2022/7/12 13:34
 */
interface Mob {

    /**
     * 怪物序号，对应 internalName 字段
     */
    val id: String

    /**
     * 展示名称
     */
    val displayName: String

    /**
     * 获取 MythicMob 类型
     */
    val type: MobType

    /**
     * 获取 Bukkit 实体对象
     */
    val entity: Entity

    /**
     * 实体类型
     */
    val entityType: EntityType

    /**
     * 等级
     */
    val level: Double

    /**
     * 姿态
     */
    val stance: String

    /**
     * 阵营
     */
    val faction: String

    /**
     * 配置
     */
    val config: ConfigurationSection

    /**
     * 增加仇恨
     */
    fun addThreat(mob: Entity, target: LivingEntity, amount: Double)

    /**
     * 减少仇恨
     */
    fun reduceThreat(mob: Entity, target: LivingEntity, amount: Double)
}
