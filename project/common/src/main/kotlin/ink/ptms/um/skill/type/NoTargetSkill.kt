package ink.ptms.um.skill.type

import ink.ptms.um.skill.SkillMeta
import ink.ptms.um.skill.SkillResult

/**
 * universal-mythic
 * ink.ptms.um.skill.NoTargetSkill
 *
 * @author 坏黑
 * @since 2023/6/6 16:05
 */
interface NoTargetSkill : BaseSkill {

    /** 施法 */
    fun cast(meta: SkillMeta): SkillResult
}