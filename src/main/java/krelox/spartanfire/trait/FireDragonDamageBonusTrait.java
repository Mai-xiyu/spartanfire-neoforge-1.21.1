package krelox.spartanfire.trait;

import com.iafenvoy.iceandfire.entity.FireDragonEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;

public class FireDragonDamageBonusTrait extends SpartanFireMeleeTrait {
    public FireDragonDamageBonusTrait() {
        super("fire_dragon_damage_bonus", TraitQuality.POSITIVE);
    }

    @Override
    public void onHitEntity(
            WeaponMaterial material,
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker,
            Entity projectile) {
        if (target instanceof FireDragonEntity) {
            target.hurt(attacker.damageSources().drown(), 5.5F + getLevel() * 4.0F);
        }
    }
}
