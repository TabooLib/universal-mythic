package ink.ptms.um

import org.bukkit.Location
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.Skill
 *
 * @author 坏黑
 * @since 2022/7/12 13:34
 */
interface Skill {

    /** 延迟 */
    val delay: Int

    /**
     * 释放技能
     * @param trigger 技能触发器
     * @param entity 技能施法者
     * @param target 技能目标
     * @param et 技能目标实体
     * @param lt 技能目标位置
     * @param power 技能强度
     * @param parameters 技能参数
     * @param targetFilter 技能目标过滤器
     */
    fun execute(
        trigger: Trigger,
        entity: Entity,
        target: Entity,
        et: Set<Entity> = emptySet(),
        lt: Set<Location> = emptySet(),
        power: Float = 0f,
        parameters: Map<String, Any> = emptyMap(),
        targetFilter: (Entity) -> Boolean = { true },
    ): Boolean

    /** 技能触发器 */
    interface Trigger {

        /** 名称 */
        val name: String
    }

    /** 技能施法者 */
    interface ActiveCaster {

        /** 参数 */
        val parameters: Map<String, Any>
    }
}