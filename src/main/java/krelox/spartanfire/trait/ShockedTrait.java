package krelox.spartanfire.trait;

import com.iafenvoy.iceandfire.event.handler.ServerEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;

public class ShockedTrait extends SpartanFireMeleeTrait {
    public ShockedTrait() {
        super("shocked", TraitQuality.POSITIVE);
    }

    @Override
    public void onHitEntity(
            WeaponMaterial material,
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker,
            Entity projectile) {
        target.knockback(1.0F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());

        if (attacker instanceof Player && attacker.attackAnim > 0.2F) {
            return;
        }
        if (attacker.level().isClientSide) {
            return;
        }

        LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(target.level());
        if (lightning == null) {
            return;
        }
        lightning.getTags().add(ServerEvents.BOLT_DONT_DESTROY_LOOT);
        lightning.getTags().add(attacker.getStringUUID());
        lightning.moveTo(target.position());
        target.level().addFreshEntity(lightning);
    }
}
