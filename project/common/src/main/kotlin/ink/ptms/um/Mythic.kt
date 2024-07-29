package ink.ptms.um

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.io.File

interface Mythic {

    /** 是否 4.X 版本 */
    val isLegacy: Boolean

    /** 获取 MythicItem 实例 */
    fun getItem(name: String): Item?

    /** 获取 ItemStack 所对应的 MythicItem ID */
    fun getItemId(itemStack: ItemStack): String?

    /** 获取 MythicItem 实例并构建到 ItemStack */
    fun getItemStack(name: String): ItemStack?

    /** 获取 MythicItem ID 列表 */
    fun getItemIDList(): List<String>

    /** 获取 MythicItem列表 */
    fun getItemList(): List<Item>

    /** 获取 ActiveMob 实例 */
    fun getMob(entity: Entity): Mob?

    /** 获取所有 Mob ID 列表 */
    fun getMobIDList(): List<String>

    /** 获取 MythicMob 实例 */
    fun getMobType(name: String): MobType?

    /** 获取技能类型 */
    fun getSkillTrigger(name: String): Skill.Trigger

    /** 获取默认技能类型 */
    fun getDefaultSkillTrigger(): Skill.Trigger

    /**
     * 将字符串转换为技能实例
     *
     * @param skillLine 技能字符串（如：`message{m="text"}`）
     */
    fun getSkillMechanic(skillLine: String): Skill?

    /**
     * 获取玩家的目标
     *
     * @param player 玩家
     */
    fun getTargetedEntity(player: Player): LivingEntity?

    /**
     * 释放技能
     *
     * @param caster 施法者
     * @param skillName 技能名称
     * @param trigger 触发器
     * @param origin 技能释放位置
     * @param et 技能释放目标
     * @param lt 技能释放位置
     * @param power 技能释放强度
     */
    fun castSkill(
        caster: Entity,
        skillName: String,
        trigger: Entity? = null,
        origin: Location = caster.location,
        et: Collection<Entity> = emptyList(),
        lt: Collection<Location> = emptyList(),
        power: Float = 1f,
    ): Boolean

    /**
     * 注册物品
     *
     * @param file 文件
     * @param node 节点名
     *
     * @return 如果物品存在则返回false
     */
    fun registerItem(file: File, node: String): Boolean

    /**
     * 移除已注册物品
     *
     * @param node 节点名
     *
     * @return 当不存在物品时,返回false
     */
    fun unregisterItem(node: String): Boolean

    /**
     * 注册怪物
     *
     * @param file 怪物文件
     * @param node 怪物节点名
     *
     * @return 如果已存在则返回false
     */
    fun registerMob(file: File, node: String): Boolean

    /**
     * 移除已注册的怪物
     *
     * @param node 节点名
     *
     * @return 如果不存在则返回false
     */
    fun unregisterMob(node: String): Boolean

    companion object {

        @JvmStatic
        lateinit var API: Mythic

        fun isLoaded(): Boolean {
            return ::API.isInitialized
        }
    }
}