package jiraiyah.allthatmatters.item;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial
{
    CITRINE(MiningLevels.IRON, 1000, 10, 3.0f, 26,
            () -> Ingredient.ofItems(ModItems.CITRINE)),
    COPPER(MiningLevels.IRON, 500, 6, 1.0f, 12,
            () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    ENDERITE(5, 3000, 24, 5.0f, 30,
            () -> Ingredient.ofItems(ModItems.ENDERITE)),
    RUBY(MiningLevels.DIAMOND, 2000, 12, 4.0f, 26,
            () -> Ingredient.ofItems(ModItems.RUBY)),
    SAPPHIRE(MiningLevels.DIAMOND, 2000, 12, 4.0f, 26,
            () -> Ingredient.ofItems(ModItems.SAPPHIRE));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantibility, Supplier<Ingredient> repairIngredient)
    {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantibility;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability()
    {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier()
    {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage()
    {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel()
    {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability()
    {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }
}