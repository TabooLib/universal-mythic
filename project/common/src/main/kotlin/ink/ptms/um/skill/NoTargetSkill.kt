package ink.ptms.um.skill

/**
 * universal-mythic
 * ink.ptms.um.skill.NoTargetSkill
 *
 * @author 坏黑
 * @since 2023/6/6 16:05
 */
interface NoTargetSkill {

    /** 施法 */
    fun cast(meta: SkillMeta): Boolean
}