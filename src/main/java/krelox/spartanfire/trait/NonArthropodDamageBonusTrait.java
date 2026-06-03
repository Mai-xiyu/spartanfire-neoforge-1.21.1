package krelox.spartanfire.trait;

import com.iafenvoy.iceandfire.entity.DeathWormEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;

public class NonArthropodDamageBonusTrait extends SpartanFireMeleeTrait {
    public NonArthropodDamageBonusTrait() {
        super("non-arthropod_damage_bonus", TraitQuality.POSITIVE);
    }

    @Override
    public void onHitEntity(
            WeaponMaterial material,
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker,
            Entity projectile) {
        if (attacker instanceof Player && attacker.attackAnim > 0.2F) {
            return;
        }
        if (!target.getType().is(EntityTypeTags.ARTHROPOD) || target instanceof DeathWormEntity) {
            target.hurt(attacker.damageSources().generic(), 5.0F);
        }
    }
}
