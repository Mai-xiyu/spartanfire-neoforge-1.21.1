package krelox.spartanfire.trait;

import com.iafenvoy.iceandfire.entity.IceDragonEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;

public class IceDragonDamageBonusTrait extends SpartanFireMeleeTrait {
    public IceDragonDamageBonusTrait() {
        super("ice_dragon_damage_bonus", TraitQuality.POSITIVE);
    }

    @Override
    public void onHitEntity(
            WeaponMaterial material,
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker,
            Entity projectile) {
        if (target instanceof IceDragonEntity) {
            target.hurt(attacker.damageSources().inFire(), 5.5F + getLevel() * 4.0F);
        }
    }
}
