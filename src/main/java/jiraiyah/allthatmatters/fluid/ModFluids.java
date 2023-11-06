package jiraiyah.allthatmatters.fluid;

import jiraiyah.allthatmatters.AllThatMatters;
import jiraiyah.allthatmatters.fluid.custom.MoltenEnderite;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids
{
    public static FlowableFluid STILL_MOLTEN_ENDERITE = Registry.register(Registries.FLUID,
            new Identifier(AllThatMatters.ModID, "molten_enderite"),
            new MoltenEnderite.Still());
    public static FlowableFluid FLOWING_MOLTEN_ENDERITE = Registry.register(Registries.FLUID,
            new Identifier(AllThatMatters.ModID, "flowing_molten_enderite"),
            new MoltenEnderite.Flowing());
    public static Block MOLTEN_ENDERITE_BLOCK = Registry.register(Registries.BLOCK, "molten_ender_block",
            new FluidBlock(ModFluids.STILL_MOLTEN_ENDERITE, FabricBlockSettings.copyOf(Blocks.WATER)));
    public static Item MOLTEN_ENDERITE_BUCKET = Registry.register(Registries.ITEM, new Identifier(AllThatMatters.ModID, "molten_enderite_bucket"),
            new BucketItem(ModFluids.STILL_MOLTEN_ENDERITE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static void register()
    {
        AllThatMatters.LOGGER.info(">>> Registering Fluids");
    }
}