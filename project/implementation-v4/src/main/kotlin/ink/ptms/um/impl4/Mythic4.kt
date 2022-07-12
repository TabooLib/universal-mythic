package ink.ptms.um.impl4

import ink.ptms.um.*
import io.lumine.xikage.mythicmobs.MythicMobs
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

/**
 * universal-mythic
 * ink.ptms.um.impl4.Mythic4
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
class Mythic4 : Mythic {

    override val isLegacy = true

    override fun getItem(name: String): Item? {
        TODO("Not yet implemented")
    }

    override fun getItemStack(name: String): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun getMob(entity: Entity): Mob? {
        return Mob4(MythicMobs.inst().mobManager.getMythicMobInstance(entity) ?: return null)
    }

    override fun getMobType(name: String): MobType? {
        TODO("Not yet implemented")
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        TODO("Not yet implemented")
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        TODO("Not yet implemented")
    }

    companion object {

        @Awake(LifeCycle.LOAD)
        fun setup() {
            if (kotlin.runCatching { Class.forName("io.lumine.xikage.mythicmobs.MythicMobs") }.getOrNull() != null) {
                Mythic.API = Mythic4()
            }
        }
    }
}