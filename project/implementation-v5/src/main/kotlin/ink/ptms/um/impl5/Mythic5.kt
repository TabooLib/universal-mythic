package ink.ptms.um.impl5

import ink.ptms.um.*
import io.lumine.mythic.api.MythicPlugin
import io.lumine.mythic.api.MythicProvider
import io.lumine.mythic.api.skills.SkillTrigger
import io.lumine.mythic.core.config.MythicLineConfigImpl
import io.lumine.mythic.core.mobs.MobExecutor
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.reflect.Reflex.Companion.invokeMethod
import taboolib.module.nms.getItemTag

/**
 * universal-mythic
 * ink.ptms.um.impl4.Mythic4
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
class Mythic5 : Mythic {

    val api: MythicPlugin
        get() = MythicProvider.get()

    override val isLegacy = false

    override fun getItem(name: String): Item? {
        return Item5(api.itemManager.getItem(name)?.get() ?: return null)
    }

    override fun getItemId(itemStack: ItemStack): String? {
        return itemStack.getItemTag()["MYTHIC_TYPE"]?.asString()
    }

    override fun getItemStack(name: String): ItemStack? {
        return api.itemManager?.getItem(name)?.get()?.generateItemStack(1)?.toBukkit()
    }

    override fun getItemIDList(): List<String> {
        return api.itemManager.items.map { it.internalName }
    }

    override fun getItemList(): List<Item> {
        return api.itemManager.items.map { Item5(it) }
    }

    override fun getMob(entity: Entity): Mob? {
        return Mob5((MythicProvider.get().mobManager as MobExecutor).getMythicMobInstance(entity) ?: return null)
    }

    override fun getMobType(name: String): MobType? {
        return MobType5(api.mobManager.getMythicMob(name)?.get() ?: return null)
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        return Skill5.Trigger(io.lumine.mythic.api.skills.SkillTrigger::class.java.invokeMethod<Any>("get", name.uppercase(), fixed = true)!!)
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        return Skill5(api.skillManager.getMechanic(MythicLineConfigImpl.unparseBlock(skillLine)) ?: return null)
    }

    object Loader {

        @Awake(LifeCycle.ENABLE)
        fun setup() {
            if (kotlin.runCatching { Class.forName("io.lumine.mythic.api.MythicProvider") }.getOrNull() != null) {
                Mythic.API = Mythic5()
            }
        }
    }
}