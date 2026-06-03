package krelox.spartanfire;

import com.iafenvoy.iceandfire.registry.IafTiers;
import com.mojang.datafixers.util.Either;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import krelox.spartanfire.trait.FireDragonDamageBonusTrait;
import krelox.spartanfire.trait.FlamedTrait;
import krelox.spartanfire.trait.IceDragonDamageBonusTrait;
import krelox.spartanfire.trait.IcedTrait;
import krelox.spartanfire.trait.NonArthropodDamageBonusTrait;
import krelox.spartanfire.trait.PoisonedTrait;
import krelox.spartanfire.trait.ShockedTrait;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.xiyu.spartanweaponryunofficial.api.SpartanWeaponryAPI;
import org.xiyu.spartanweaponryunofficial.api.SpartanWeaponryAPI.WeaponItemType;
import org.xiyu.spartanweaponryunofficial.api.WeaponMaterial;
import org.xiyu.spartanweaponryunofficial.api.WeaponTraits;
import org.xiyu.spartanweaponryunofficial.api.trait.WeaponTrait;

@Mod(SpartanFire.MODID)
public class SpartanFire {
    public static final String MODID = "spartanfire";

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<WeaponTrait> TRAITS =
            DeferredRegister.create(WeaponTraits.REGISTRY_KEY, MODID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<WeaponTrait, WeaponTrait> ICE_DRAGON_DAMAGE_BONUS_I =
            registerTrait("ice_dragon_damage_bonus_1", new IceDragonDamageBonusTrait().setLevel(1));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> ICE_DRAGON_DAMAGE_BONUS_II =
            registerTrait("ice_dragon_damage_bonus_2", new IceDragonDamageBonusTrait().setLevel(2));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> FIRE_DRAGON_DAMAGE_BONUS_I =
            registerTrait("fire_dragon_damage_bonus_1", new FireDragonDamageBonusTrait().setLevel(1));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> FIRE_DRAGON_DAMAGE_BONUS_II =
            registerTrait("fire_dragon_damage_bonus_2", new FireDragonDamageBonusTrait().setLevel(2));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> FLAMED_I =
            registerTrait("flamed_1", new FlamedTrait().setLevel(1).setMagnitude(5.0F));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> FLAMED_II =
            registerTrait("flamed_2", new FlamedTrait().setLevel(2).setMagnitude(15.0F));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> ICED_I =
            registerTrait("iced_1", new IcedTrait().setLevel(1));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> ICED_II =
            registerTrait("iced_2", new IcedTrait().setLevel(2));
    public static final DeferredHolder<WeaponTrait, WeaponTrait> SHOCKED =
            registerTrait("shocked", new ShockedTrait());
    public static final DeferredHolder<WeaponTrait, WeaponTrait> NON_ARTHROPOD_DAMAGE_BONUS =
            registerTrait("non-arthropod_damage_bonus", new NonArthropodDamageBonusTrait());
    public static final DeferredHolder<WeaponTrait, WeaponTrait> POISONED =
            registerTrait("poisoned", new PoisonedTrait());

    public static final DeferredItem<Item> WITHERBONE_HANDLE =
            ITEMS.register("witherbone_handle", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WITHERBONE_POLE =
            ITEMS.register("witherbone_pole", () -> new Item(new Item.Properties()));

    private static final TagKey<Item> WITHERBONE = itemTag("c:bones/wither");

    public static final Map<WeaponMaterial, EnumMap<WeaponItemType, DeferredItem<Item>>> WEAPONS =
            new LinkedHashMap<>();
    public static final List<WeaponMaterial> MATERIALS = new ArrayList<>();

    public static final WeaponMaterial DRAGON_BONE =
            material("dragon_bone", IafTiers.DRAGONBONE_TOOL_MATERIAL, "c:bones/dragon");
    public static final WeaponMaterial FLAMED_DRAGON_BONE =
            material("flamed_dragon_bone", IafTiers.BLOODED_DRAGONBONE_TOOL_MATERIAL, "c:bones/dragon");
    public static final WeaponMaterial ICED_DRAGON_BONE =
            material("iced_dragon_bone", IafTiers.BLOODED_DRAGONBONE_TOOL_MATERIAL, "c:bones/dragon");
    public static final WeaponMaterial LIGHTNING_DRAGON_BONE =
            material("lightning_dragon_bone", IafTiers.BLOODED_DRAGONBONE_TOOL_MATERIAL, "c:bones/dragon");

    public static final WeaponMaterial DESERT_MYRMEX_CHITIN =
            material("desert_myrmex_chitin", 600, 7.0F, 3.0F, 14, "c:chitin_desert");
    public static final WeaponMaterial DESERT_MYRMEX_STINGER =
            material("desert_myrmex_stinger", 600, 7.0F, 3.0F, 14, "c:chitin_desert");
    public static final WeaponMaterial JUNGLE_MYRMEX_CHITIN =
            material("jungle_myrmex_chitin", 600, 7.0F, 3.0F, 14, "c:chitin_jungle");
    public static final WeaponMaterial JUNGLE_MYRMEX_STINGER =
            material("jungle_myrmex_stinger", 600, 7.0F, 3.0F, 14, "c:chitin_jungle");

    public static final WeaponMaterial FIRE_DRAGONSTEEL =
            material("fire_dragonsteel", IafTiers.DRAGONSTEEL_FIRE, "c:ingots/fire_dragonsteel");
    public static final WeaponMaterial ICE_DRAGONSTEEL =
            material("ice_dragonsteel", IafTiers.DRAGONSTEEL_ICE, "c:ingots/ice_dragonsteel");
    public static final WeaponMaterial LIGHTNING_DRAGONSTEEL =
            material(
                    "lightning_dragonsteel",
                    IafTiers.DRAGONSTEEL_LIGHTNING,
                    "c:ingots/lightning_dragonsteel");

    static {
        MATERIALS.forEach(SpartanFire::registerWeapons);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPARTAN_FIRE_TAB =
            TABS.register(
                    MODID,
                    () ->
                            CreativeModeTab.builder()
                                    .title(Component.translatable("itemGroup." + MODID))
                                    .icon(
                                            () ->
                                                    new ItemStack(
                                                            getWeapon(
                                                                            FLAMED_DRAGON_BONE,
                                                                            WeaponItemType
                                                                                    .GREATSWORD)
                                                                    .get()))
                                    .displayItems(
                                            (parameters, output) ->
                                                    ITEMS.getEntries()
                                                            .forEach(item -> output.accept(item.get())))
                                    .build());

    public SpartanFire(IEventBus modBus) {
        SpartanWeaponryAPI.assertAPIVersion(MODID, 14);
        ITEMS.register(modBus);
        TRAITS.register(modBus);
        TABS.register(modBus);
    }

    public static DeferredItem<Item> getWeapon(WeaponMaterial material, WeaponItemType type) {
        return WEAPONS.get(material).get(type);
    }

    private static DeferredHolder<WeaponTrait, WeaponTrait> registerTrait(
            String name, WeaponTrait trait) {
        return TRAITS.register(name, () -> trait);
    }

    private static WeaponMaterial material(String name, Tier tier, String repairTag) {
        WeaponMaterial material =
                WeaponMaterial.builder(name, MODID)
                        .tier(tier)
                        .repairTag(itemTag(repairTag))
                        .traitsTag(traitTag("materials/" + name))
                        .build();
        MATERIALS.add(material);
        return material;
    }

    private static WeaponMaterial material(
            String name,
            int durability,
            float speed,
            float baseDamage,
            int enchantability,
            String repairTag) {
        WeaponMaterial material =
                WeaponMaterial.builder(name, MODID)
                        .durability(durability)
                        .speed(speed)
                        .baseDamage(baseDamage)
                        .enchantmentValue(enchantability)
                        .repairTag(itemTag(repairTag))
                        .traitsTag(traitTag("materials/" + name))
                        .build();
        MATERIALS.add(material);
        return material;
    }

    private static void registerWeapons(WeaponMaterial material) {
        EnumMap<WeaponItemType, DeferredItem<Item>> materialWeapons =
                WEAPONS.computeIfAbsent(material, key -> new EnumMap<>(WeaponItemType.class));
        for (WeaponItemType type : WeaponItemType.values()) {
            String itemName = material.getMaterialName() + "_" + type.getSerializedName();
            materialWeapons.put(
                    type, ITEMS.register(itemName, () -> SpartanWeaponryAPI.createWeapon(type, material)));
        }
    }

    private static TagKey<Item> itemTag(String location) {
        return ItemTags.create(ResourceLocation.parse(location));
    }

    private static TagKey<WeaponTrait> traitTag(String path) {
        return WeaponTraits.REGISTRY.createTagKey(ResourceLocation.fromNamespaceAndPath(MODID, path));
    }

    private static boolean isLegendaryDragonBone(Item item) {
        return SpartanWeaponryAPI.getWeaponClassification(item)
                .map(classification -> classification.material())
                .filter(
                        material ->
                                material == FLAMED_DRAGON_BONE
                                        || material == ICED_DRAGON_BONE
                                        || material == LIGHTNING_DRAGON_BONE)
                .isPresent();
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
    public static final class ClientEvents {
        private ClientEvents() {}

        @SubscribeEvent
        public static void gatherTooltipComponents(RenderTooltipEvent.GatherComponents event) {
            if (isLegendaryDragonBone(event.getItemStack().getItem())) {
                event.getTooltipElements()
                        .add(
                                1,
                                Either.left(
                                        Component.translatable("item.iceandfire.legendary_weapon.desc")
                                                .withStyle(ChatFormatting.GOLD)));
            }
        }
    }
}
