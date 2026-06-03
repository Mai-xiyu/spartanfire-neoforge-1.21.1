package krelox.spartanfire.mixin;

import krelox.spartanfire.SpartanFire;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.xiyu.spartanweaponryunofficial.api.SpartanWeaponryAPI;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "isFoil", at = @At("RETURN"), cancellable = true)
    private void spartanfire$isFoil(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean isLegendaryDragonBone =
                SpartanWeaponryAPI.getWeaponClassification(stack.getItem())
                        .map(classification -> classification.material())
                        .filter(
                                material ->
                                        material == SpartanFire.FLAMED_DRAGON_BONE
                                                || material == SpartanFire.ICED_DRAGON_BONE
                                                || material == SpartanFire.LIGHTNING_DRAGON_BONE)
                        .isPresent();
        cir.setReturnValue(cir.getReturnValue() || isLegendaryDragonBone);
    }
}
