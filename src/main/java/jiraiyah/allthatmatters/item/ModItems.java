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
    //public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));

    /*private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries)
    {
        //entries.add(RUBY);
    }*/

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, new Identifier(AllThatMatters.ModID, name), item);
    }

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Items for : " + AllThatMatters.ModID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}