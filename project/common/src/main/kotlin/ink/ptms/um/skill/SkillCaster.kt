package ink.ptms.um.skill

import org.bukkit.Location
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.skill.SkillCaster
 *
 * @author 坏黑
 * @since 2023/6/6 15:28
 */
interface SkillCaster {

    /** 技能施法者 */
    val entity: Entity

    /** 技能施法者位置 */
    val location: Location

    /** 等级 */
    val level: Double

    /** 强度 */
    val power: Float

    /** 全局冷却 */
    var globalCooldown: Int
}