package jiraiyah.allthatmatters.item;

import jiraiyah.allthatmatters.AllThatMatters;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

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