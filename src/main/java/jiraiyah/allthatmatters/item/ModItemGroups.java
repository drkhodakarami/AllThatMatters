package jiraiyah.allthatmatters.item;

import jiraiyah.allthatmatters.AllThatMatters;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups
{
    /*public static final ItemGroup RUBY_Group = Registry.register(Registries.ITEM_GROUP,
            new Identifier(StripBlock.ModID, "stripperblockgroup"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.stripperblockgroup"))
                    .icon(() -> new ItemStack(ModBlocks.STRIPPER_BLOCK)).entries((displayContext, entries) -> {

                        entries.add(ModBlocks.STRIPPER_BLOCK);

                    }).build());*/

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Item Groups for : " + AllThatMatters.ModID);
    }
}