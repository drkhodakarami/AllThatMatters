package jiraiyah.allthatmatters.item;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.item.custom.ModArmorItem;
import jiraiyah.allthatmatters.item.custom.GemBow;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems
{
    public static final Item RUBY = registerItem("gem_ruby", new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new FabricItemSettings()));

    public static final Item CITRINE = registerItem("gem_citrine", new Item(new FabricItemSettings()));
    public static final Item RAW_CITRINE = registerItem("raw_citrine", new Item(new FabricItemSettings()));

    public static final Item SAPPHIRE = registerItem("gem_sapphire", new Item(new FabricItemSettings()));
    public static final Item RAW_SAPPHIRE = registerItem("raw_sapphire", new Item(new FabricItemSettings()));

    public static final Item ENDERITE = registerItem("ingot_enderite", new Item(new FabricItemSettings()));
    public static final Item RAW_ENDERITE = registerItem("raw_enderite", new Item(new FabricItemSettings()));

    //region CITRINE TOOLS
    public static final Item TOOL_CITRINE_AXE = registerItem("tool_citrine_axe",
            new AxeItem(ModToolMaterial.CITRINE, 3, 1, new FabricItemSettings()));
    // TODO : HAMMER Item Object
    public static final Item TOOL_CITRINE_HAMMER = registerItem("tool_citrine_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TOOL_CITRINE_HOE = registerItem("tool_citrine_hoe",
            new HoeItem(ModToolMaterial.CITRINE, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_CITRINE_PICKAXE = registerItem("tool_citrine_pickaxe",
            new PickaxeItem(ModToolMaterial.CITRINE, 1, 1f, new FabricItemSettings()));
    public static final Item TOOL_CITRINE_SHOVEL = registerItem("tool_citrine_shovel",
            new ShovelItem(ModToolMaterial.CITRINE, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_CITRINE_SWORD = registerItem("tool_citrine_sword",
            new SwordItem(ModToolMaterial.CITRINE, 5, 3, new FabricItemSettings()));
    //endregion

    //region COPPER TOOLS
    public static final Item TOOL_COPPER_AXE = registerItem("tool_copper_axe",
            new AxeItem(ModToolMaterial.COPPER, 3, 1, new FabricItemSettings()));
    // TODO : HAMMER Item Object
    public static final Item TOOL_COPPER_HAMMER = registerItem("tool_copper_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TOOL_COPPER_HOE = registerItem("tool_copper_hoe",
            new HoeItem(ModToolMaterial.COPPER, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_COPPER_PICKAXE = registerItem("tool_copper_pickaxe",
            new PickaxeItem(ModToolMaterial.COPPER, 1, 1f, new FabricItemSettings()));
    public static final Item TOOL_COPPER_SHOVEL = registerItem("tool_copper_shovel",
            new ShovelItem(ModToolMaterial.COPPER, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_COPPER_SWORD = registerItem("tool_copper_sword",
            new SwordItem(ModToolMaterial.COPPER, 5, 3, new FabricItemSettings()));
    //endregion

    //region VANILLA HAMMERS
    // TODO : HAMMER Item Object
    public static final Item TOOL_DIAMOND_HAMMER = registerItem("tool_diamond_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    // TODO : HAMMER Item Object
    public static final Item TOOL_GOLD_HAMMER = registerItem("tool_gold_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    // TODO : HAMMER Item Object
    public static final Item TOOL_IRON_HAMMER = registerItem("tool_iron_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    // TODO : HAMMER Item Object
    public static final Item TOOL_NETHERITE_HAMMER = registerItem("tool_netherite_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    // TODO : HAMMER Item Object
    public static final Item TOOL_STONE_HAMMER = registerItem("tool_stone_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    // TODO : HAMMER Item Object
    public static final Item TOOL_WOOD_HAMMER = registerItem("tool_wood_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    //endregion

    //region ENDERITE TOOLS
    public static final Item TOOL_ENDERITE_AXE = registerItem("tool_enderite_axe",
            new AxeItem(ModToolMaterial.ENDERITE, 3, 1, new FabricItemSettings()));
    // TODO : HAMMER Item Object
    public static final Item TOOL_ENDERITE_HAMMER = registerItem("tool_enderite_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TOOL_ENDERITE_HOE = registerItem("tool_enderite_hoe",
            new HoeItem(ModToolMaterial.ENDERITE, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_ENDERITE_PICKAXE = registerItem("tool_enderite_pickaxe",
            new PickaxeItem(ModToolMaterial.ENDERITE, 1, 1f, new FabricItemSettings()));
    public static final Item TOOL_ENDERITE_SHOVEL = registerItem("tool_enderite_shovel",
            new ShovelItem(ModToolMaterial.ENDERITE, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_ENDERITE_SWORD = registerItem("tool_enderite_sword",
            new SwordItem(ModToolMaterial.ENDERITE, 5, 3, new FabricItemSettings()));
    //endregion

    //region RUBY TOOLS
    public static final Item TOOL_RUBY_AXE = registerItem("tool_ruby_axe",
            new AxeItem(ModToolMaterial.RUBY, 3, 1, new FabricItemSettings()));
    // TODO : HAMMER Item Object
    public static final Item TOOL_RUBY_HAMMER = registerItem("tool_ruby_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TOOL_RUBY_HOE = registerItem("tool_ruby_hoe",
            new HoeItem(ModToolMaterial.RUBY, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_RUBY_PICKAXE = registerItem("tool_ruby_pickaxe",
            new PickaxeItem(ModToolMaterial.RUBY, 1, 1f, new FabricItemSettings()));
    public static final Item TOOL_RUBY_SHOVEL = registerItem("tool_ruby_shovel",
            new ShovelItem(ModToolMaterial.RUBY, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_RUBY_SWORD = registerItem("tool_ruby_sword",
            new SwordItem(ModToolMaterial.RUBY, 5, 3, new FabricItemSettings()));
    //endregion

    //region SAPPHIRE TOOLS
    public static final Item TOOL_SAPPHIRE_AXE = registerItem("tool_sapphire_axe",
            new AxeItem(ModToolMaterial.SAPPHIRE, 3, 1, new FabricItemSettings()));
    // TODO : HAMMER Item Object
    public static final Item TOOL_SAPPHIRE_HAMMER = registerItem("tool_sapphire_hammer",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TOOL_SAPPHIRE_HOE = registerItem("tool_sapphire_hoe",
            new HoeItem(ModToolMaterial.SAPPHIRE, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_SAPPHIRE_PICKAXE = registerItem("tool_sapphire_pickaxe",
            new PickaxeItem(ModToolMaterial.SAPPHIRE, 1, 1f, new FabricItemSettings()));
    public static final Item TOOL_SAPPHIRE_SHOVEL = registerItem("tool_sapphire_shovel",
            new ShovelItem(ModToolMaterial.SAPPHIRE, 0, 0, new FabricItemSettings()));
    public static final Item TOOL_SAPPHIRE_SWORD = registerItem("tool_sapphire_sword",
            new SwordItem(ModToolMaterial.SAPPHIRE, 5, 3, new FabricItemSettings()));
    //endregion

    //region AMETHYST ARMOR
    public static final Item ARMOR_AMETHYST_HELMET = registerItem("armor_amethyst_helmet",
            new ModArmorItem(ModArmorMaterial.AMETHYST, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARMOR_AMETHYST_CHESTPLATE = registerItem("armor_amethyst_chestplate",
            new ArmorItem(ModArmorMaterial.AMETHYST, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARMOR_AMETHYST_LEGGINGS = registerItem("armor_amethyst_leggings",
            new ArmorItem(ModArmorMaterial.AMETHYST, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARMOR_AMETHYST_BOOTS = registerItem("armor_amethyst_boots",
            new ArmorItem(ModArmorMaterial.AMETHYST, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    //region CITRINE ARMOR
    public static final Item ARMOR_CITRINE_HELMET = registerItem("armor_citrine_helmet",
            new ModArmorItem(ModArmorMaterial.CITRINE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARMOR_CITRINE_CHESTPLATE = registerItem("armor_citrine_chestplate",
            new ArmorItem(ModArmorMaterial.CITRINE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARMOR_CITRINE_LEGGINGS = registerItem("armor_citrine_leggings",
            new ArmorItem(ModArmorMaterial.CITRINE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARMOR_CITRINE_BOOTS = registerItem("armor_citrine_boots",
            new ArmorItem(ModArmorMaterial.CITRINE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    //region COPPER ARMOR
    public static final Item ARMOR_COPPER_HELMET = registerItem("armor_copper_helmet",
            new ModArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARMOR_COPPER_CHESTPLATE = registerItem("armor_copper_chestplate",
            new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARMOR_COPPER_LEGGINGS = registerItem("armor_copper_leggings",
            new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARMOR_COPPER_BOOTS = registerItem("armor_copper_boots",
            new ArmorItem(ModArmorMaterial.COPPER, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    //region EMERALD ARMOR
    public static final Item ARMOR_EMERALD_HELMET = registerItem("armor_emerald_helmet",
            new ModArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARMOR_EMERALD_CHESTPLATE = registerItem("armor_emerald_chestplate",
            new ArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARMOR_EMERALD_LEGGINGS = registerItem("armor_emerald_leggings",
            new ArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARMOR_EMERALD_BOOTS = registerItem("armor_emerald_boots",
            new ArmorItem(ModArmorMaterial.EMERALD, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    //region ENDERITE ARMOR
    public static final Item ARMOR_ENDERITE_HELMET = registerItem("armor_enderite_helmet",
            new ModArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARMOR_ENDERITE_CHESTPLATE = registerItem("armor_enderite_chestplate",
            new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARMOR_ENDERITE_LEGGINGS = registerItem("armor_enderite_leggings",
            new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARMOR_ENDERITE_BOOTS = registerItem("armor_enderite_boots",
            new ArmorItem(ModArmorMaterial.ENDERITE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    //region RUBY ARMOR
    public static final Item ARMOR_RUBY_HELMET = registerItem("armor_ruby_helmet",
            new ModArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARMOR_RUBY_CHESTPLATE = registerItem("armor_ruby_chestplate",
            new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARMOR_RUBY_LEGGINGS = registerItem("armor_ruby_leggings",
            new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARMOR_RUBY_BOOTS = registerItem("armor_ruby_boots",
            new ArmorItem(ModArmorMaterial.RUBY, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    //region SAPPHIRE ARMOR
    public static final Item ARMOR_SAPPHIRE_HELMET = registerItem("armor_sapphire_helmet",
            new ModArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARMOR_SAPPHIRE_CHESTPLATE = registerItem("armor_sapphire_chestplate",
            new ArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARMOR_SAPPHIRE_LEGGINGS = registerItem("armor_sapphire_leggings",
            new ArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARMOR_SAPPHIRE_BOOTS = registerItem("armor_sapphire_boots",
            new ArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    public static final Item TOOL_RUBY_BOW = registerItem("tool_ruby_bow",
            new GemBow(new FabricItemSettings().maxDamage(2000)));

    private ModItems()
    {
        throw new AssertionError();
    }

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, AllThatMatters.identifier(name), item);
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Items");
    }
}