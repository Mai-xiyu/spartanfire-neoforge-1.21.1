package krelox.spartanfire.trait;

import com.iafenvoy.iceandfire.registry.IafMobEffects;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;

public class IcedTrait extends SpartanFireMeleeTrait {
    public IcedTrait() {
        super("iced", TraitQuality.POSITIVE);
    }

    @Override
    public void onHitEntity(
            WeaponMaterial material,
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker,
            Entity projectile) {
        int duration = 100 + getLevel() * 100;
        target.addEffect(
                new MobEffectInstance(
                        BuiltInRegistries.MOB_EFFECT.wrapAsHolder(IafMobEffects.FROZEN.get()),
                        duration));
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 2));
    }
}
