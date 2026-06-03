package krelox.spartanfire.trait;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;

public class PoisonedTrait extends SpartanFireMeleeTrait {
    public PoisonedTrait() {
        super("poisoned", TraitQuality.POSITIVE);
    }

    @Override
    public void onHitEntity(
            WeaponMaterial material,
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker,
            Entity projectile) {
        target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 2));
    }
}
