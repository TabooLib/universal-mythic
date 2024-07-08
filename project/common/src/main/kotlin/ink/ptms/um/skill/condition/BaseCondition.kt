package ink.ptms.um.skill.condition

/** project name universal-mythic
 *  package ink.ptms.um.skill.condition
 *  time 2024/7/6
 *  author 香香软软的枫溪宝贝
 */
interface BaseCondition {
    companion object {
        @JvmStatic
        fun isSubclass(subclass: BaseCondition): Boolean {
            return when (subclass) {
                is CasterCondition -> true
                is EntityCondition -> true
                is LocationCondition -> true
                is SkillMetadataCondition -> true
                is EntityComparisonCondition -> true
                is SkillMetaComparisonCondition->true
                is EntityLocationDistanceCondition -> true
                else -> false
            }
        }
    }
}