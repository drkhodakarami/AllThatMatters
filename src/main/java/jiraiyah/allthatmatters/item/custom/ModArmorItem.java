package jiraiyah.allthatmatters.item.custom;

import jiraiyah.allthatmatters.effect.ModEffects;
import jiraiyah.allthatmatters.item.ModArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModArmorItem extends ArmorItem
{
    private static final Map<ArmorMaterial, ArrayList<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP;

    static
    {
        MATERIAL_TO_EFFECT_MAP = new HashMap<>();
        addValues(ModArmorMaterial.EMERALD, new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.EMERALD, new StatusEffectInstance(StatusEffects.BAD_OMEN, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.EMERALD, new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 3, false, false, true));

        addValues(ModArmorMaterial.CITRINE, new StatusEffectInstance(StatusEffects.HASTE, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.CITRINE, new StatusEffectInstance(StatusEffects.INVISIBILITY, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.CITRINE, new StatusEffectInstance(StatusEffects.SATURATION, 1200, 3, false, false, true));

        addValues(ModArmorMaterial.AMETHYST, new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.AMETHYST, new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.AMETHYST, new StatusEffectInstance(StatusEffects.LUCK, 1200, 3, false, false, true));

        addValues(ModArmorMaterial.RUBY, new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.RUBY, new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.RUBY, new StatusEffectInstance(StatusEffects.LUCK, 1200, 3, false, false, true));

        addValues(ModArmorMaterial.SAPPHIRE, new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.SAPPHIRE, new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.SAPPHIRE, new StatusEffectInstance(StatusEffects.INVISIBILITY, 1200, 3, false, false, true));

        addValues(ModArmorMaterial.COPPER, new StatusEffectInstance(StatusEffects.SPEED, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.COPPER, new StatusEffectInstance(StatusEffects.JUMP_BOOST, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.COPPER, new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1200, 3, false, false, true));

        addValues(ModArmorMaterial.ENDERITE, new StatusEffectInstance(StatusEffects.LUCK, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.ENDERITE, new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 3, false, false, true));
        addValues(ModArmorMaterial.ENDERITE, new StatusEffectInstance(ModEffects.FLIGHT_EFFECT, 1200, 3, false, false, true));
    }

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings)
    {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        if (!world.isClient())
            if (entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player))
                evaluateArmorEffects(player);

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player)
    {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private void evaluateArmorEffects(PlayerEntity player)
    {
        for (HashMap.Entry<ArmorMaterial, ArrayList<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet())
        {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            entry.getValue().forEach((effect) ->
            {
                if (hasCorrectArmorOn(mapArmorMaterial, player) && isStatusEffectFading(player, effect))
                    addStatusEffectForMaterial(player, effect);
            });
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player)
    {
        // In case of Elytra, this will return and prevent game crash because Elytra is not ArmorItem
        for (ItemStack armorStack : player.getInventory().armor)
            if (!(armorStack.getItem() instanceof ArmorItem))
                return false;

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem) player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    private boolean isStatusEffectFading(PlayerEntity player, StatusEffectInstance effect)
    {
        return !player.hasStatusEffect(effect.getEffectType()) ||
                player.getStatusEffect(effect.getEffectType()).isDurationBelow(220);

    }

    private void addStatusEffectForMaterial(PlayerEntity player, StatusEffectInstance mapStatusEffect)
    {
        player.addStatusEffect(new StatusEffectInstance(mapStatusEffect.getEffectType(),
                mapStatusEffect.getDuration(),
                mapStatusEffect.getAmplifier(),
                mapStatusEffect.isAmbient(),
                mapStatusEffect.shouldShowParticles(),
                mapStatusEffect.shouldShowIcon()));
    }

    private static void addValues(ArmorMaterial key, StatusEffectInstance value)
    {
        ArrayList tempList;
        if (MATERIAL_TO_EFFECT_MAP.containsKey(key))
        {
            tempList = MATERIAL_TO_EFFECT_MAP.get(key);
            if (tempList == null)
                tempList = new ArrayList();
            tempList.add(value);
        }
        else
        {
            tempList = new ArrayList();
            tempList.add(value);
        }
        MATERIAL_TO_EFFECT_MAP.put(key, tempList);
    }
}