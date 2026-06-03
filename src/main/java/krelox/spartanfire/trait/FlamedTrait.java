package krelox.spartanfire.trait;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;

public class FlamedTrait extends SpartanFireMeleeTrait {
    public FlamedTrait() {
        super("flamed", TraitQuality.POSITIVE);
    }

    @Override
    public void onHitEntity(
            WeaponMaterial material,
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker,
            Entity projectile) {
        target.igniteForSeconds(getMagnitude());
        target.knockback(1.0F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
    }
}
