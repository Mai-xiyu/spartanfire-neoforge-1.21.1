package krelox.spartanfire.trait;

import java.util.Optional;
import krelox.spartanfire.SpartanFire;
import org.xiyu.spartanweaponryunofficial.api.trait.IMeleeTraitCallback;
import org.xiyu.spartanweaponryunofficial.api.trait.WeaponTrait;

public abstract class SpartanFireMeleeTrait extends WeaponTrait implements IMeleeTraitCallback {
    protected SpartanFireMeleeTrait(String type, TraitQuality quality) {
        super(type, SpartanFire.MODID, quality);
        setUniversal();
    }

    @Override
    public Optional<IMeleeTraitCallback> getMeleeCallback() {
        return Optional.of(this);
    }
}
