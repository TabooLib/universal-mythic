package ink.ptms.um.item

import ink.ptms.um.skill.SkillCaster
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.item.DropMeta
 *
 * @author 坏黑
 * @since 2023/6/11 23:40
 */
interface DropMeta {

    /** 掉落者 */
    val dropper: SkillCaster?

    /** 触发者 */
    val cause: Entity?

    var amount: Float

    var generations: Int

    fun tick()
}