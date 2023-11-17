package jiraiyah.allthatmatters.item;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.item.custom.GemBow;
import jiraiyah.allthatmatters.item.custom.HammerItem;
import jiraiyah.allthatmatters.item.custom.ModArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems
{
    //region GEM
    public static final Item RUBY = registerItem("gem_ruby", new Item(new FabricItemSettings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new FabricItemSettings()));

    public static final Item CITRINE = registerItem("gem_citrine", new Item(new FabricItemSettings()));
    public static final Item RAW_CITRINE = registerItem("raw_citrine", new Item(new FabricItemSettings()));

    public static final Item SAPPHIRE = registerItem("gem_sapphire", new Item(new FabricItemSettings()));
    public static final Item RAW_SAPPHIRE = registerItem("raw_sapphire", new Item(new FabricItemSettings()));

    public static final Item ENDERITE = registerItem("ingot_enderite", new Item(new FabricItemSettings()));
    public static final Item RAW_ENDERITE = registerItem("raw_enderite", new Item(new FabricItemSettings()));
    //endregion

    //region CITRINE TOOLS
    public static final Item TOOL_CITRINE_AXE = registerItem("tool_citrine_axe",
            new AxeItem(ModToolMaterial.CITRINE, 3, 1, new FabricItemSettings()));
    public static final Item TOOL_CITRINE_HAMMER = registerItem("tool_citrine_hammer",
            new HammerItem(ModToolMaterial.SAPPHIRE, 3, 2));
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
    public static final Item TOOL_COPPER_HAMMER = registerItem("tool_copper_hammer",
            new HammerItem(ModToolMaterial.COPPER, 3, 2));
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
    public static final Item TOOL_DIAMOND_HAMMER = registerItem("tool_diamond_hammer",
            new HammerItem(ToolMaterials.DIAMOND, 5, 2));
    public static final Item TOOL_GOLD_HAMMER = registerItem("tool_gold_hammer",
            new HammerItem(ToolMaterials.GOLD, 5, 1));
    public static final Item TOOL_IRON_HAMMER = registerItem("tool_iron_hammer",
            new HammerItem(ToolMaterials.IRON, 5, 1));
    public static final Item TOOL_NETHERITE_HAMMER = registerItem("tool_netherite_hammer",
            new HammerItem(ToolMaterials.NETHERITE, 5, 3));
    public static final Item TOOL_STONE_HAMMER = registerItem("tool_stone_hammer",
            new HammerItem(ToolMaterials.STONE, 3, 1));
    public static final Item TOOL_WOOD_HAMMER = registerItem("tool_wood_hammer",
            new HammerItem(ToolMaterials.WOOD, 3, 1));
    //endregion

    //region ENDERITE TOOLS
    public static final Item TOOL_ENDERITE_AXE = registerItem("tool_enderite_axe",
            new AxeItem(ModToolMaterial.ENDERITE, 3, 1, new FabricItemSettings()));
    public static final Item TOOL_ENDERITE_HAMMER = registerItem("tool_enderite_hammer",
            new HammerItem(ModToolMaterial.ENDERITE, 5, 5));
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
    public static final Item TOOL_RUBY_HAMMER = registerItem("tool_ruby_hammer",
            new HammerItem(ModToolMaterial.RUBY, 3, 3));
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
    public static final Item TOOL_SAPPHIRE_HAMMER = registerItem("tool_sapphire_hammer",
            new HammerItem(ModToolMaterial.SAPPHIRE, 3, 3));
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

    //region GEM BOWS
    public static final Item TOOL_RUBY_BOW = registerItem("tool_ruby_bow",
            new GemBow(new FabricItemSettings().maxDamage(2000), false));
    public static final Item TOOL_SAPPHIRE_BOW = registerItem("tool_sapphire_bow",
            new GemBow(new FabricItemSettings().maxDamage(2000), false));
    public static final Item TOOL_ENDERITE_BOW = registerItem("tool_enderite_bow",
            new GemBow(new FabricItemSettings().maxDamage(2000), true));
    //endregion

    //region CASTS
    public static final Item CAST_AXE = registerItem("cast_axe", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_BINDING = registerItem("cast_binding", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_GEAR = registerItem("cast_gear", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_GEM = registerItem("cast_gem", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_HAMMER = registerItem("cast_hammer", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_HANDLE = registerItem("cast_handle", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_HOE = registerItem("cast_hoe", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_INGOT = registerItem("cast_ingot", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_NUGGET = registerItem("cast_nugget", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_PICKAXE = registerItem("cast_pickaxe", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_PLATE = registerItem("cast_plate", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_ROD = registerItem("cast_rod", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_SHOVEL = registerItem("cast_shovel", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_SWORD = registerItem("cast_sword", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_WIRE = registerItem("cast_wire", new Item(new FabricItemSettings().maxCount(1)));

    public static final Item CAST_WOOD_PICKAXE = registerItem("cast_wood_pickaxe", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CAST_WOOD_INGOT = registerItem("cast_wood_ingot", new Item(new FabricItemSettings().maxCount(1)));
    //endregion

    public static final Item BINDING = registerItem("binding", new Item(new FabricItemSettings()));

    //region GEARS
    public static final Item GEAR_COPPER = registerItem("gear_copper", new Item(new FabricItemSettings()));
    public static final Item GEAR_DIAMOND = registerItem("gear_diamond", new Item(new FabricItemSettings()));
    public static final Item GEAR_EMERALD = registerItem("gear_emerald", new Item(new FabricItemSettings()));
    public static final Item GEAR_ENDERITE = registerItem("gear_enderite", new Item(new FabricItemSettings()));
    public static final Item GEAR_GOLD = registerItem("gear_gold", new Item(new FabricItemSettings()));
    public static final Item GEAR_IRON = registerItem("gear_iron", new Item(new FabricItemSettings()));
    public static final Item GEAR_LAPIS = registerItem("gear_lapis", new Item(new FabricItemSettings()));
    public static final Item GEAR_NETHERITE = registerItem("gear_netherite", new Item(new FabricItemSettings()));
    public static final Item GEAR_OBSIDIAN = registerItem("gear_obsidian", new Item(new FabricItemSettings()));
    public static final Item GEAR_PRISMARINE = registerItem("gear_prismarine", new Item(new FabricItemSettings()));
    public static final Item GEAR_QUARTZ = registerItem("gear_quartz", new Item(new FabricItemSettings()));
    public static final Item GEAR_RUBY = registerItem("gear_ruby", new Item(new FabricItemSettings()));
    public static final Item GEAR_SAPPHIRE = registerItem("gear_sapphire", new Item(new FabricItemSettings()));
    public static final Item GEAR_STONE = registerItem("gear_stone", new Item(new FabricItemSettings()));
    public static final Item GEAR_WOOD = registerItem("gear_wood", new Item(new FabricItemSettings()));
    //endregion

    //region PLATES
    public static final Item PLATE_AMETHYST = registerItem("plate_amethyst", new Item(new FabricItemSettings()));
    public static final Item PLATE_CITRINE = registerItem("plate_citrine", new Item(new FabricItemSettings()));
    public static final Item PLATE_COPPER = registerItem("plate_copper", new Item(new FabricItemSettings()));
    public static final Item PLATE_DIAMOND = registerItem("plate_diamond", new Item(new FabricItemSettings()));
    public static final Item PLATE_EMERALD = registerItem("plate_emerald", new Item(new FabricItemSettings()));
    public static final Item PLATE_ENDERITE = registerItem("plate_enderite", new Item(new FabricItemSettings()));
    public static final Item PLATE_GOLD = registerItem("plate_gold", new Item(new FabricItemSettings()));
    public static final Item PLATE_IRON = registerItem("plate_iron", new Item(new FabricItemSettings()));
    public static final Item PLATE_NETHERITE = registerItem("plate_netherite", new Item(new FabricItemSettings()));
    public static final Item PLATE_RUBY = registerItem("plate_ruby", new Item(new FabricItemSettings()));
    public static final Item PLATE_SAPPHIRE = registerItem("plate_sapphire", new Item(new FabricItemSettings()));

    public static final Item REINFORCED_PLATE_AMETHYST = registerItem("reinforced_plate_amethyst", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_CITRINE = registerItem("reinforced_plate_citrine", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_COPPER = registerItem("reinforced_plate_copper", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_DIAMOND = registerItem("reinforced_plate_diamond", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_EMERALD = registerItem("reinforced_plate_emerald", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_ENDERITE = registerItem("reinforced_plate_enderite", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_GOLD = registerItem("reinforced_plate_gold", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_IRON = registerItem("reinforced_plate_iron", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_NETHERITE = registerItem("reinforced_plate_netherite", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_RUBY = registerItem("reinforced_plate_ruby", new Item(new FabricItemSettings()));
    public static final Item REINFORCED_PLATE_SAPPHIRE = registerItem("reinforced_plate_sapphire", new Item(new FabricItemSettings()));
    //endregion

    //region RODS
    public static final Item ROD_COPPER = registerItem("rod_copper", new Item(new FabricItemSettings()));
    public static final Item ROD_ENDERITE = registerItem("rod_enderite", new Item(new FabricItemSettings()));
    public static final Item ROD_GLOWSTONE = registerItem("rod_glowstone", new Item(new FabricItemSettings()));
    public static final Item ROD_GOLD = registerItem("rod_gold", new Item(new FabricItemSettings()));
    public static final Item ROD_IRON = registerItem("rod_iron", new Item(new FabricItemSettings()));
    public static final Item ROD_OBSIDIAN = registerItem("rod_obsidian", new Item(new FabricItemSettings()));
    //endregion

    //region CITRINE HEADS
    public static final Item HEAD_CITRINE_AXE = registerItem("head_citrine_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_CITRINE_HAMMER = registerItem("head_citrine_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_CITRINE_HOE = registerItem("head_citrine_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_CITRINE_PICKAXE = registerItem("head_citrine_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_CITRINE_SHOVEL = registerItem("head_citrine_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_CITRINE_SWORD = registerItem("head_citrine_sword", new Item(new FabricItemSettings()));
    //endregion

    //region COPPER HEADS
    public static final Item HEAD_COPPER_AXE = registerItem("head_copper_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_COPPER_HAMMER = registerItem("head_copper_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_COPPER_HOE = registerItem("head_copper_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_COPPER_PICKAXE = registerItem("head_copper_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_COPPER_SHOVEL = registerItem("head_copper_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_COPPER_SWORD = registerItem("head_copper_sword", new Item(new FabricItemSettings()));
    //endregion

    //region DIAMOND HEADS
    public static final Item HEAD_DIAMOND_AXE = registerItem("head_diamond_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_DIAMOND_HAMMER = registerItem("head_diamond_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_DIAMOND_HOE = registerItem("head_diamond_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_DIAMOND_PICKAXE = registerItem("head_diamond_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_DIAMOND_SHOVEL = registerItem("head_diamond_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_DIAMOND_SWORD = registerItem("head_diamond_sword", new Item(new FabricItemSettings()));
    //endregion

    //region ENDERITE HEADS
    public static final Item HEAD_ENDERITE_AXE = registerItem("head_enderite_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_ENDERITE_HAMMER = registerItem("head_enderite_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_ENDERITE_HOE = registerItem("head_enderite_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_ENDERITE_PICKAXE = registerItem("head_enderite_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_ENDERITE_SHOVEL = registerItem("head_enderite_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_ENDERITE_SWORD = registerItem("head_enderite_sword", new Item(new FabricItemSettings()));
    //endregion

    //region GOLD HEADS
    public static final Item HEAD_GOLD_AXE = registerItem("head_gold_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_GOLD_HAMMER = registerItem("head_gold_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_GOLD_HOE = registerItem("head_gold_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_GOLD_PICKAXE = registerItem("head_gold_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_GOLD_SHOVEL = registerItem("head_gold_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_GOLD_SWORD = registerItem("head_gold_sword", new Item(new FabricItemSettings()));
    //endregion

    //region IRON HEADS
    public static final Item HEAD_IRON_AXE = registerItem("head_iron_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_IRON_HAMMER = registerItem("head_iron_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_IRON_HOE = registerItem("head_iron_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_IRON_PICKAXE = registerItem("head_iron_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_IRON_SHOVEL = registerItem("head_iron_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_IRON_SWORD = registerItem("head_iron_sword", new Item(new FabricItemSettings()));
    //endregion

    //region NETHERITE HEADS
    public static final Item HEAD_NETHERITE_AXE = registerItem("head_netherite_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_NETHERITE_HAMMER = registerItem("head_netherite_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_NETHERITE_HOE = registerItem("head_netherite_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_NETHERITE_PICKAXE = registerItem("head_netherite_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_NETHERITE_SHOVEL = registerItem("head_netherite_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_NETHERITE_SWORD = registerItem("head_netherite_sword", new Item(new FabricItemSettings()));
    //endregion

    //region RUBY HEADS
    public static final Item HEAD_RUBY_AXE = registerItem("head_ruby_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_RUBY_HAMMER = registerItem("head_ruby_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_RUBY_HOE = registerItem("head_ruby_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_RUBY_PICKAXE = registerItem("head_ruby_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_RUBY_SHOVEL = registerItem("head_ruby_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_RUBY_SWORD = registerItem("head_ruby_sword", new Item(new FabricItemSettings()));
    //endregion

    //region SAPPHIRE HEADS
    public static final Item HEAD_SAPPHIRE_AXE = registerItem("head_sapphire_axe", new Item(new FabricItemSettings()));
    public static final Item HEAD_SAPPHIRE_HAMMER = registerItem("head_sapphire_hammer", new Item(new FabricItemSettings()));
    public static final Item HEAD_SAPPHIRE_HOE = registerItem("head_sapphire_hoe", new Item(new FabricItemSettings()));
    public static final Item HEAD_SAPPHIRE_PICKAXE = registerItem("head_sapphire_pickaxe", new Item(new FabricItemSettings()));
    public static final Item HEAD_SAPPHIRE_SHOVEL = registerItem("head_sapphire_shovel", new Item(new FabricItemSettings()));
    public static final Item HEAD_SAPPHIRE_SWORD = registerItem("head_sapphire_sword", new Item(new FabricItemSettings()));
    //endregion

    //region STONE HEADS
    public static final Item HEAD_STONE_HAMMER = registerItem("head_stone_hammer", new Item(new FabricItemSettings()));
    //endregion

    //region WOOD HEADS
    public static final Item HEAD_WOOD_HAMMER = registerItem("head_wood_hammer", new Item(new FabricItemSettings()));
    //endregion

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